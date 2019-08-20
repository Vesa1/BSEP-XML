package auth.service.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auth.service.authservice.model.Authority;

@Repository
public interface RoleRepository extends JpaRepository<Authority, Long>{

	Authority findByName(String string);

}
