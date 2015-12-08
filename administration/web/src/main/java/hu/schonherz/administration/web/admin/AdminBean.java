package hu.schonherz.administration.web.admin;

import java.io.Serializable;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Service;


@Named("adminLazyBean")
@ViewScoped
public class AdminBean {

	@Inject
	private LazyAdmin lazyDataModel;
//	private LazyDataModel<UserDTO> lazyDataModel;
	public LazyAdmin getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyAdmin lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	
//	 @PostConstruct
//	    public void init() {
//		 Logger log = Logger.getLogger(LazyAdmin.class.getName());
//			log.debug("INIT MEGHIVVA");
//	        lazyDataModel = new LazyAdmin();
//	
//	    }
//	
//	public LazyDataModel<UserDTO> getLazyDataModel() {
//		return lazyDataModel;
//	}
//
//	public void setLazyDataModel(LazyDataModel<UserDTO> lazyDataModel) {
//		this.lazyDataModel = lazyDataModel;
//	}


}