package hu.schonherz.administration.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import hu.schonherz.administration.persistence.entities.Restaurant;

@Repository
public interface RestaurantDao extends JpaRepository<Restaurant, Long>, JpaSpecificationExecutor<Restaurant> {

	Restaurant findByName(String name);
}
