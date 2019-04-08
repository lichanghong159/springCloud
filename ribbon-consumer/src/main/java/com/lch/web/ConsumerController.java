package com.lch.web;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {
	@Autowired
	private RestTemplate restTemplate;
	@RequestMapping(value = "/ribbon-consumer",method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "fallback")
	public String helloConsumer(){
		return restTemplate.getForEntity("http://HELLO-SERVER/hello",String.class).getBody();
	}
	public String fallback() {
		System.out.println("fallbackMethod worldCustomer");
		return "fallback Customer";
	}
}
