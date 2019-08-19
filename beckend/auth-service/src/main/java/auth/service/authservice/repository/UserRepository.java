package auth.service.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auth.service.authservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String username);

}
