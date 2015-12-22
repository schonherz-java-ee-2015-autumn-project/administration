package hu.schonherz.administration.web.restaurant;

import java.io.IOException;

import javax.enterprise.context.spi.Context;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.springframework.web.context.request.FacesRequestAttributes;

import hu.schonherz.administration.serviceapi.dto.RestaurantDTO;

@Named("restaurantLazyBean")
@ViewScoped
public class RestaurantBean {
	
	private RestaurantDTO selected;

	@Inject
	private LazyRestaurant lazyDataModel;

	
	public LazyRestaurant getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyRestaurant lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	public RestaurantDTO getSelected() {
		return selected;
	}

	public void setSelected(RestaurantDTO selected) {
		this.selected = selected;
	}
	
	public void redirect(SelectEvent event) {
		if(!selected.equals(((RestaurantDTO)event.getObject()))){
			selected = (RestaurantDTO)event.getObject();
		}
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(ec.getRequestContextPath() + "/secured/restaurant/edit.xhtml?selected="+selected.getId());
		} catch (IOException e) {
		}
    
	}
}