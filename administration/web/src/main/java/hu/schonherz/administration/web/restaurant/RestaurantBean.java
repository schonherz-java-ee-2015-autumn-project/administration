package hu.schonherz.administration.web.restaurant;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("restaurantLazyBean")
@ViewScoped
public class RestaurantBean {

	@Inject
	private LazyRestaurant lazyDataModel;
	public LazyRestaurant getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyRestaurant lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	



}