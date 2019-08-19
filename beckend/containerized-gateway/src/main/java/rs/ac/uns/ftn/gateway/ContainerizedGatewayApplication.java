package rs.ac.uns.ftn.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ContainerizedGatewayApplication
{
	public static void main(String[] args) {
		SpringApplication.run(ContainerizedGatewayApplication.class, args);
		System.out.println("Usao u zuul");
	}
}
