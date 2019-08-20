package auth.service.authservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import auth.service.authservice.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	Address findByXCordAndYCord(double getxCord, double getyCord);


}
