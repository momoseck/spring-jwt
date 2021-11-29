package com.api.springsecurityjwt;

import com.api.springsecurityjwt.entity.User;
import com.api.springsecurityjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringsecurityjwtApplication {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void init(){
		List<User> users = Stream.of(
				new User(1,"admin",bCryptPasswordEncoder.encode("admin"),"seckmamadou007@gmail.com"),
				new User(2,"momo",bCryptPasswordEncoder.encode("momo"),"seckmamadou00@gmail.com")
		).collect(Collectors.toList());
		userRepository.saveAll(users);
		System.out.println(userRepository.findAll().size() );
		for( User user: userRepository.findAll()){
			System.out.println(user.getUsername()+" "+user.getPassword());
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringsecurityjwtApplication.class, args);
	}

}
