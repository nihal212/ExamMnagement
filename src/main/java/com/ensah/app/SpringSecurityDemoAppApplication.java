package com.ensah.app;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ensah.app.entities.Authority;
import com.ensah.app.entities.User;
import com.ensah.app.repository.UserDetailsRepository;

@SpringBootApplication
public class SpringSecurityDemoAppApplication {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityDemoAppApplication.class, args);
	}
	
	/*@PostConstruct
	protected void init() {
		
		List<Authority> authorityList=new ArrayList<>();
		
		authorityList.add(createAuthority("USER","User role"));
		//authorityList.add(createAuthority("ADMIN","Admin role"));
		
		User user=new User();
		
		user.setUserName("nihale");
		user.setFirstName("nihal");
		user.setLastName("saidy");
		
		user.setPassword(passwordEncoder.encode("nihal123456"));
		user.setEnabled(true);
		user.setAuthorities(authorityList);
		
		userDetailsRepository.save(user);
		
		
		
	}*/
	
	
	private Authority createAuthority(String roleCode,String roleDescription) {
		Authority authority=new Authority();
		authority.setRoleCode(roleCode);
		authority.setRoleDescription(roleDescription);
		return authority;
	}
	
	

}
