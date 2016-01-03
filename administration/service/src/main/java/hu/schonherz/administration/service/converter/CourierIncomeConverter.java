package hu.schonherz.administration.service.converter;

import java.util.ArrayList;
import java.util.List;

import hu.schonherz.administration.persistence.dao.UserDao;
import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.User;
import hu.schonherz.administration.serviceapi.dto.CourierIncomeDTO;

public class CourierIncomeConverter {
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public CourierIncomeDTO toDTO(CourierIncome income) {
		CourierIncomeDTO result = new CourierIncomeDTO();
		result.setId(income.getId());
		result.setActualCash(income.getActualCash());
		result.setActualVoucher(income.getActualVoucher());
		User u = income.getCourier();
		if (u != null) {
			result.setCourierId(u.getId());
			result.setCourierName(u.getName());
		}
		result.setCrediCard(income.getCrediCard());
		result.setDate(income.getDate());
		result.setSZÉPCard(income.getSZÉPCard());
		result.setVoucher(income.getVoucher());
		return result;
	}

	public CourierIncome toEntity(CourierIncomeDTO income) {
		CourierIncome result = new CourierIncome();
		result.setId(income.getId());
		result.setActualCash(income.getActualCash());
		result.setActualVoucher(income.getActualVoucher());
		if (income.getCourierId() != null) {
			User u = userDao.findOne(income.getCourierId());
			if (u != null) {
				result.setCourier(u);
			}
		}
		result.setCrediCard(income.getCrediCard());
		result.setDate(income.getDate());
		result.setSZÉPCard(income.getSZÉPCard());
		result.setVoucher(income.getVoucher());
		return result;
	}
	
	public List<CourierIncome> toEntity(List<CourierIncomeDTO> income){
		if(income == null)
			return null;
		List<CourierIncome> entity = new ArrayList<>();
		for(CourierIncomeDTO i: income){
			entity.add(toEntity(i));
		}
		return entity;
	}
	
	public List<CourierIncomeDTO> toDTO(List<CourierIncome> income){
		if(income == null)
			return null;
		List<CourierIncomeDTO> dto = new ArrayList<>();
		for(CourierIncome i: income){
			dto.add(toDTO(i));
		}
		return dto;
	}
}
