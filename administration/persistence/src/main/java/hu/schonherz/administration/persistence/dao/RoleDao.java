package hu.schonherz.administration.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hu.schonherz.administration.persistence.entities.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

	Role findByName(String name);
}
