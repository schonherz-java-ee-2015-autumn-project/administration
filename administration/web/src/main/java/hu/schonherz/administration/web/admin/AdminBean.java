package hu.schonherz.administration.web.admin;

import java.io.IOException;

import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import hu.schonherz.administration.serviceapi.dto.UserDTO;


@Named("adminBean")
@ViewScoped
public class AdminBean {
	
	@Inject
	private LazyAdmin lazyDataModel;
	
	private UserDTO selected;
	
	public void redirect(SelectEvent event) {
		if(!selected.equals(((UserDTO)event.getObject()))){
			selected = (UserDTO)event.getObject();
		}
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    try {
			ec.redirect(ec.getRequestContextPath() + "/secured/administration/edit.xhtml?selected="+selected.getId());
		} catch (IOException e) {
		}
    
	}
	
	
	public UserDTO getSelected() {
		return selected;
	}


	public void setSelected(UserDTO selected) {
		this.selected = selected;
	}


	public LazyAdmin getLazyDataModel() {
		return lazyDataModel;
	}
	
	public void setLazyDataModel(LazyAdmin lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
	



}