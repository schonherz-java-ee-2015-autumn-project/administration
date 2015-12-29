package hu.schonherz.administration.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import hu.schonherz.administration.persistence.entities.Cargo;

@Repository
public interface CargoDao extends JpaRepository<Cargo, Long>, JpaSpecificationExecutor<Cargo> {


}
