package hu.schonherz.administration.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import hu.schonherz.administration.persistence.entities.RestaurantReport;

public interface RestaurantReportDao extends JpaRepository<RestaurantReport, Long> , JpaSpecificationExecutor<RestaurantReport>{

}
