package hu.schonherz.administration.web.courier;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.CourierIncomeService;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;
import hu.schonherz.administration.web.localization.MessageProvider;

@Named("courierIncomeLazyBean")
@ViewScoped
@EJB(name = "ejb.CourierIncomeService", beanInterface = CourierIncomeService.class)
public class CourierIncomeBean {

	private static String ACTUAL_CASH = "actual_cash";
	private static String ACTUAL_VOUCHER = "actual_voucher";
	private Integer confirmedValue;
	private String confirmedColumn;
	private CourierIncomeDTO confirmedIncome;
	@EJB
	private CourierIncomeService incomeService;

	@Inject
	private LazyCourierIncome lazyDataModel;

	private List<SortMeta> preSortOrder;

	public CourierIncomeService getIncomeService() {
		return incomeService;
	}

	public void setIncomeService(CourierIncomeService incomeService) {
		this.incomeService = incomeService;
	}

	public List<SortMeta> getPreSortOrder() {
		return preSortOrder;
	}

	public void setPreSortOrder(List<SortMeta> preSortOrder) {
		this.preSortOrder = preSortOrder;
	}

	private CourierIncomeDTO selected;

	public CourierIncomeDTO getSelected() {
		return selected;
	}

	public void setSelected(CourierIncomeDTO selected) {
		this.selected = selected;
	}

	public LazyCourierIncome getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyCourierIncome lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public void onCellEdit(CellEditEvent event) {
		FacesMessage msg = null;
		CourierIncomeDTO dto = (CourierIncomeDTO) ((DataTable) event.getComponent()).getRowData();

		CourierIncomeDTO edited = incomeService.getCourierIncomeById(dto.getId());

		Integer newValue = (Integer) event.getNewValue();
		Integer oldValue = (Integer) event.getOldValue();
		if (newValue != null && oldValue != null && edited != null && newValue>=0) {
			String columnName = event.getColumn().getClientId();
			if (columnName.contains(ACTUAL_CASH)) {
				msg = editActualValues(msg, edited, newValue, ACTUAL_CASH);
			} else {
				msg = editActualValues(msg, edited, newValue, ACTUAL_VOUCHER);
			}
		} else {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,MessageProvider.getValue("save_failed") ,
					MessageProvider.getValue("invalid_income_value"));
		}
		if (msg != null)
			FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	private FacesMessage editActualValues(FacesMessage msg, CourierIncomeDTO edited, Integer newValue, String column) {

		Integer required = 0;
		Integer old = 0;
		if (column.equals(ACTUAL_CASH)) {
			required = edited.getCash();
			old = edited.getActualCash();
		} else {
			required = edited.getVoucher();
			old = edited.getActualVoucher();
		}
		if (newValue > required) {
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,MessageProvider.getValue("save_failed") ,
					MessageProvider.getValue("invalid_income_value"));
		} else {
			if (newValue < required || newValue < old) {
				confirmedValue = newValue;
				confirmedColumn = column;
				confirmedIncome = edited;
				showConfirmationDialogue();
			} else {
				if (column.equals(ACTUAL_CASH)) {
					edited.setActualCash(newValue);
				} else {
					edited.setActualVoucher(newValue);
				}
				incomeService.save(edited);
				msg = new FacesMessage(FacesMessage.SEVERITY_INFO,MessageProvider.getValue("successful_save") ,
						null);
			}
		}
		return msg;
	}

	public void buildSortOrder() {
		preSortOrder = new ArrayList<>();
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		UIComponent column = viewRoot.findComponent("courierIncomeForm:income_table:courier_name");

		SortMeta sm1 = new SortMeta();
		sm1.setSortBy((org.primefaces.component.api.UIColumn) column);
		sm1.setSortField("courierName");
		sm1.setSortOrder(SortOrder.ASCENDING);

		UIComponent column2 = viewRoot.findComponent("courierIncomeForm:income_table:income_date");

		SortMeta sm2 = new SortMeta();
		sm2.setSortBy((org.primefaces.component.api.UIColumn) column2);
		sm2.setSortField("date");
		sm2.setSortOrder(SortOrder.DESCENDING);

		preSortOrder.add(sm1);
		preSortOrder.add(sm2);
	}

	public void saveEdited() {
		if (confirmedColumn != null && confirmedValue != null && confirmedIncome != null) {
			if (confirmedColumn.equals(ACTUAL_CASH)) {
				confirmedIncome.setActualCash(confirmedValue);
			} else {
				confirmedIncome.setActualVoucher(confirmedValue);
			}
			incomeService.save(confirmedIncome);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, MessageProvider.getValue("successful_save"), ""));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, MessageProvider.getValue("save_failed"), ""));
		}
	}

	private void showConfirmationDialogue() {
		RequestContext context = RequestContext.getCurrentInstance();
		context.execute("PF('confirmation').show();");
	}

	public Integer getConfirmedValue() {
		return confirmedValue;
	}

	public void setConfirmedValue(Integer confirmedValue) {
		this.confirmedValue = confirmedValue;
	}

	public String getConfirmedColumn() {
		return confirmedColumn;
	}

	public void setConfirmedColumn(String confirmedColumn) {
		this.confirmedColumn = confirmedColumn;
	}

	public CourierIncomeDTO getConfirmedIncome() {
		return confirmedIncome;
	}

	public void setConfirmedIncome(CourierIncomeDTO confirmedIncome) {
		this.confirmedIncome = confirmedIncome;
	}

}