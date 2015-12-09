package hu.schonherz.administration.web.admin;

import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named("adminLazyBean")
@ViewScoped
public class AdminBean {

	@Inject
	private LazyAdmin lazyDataModel;
	public LazyAdmin getLazyDataModel() {
		return lazyDataModel;
	}
	public void setLazyDataModel(LazyAdmin lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}
	



}