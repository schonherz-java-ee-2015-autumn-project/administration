package hu.schonherz.administration.web.report;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;


@Named("reportLazyBean")
@ViewScoped
public class IncomeReportBean {

	@Inject
	private LazyIncomeReport lazyDataModel;
	
	private List<SortMeta> preSortOrder;
	
	private CourierIncomeDTO selected;

	public void buildSortOrder() {
		preSortOrder = new ArrayList<>();
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
	
		UIComponent column2 = viewRoot.findComponent("incomeReportForm:income_table:income_date");

		SortMeta sm2 = new SortMeta();
		sm2.setSortBy((org.primefaces.component.api.UIColumn) column2);
		sm2.setSortField("date");
		sm2.setSortOrder(SortOrder.DESCENDING);

		preSortOrder.add(sm2);
	}
	
	
	
	public LazyIncomeReport getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyIncomeReport lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public List<SortMeta> getPreSortOrder() {
		return preSortOrder;
	}

	public void setPreSortOrder(List<SortMeta> preSortOrder) {
		this.preSortOrder = preSortOrder;
	}

	public CourierIncomeDTO getSelected() {
		return selected;
	}

	public void setSelected(CourierIncomeDTO selected) {
		this.selected = selected;
	}

}
