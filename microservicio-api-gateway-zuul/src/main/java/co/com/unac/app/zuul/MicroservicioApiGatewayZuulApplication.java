package co.com.unac.app.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class MicroservicioApiGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioApiGatewayZuulApplication.class, args);
	}

}
