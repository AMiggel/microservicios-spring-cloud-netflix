package co.com.unac.app.configuracion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class MicroservicioConfiguracionServiciosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicioConfiguracionServiciosApplication.class, args);
	}

}
