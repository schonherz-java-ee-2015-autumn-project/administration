package hu.schonherz.administration.web.courier;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("courierLazyBean")
@ViewScoped
public class CourierBean {
	

	@Inject
	private LazyCourier lazyDataModel;
	
	public LazyCourier getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyCourier lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
	
	



}