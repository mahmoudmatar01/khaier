package com.example.khaier;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@OpenAPIDefinition(servers =
        {
                @Server(
                        url = "https://khaier-production.up.railway.app",
                        description = "Remote Project for Historian API"
                ),
                @Server(
                        url = "http://localhost:5920",
                        description = "Matar Local Project for Historian API"
                )
        }
)
public class KhaierApplication  {

    public static void main(String[] args) {
        SpringApplication.run(KhaierApplication.class, args);
    }

}
