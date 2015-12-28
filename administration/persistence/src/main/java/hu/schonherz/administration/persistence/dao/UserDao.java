package hu.schonherz.administration.persistence.dao;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import hu.schonherz.administration.persistence.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long> ,  JpaSpecificationExecutor<User>{

	User findByUsername(String username);
	
	User findById(long id);

	Long countByUsername(String username);
	
	

}
