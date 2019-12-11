package es.uc3m.ecommerce.users.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface AppuserDAO extends CrudRepository<Appuser, Long>{

	List<Appuser> findByUserRoleAndIsDeleted(int userRole, int isDeleted);
	List<Appuser> findByIsDeleted(int isDeleted);
	
}