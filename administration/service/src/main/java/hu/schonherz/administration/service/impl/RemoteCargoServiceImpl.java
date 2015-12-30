package hu.schonherz.administration.service.impl;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import hu.schonherz.administration.persistence.dao.CargoDao;
import hu.schonherz.administration.service.converter.CargoConverter;
import hu.schonherz.administration.service.validator.CargoValidator;
import hu.schonherz.administration.serviceapi.RemoteCargoService;
import hu.schonherz.administration.serviceapi.dto.CargoDTO;
import hu.schonherz.administration.serviceapi.exeption.InvalidFieldValuesException;

@Stateless(mappedName = "RemoteCargoService")
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Interceptors(SpringBeanAutowiringInterceptor.class)
@Local(RemoteCargoService.class)
public class RemoteCargoServiceImpl implements RemoteCargoService {

	@Autowired
	private CargoDao cargoDao;

	@Autowired
	private CargoConverter cv;

	@Override
	public void saveCargo(CargoDTO cargo) throws InvalidFieldValuesException {
		if (CargoValidator.isValidCargo(cargo))
			cargoDao.save(cv.toEntity(cargo));

	}

	@Override
	public List<CargoDTO> getCargos() {
		return cv.toDTO(cargoDao.findAll());
	}

	public CargoDao getCargoDao() {
		return cargoDao;
	}

	public void setCargoDao(CargoDao cargoDao) {
		this.cargoDao = cargoDao;
	}

	public CargoConverter getCv() {
		return cv;
	}

	public void setCv(CargoConverter cv) {
		this.cv = cv;
	}

}
