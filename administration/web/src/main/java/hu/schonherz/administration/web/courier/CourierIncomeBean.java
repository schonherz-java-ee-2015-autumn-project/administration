package hu.schonherz.administration.web.courier;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;

@Named("courierIncomeLazyBean")
@ViewScoped
public class CourierIncomeBean {

	@Inject
	private LazyCourierIncome lazyDataModel;

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

}