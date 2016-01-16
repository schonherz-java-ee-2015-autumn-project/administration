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

import hu.schonherz.administration.serviceapi.dto.RestaurantReportDTO;


@Named("restaurantReportLazyBean")
@ViewScoped
public class RestaurantReportBean {

	@Inject
	private LazyRestaurantReport lazyDataModel;
	
	private List<SortMeta> preSortOrder;
	
	private RestaurantReportDTO selected;
	
	public void buildSortOrder() {
		preSortOrder = new ArrayList<>();
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		UIComponent column1 = viewRoot.findComponent("restaurantReportForm:restaurant_report_table:restaurant_report_name");
		SortMeta sm1 = new SortMeta();
		sm1.setSortBy((org.primefaces.component.api.UIColumn) column1);
		sm1.setSortField("restaurant");
		sm1.setSortOrder(SortOrder.ASCENDING);
		
		UIComponent column = viewRoot.findComponent("restaurantReportForm:restaurant_report_table:restaurant_report_date");
		SortMeta sm = new SortMeta();
		sm.setSortBy((org.primefaces.component.api.UIColumn) column);
		sm.setSortField("date");
		sm.setSortOrder(SortOrder.DESCENDING);

		preSortOrder.add(sm);
		preSortOrder.add(sm1);
	}

	public List<SortMeta> getPreSortOrder() {
		return preSortOrder;
	}

	public void setPreSortOrder(List<SortMeta> preSortOrder) {
		this.preSortOrder = preSortOrder;
	}

	public LazyRestaurantReport getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyRestaurantReport lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public RestaurantReportDTO getSelected() {
		return selected;
	}

	public void setSelected(RestaurantReportDTO selected) {
		this.selected = selected;
	}

}
