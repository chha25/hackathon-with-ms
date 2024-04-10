package com.example.demo;

import com.microsoft.applicationinsights.attach.ApplicationInsights;
import com.microsoft.applicationinsights.connectionstring.ConnectionString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	@Value("${APPLICATIONINSIGHTS_CONNECTION_STRING}")
	private static String applicationInsightsConnectionString;

	public static void main(String[] args) {
		ApplicationInsights.attach();
		ConnectionString.configure(applicationInsightsConnectionString);
		SpringApplication.run(DemoApplication.class, args);
	}

}
