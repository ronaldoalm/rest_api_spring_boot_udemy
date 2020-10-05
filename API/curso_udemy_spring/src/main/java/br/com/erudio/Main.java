package br.com.erudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.erudio.config.FileStorageConfig;

@SpringBootApplication
@EnableAutoConfiguration
@EnableConfigurationProperties({FileStorageConfig.class})
@ComponentScan
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(16);
		//String result = bCryptPasswordEncoder.encode("admin123");
		//System.out.println("My hash" + result);
	}

}
