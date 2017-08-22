package com.hazelcast.jet;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import com.hazelcast.jet.config.HazelProperties;

@SpringBootApplication
@EnableConfigurationProperties({HazelProperties.class})
public class Application {
	
    private static final Logger log = LoggerFactory.getLogger(Application.class);
   
	public static void main(String[] args) throws UnknownHostException {
		 SpringApplication app = new SpringApplication(Application.class);
	      
	        Environment env = app.run(args).getEnvironment();
	        String protocol = "http";
	        if (env.getProperty("server.ssl.key-store") != null) {
	            protocol = "https";
	        }
	        log.info("\n----------------------------------------------------------\n\t" +
	                "Application '{}' is running! Access URLs:\n\t" +
	                "Local: \t\t{}://localhost:{}\n\t" +
	                "External: \t{}://{}:{}\n\t" +
	                "Profile(s): \t{}\n----------------------------------------------------------",
	            env.getProperty("spring.application.name"),
	            protocol,
	            env.getProperty("server.port"),
	            protocol,
	            InetAddress.getLocalHost().getHostAddress(),
	            env.getProperty("server.port"),
	            env.getActiveProfiles());
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}
