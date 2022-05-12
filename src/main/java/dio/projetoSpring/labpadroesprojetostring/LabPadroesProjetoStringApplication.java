package dio.projetoSpring.labpadroesprojetostring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients //Habilitando o Feign
@SpringBootApplication
public class LabPadroesProjetoStringApplication {

	public static void main(String[] args) {

		SpringApplication.run(LabPadroesProjetoStringApplication.class, args);
	}

}
