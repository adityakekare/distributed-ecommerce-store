package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.context.ServerPortInfoApplicationContextInitializer;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

//@EnableSwagger2
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);
	}

	@Component
	public static class ServerPortLogger implements ApplicationListener<WebServerInitializedEvent> {

		public void onApplicationEvent(WebServerInitializedEvent event) {
			int serverPort = event.getWebServer().getPort();
//			System.out.println("Server Port:" +  serverPort);
			String url = "http://localhost:1010/register/" + serverPort;
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.getForObject(url, String.class);
//			System.out.println(result);
		}
	}

//	@PostConstruct
//	public void executeGetRequest() {
////		int serverPort = webServerAppCtxt.getWebServer().getPort();
//		int serverPort = serverProperties.getPort();
//		System.out.println("Server Port:" +  serverPort);
//		String url = "http://localhost:1010/register/" + serverPort;
//    RestTemplate restTemplate = new RestTemplate();
//    String result = restTemplate.getForObject(url, String.class);
////
////		System.out.println(result);
//	}

}
