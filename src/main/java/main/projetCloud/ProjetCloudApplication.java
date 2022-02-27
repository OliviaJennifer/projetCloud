package main.projetCloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import main.projetCloud.filters.AuthFilter;

@SpringBootApplication
public class ProjetCloudApplication {

	public static void main(String[] args) {SpringApplication.run(ProjetCloudApplication.class, args);}
	
	@Bean
	public FilterRegistrationBean<AuthFilter> filterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		registrationBean.addUrlPatterns("/api/signalement/*");
		registrationBean.addUrlPatterns("/api/responsable/allTypeSignalement");
		return registrationBean;
	}
}
