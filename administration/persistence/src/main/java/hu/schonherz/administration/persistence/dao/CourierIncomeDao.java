package hu.schonherz.administration.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.schonherz.administration.persistence.entities.CourierIncome;
import hu.schonherz.administration.persistence.entities.User;

public interface CourierIncomeDao extends JpaRepository<CourierIncome, Long> ,  JpaSpecificationExecutor<CourierIncome>{
	
	List<CourierIncome> findByCourier(User courier);
}
