package com.maliware.let.srecruit;

import com.maliware.let.srecruit.model.User;
import com.maliware.let.srecruit.repository.UserRepo;
import com.maliware.let.srecruit.service.CompetenceService;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class Application implements SmartInitializingSingleton {

	@Autowired
	UserRepo userRepo;
	@Autowired
	CompetenceService setInialSkillVaues;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void afterSingletonsInstantiated() {
		assert setInialSkillVaues != null;
		setInialSkillVaues.injectCommonSkills();
//		Optional<User> admin1 = userRepo.findByUsername("admin");
//		Optional<User>  haroun = userRepo.findByUsername("haroun");
		UUID userId = UUID.randomUUID();
		UUID admId  = UUID.randomUUID();
		User admin = new User(admId, "admin",passwordEncoder().encode("admin"),true);
		admin.addAuthority("READ");
		admin.addAuthority("WRITE");
		User user = new User(userId,"haroun",passwordEncoder().encode("haroun"),true);
		user.addAuthority("READ");
		if (!admin.getUuid().equals(admId) && !user.getUuid().equals(userId)){
		this.userRepo.saveAndFlush(admin);
		this.userRepo.saveAndFlush(user);


		}
//		if (admin1.isEmpty() && haroun.isEmpty()){
//			this also work fine
//		}
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	WebMvcConfigurer webMvc() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry
						.addMapping("/**")
						.allowedMethods("GET","POST", "PUT")
						.allowedOrigins("http://localhost:4200")
						.maxAge(0);
			}
		};
	}
}
