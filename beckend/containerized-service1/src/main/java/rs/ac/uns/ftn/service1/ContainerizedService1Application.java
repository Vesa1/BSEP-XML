package rs.ac.uns.ftn.service1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ContainerizedService1Application {

	public static void main(String[] args) {
		SpringApplication.run(ContainerizedService1Application.class, args);
	}
}
