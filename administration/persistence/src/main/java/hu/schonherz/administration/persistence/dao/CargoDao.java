package hu.schonherz.administration.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import hu.schonherz.administration.persistence.entities.Cargo;
import hu.schonherz.administration.persistence.entities.User;

@Repository
public interface CargoDao extends JpaRepository<Cargo, Long>, JpaSpecificationExecutor<Cargo> {
	
	List<Cargo> findByCourier(User courier);

}
