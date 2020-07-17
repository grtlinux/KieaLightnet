package org.tain;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.tain.domain.Lightnet;
import org.tain.repository.LightnetRepository;
import org.tain.utils.CurrentInfo;
import org.tain.utils.Flag;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class KieaLightnet06Info01Application implements CommandLineRunner {

	public static void main(String[] args) {
		log.info("KANG-20200628 >>>>> {} {}", CurrentInfo.get(), LocalDateTime.now());
		SpringApplication.run(KieaLightnet06Info01Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("KANG-20200628 >>>>> {}", CurrentInfo.get());
		if (Flag.flag) job02();
	}

	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////////////////////
	
	@Value("${json.table-file.lightnet}")
	private String jsonTableFileLightnet;
	
	@Autowired
	private LightnetRepository lightnetRepository;
	
	private void job02() throws Exception {
		log.info("KANG-20200618 >>>>> {}", CurrentInfo.get());
		
		ObjectMapper objectMapper = new ObjectMapper();
		List<Lightnet> lstLightnet = objectMapper.readValue(new File(this.jsonTableFileLightnet), new TypeReference<List<Lightnet>>() {});

		lstLightnet.forEach(entry -> {
			this.lightnetRepository.save(entry);
		});
	}
	
	//////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////

	@Configuration
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	@EnableWebSecurity
	static class SecurityConfiguration extends WebSecurityConfigurerAdapter {

		@Bean
		InMemoryUserDetailsManager userDetailsManager() {
			User.UserBuilder commonUser = User.withUsername("commonUser").password("{noop}common").roles("USER");
			User.UserBuilder havi = User.withUsername("havi").password("{noop}test").roles("USER", "ADMIN");

			List<UserDetails> userDetailsList = new ArrayList<>();
			userDetailsList.add(commonUser.build());
			userDetailsList.add(havi.build());

			return new InMemoryUserDetailsManager(userDetailsList);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			CorsConfiguration configuration = new CorsConfiguration();
			configuration.addAllowedOrigin(CorsConfiguration.ALL);
			configuration.addAllowedMethod(CorsConfiguration.ALL);
			configuration.addAllowedHeader(CorsConfiguration.ALL);
			UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", configuration);

			http.httpBasic()
					.and().authorizeRequests()
					//.antMatchers(HttpMethod.POST, "/Boards/**").hasRole("ADMIN")
					.anyRequest().permitAll()
					.and().cors().configurationSource(source)
					.and().csrf().disable();
		}
	}
}
