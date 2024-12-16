package es.dsw.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	public static InMemoryUserDetailsManager InMemory;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
		   .authorizeHttpRequests((authorize) -> authorize
				    			  .requestMatchers("/css/**").permitAll()
				    			  .requestMatchers("/ayuda").permitAll()
				    			  .requestMatchers("/admin/**").hasRole("manager")
				    			  .requestMatchers("/basic/rrhh").hasRole("supervisor")
				    			  .requestMatchers("/basic/almacen").hasRole("supervisor")
				    			  .requestMatchers("/basic/marketing").hasRole("comercial")
				                  .anyRequest().authenticated()
				                 )
		   .httpBasic(withDefaults())
		   .formLogin(form -> form
				              .loginPage("/loggin")
				              .loginProcessingUrl("/logginprocess")
				              .defaultSuccessUrl("/wellcome", true)
				              .permitAll()
				   )
		   .logout((logout) -> logout.logoutSuccessUrl("/loggin?logout").permitAll());
		   
		
		
		return http.build();
	}
	
	@Bean
	InMemoryUserDetailsManager userDetailsService() {
		
		@SuppressWarnings("deprecation")
		UserDetails user1 = User.withDefaultPasswordEncoder()
		    .username("Miguel") //Nombre de usuario
            .password("1234")   //Password
            .roles("manager") //Roles
            .build();
		
		@SuppressWarnings("deprecation")
		UserDetails user2 = User.withDefaultPasswordEncoder()
		    .username("Rosa") //Nombre de usuario
            .password("4567")   //Password
            .roles("supervisor") //Roles
            .build();
		
		@SuppressWarnings("deprecation")
		UserDetails user3 = User.withDefaultPasswordEncoder()
		    .username("Javier") //Nombre de usuario
            .password("8910")   //Password
            .roles("comercial") //Roles
            .build();
		

		InMemory = new InMemoryUserDetailsManager();
		
		InMemory.createUser(user1);
		InMemory.createUser(user2);
		InMemory.createUser(user3);
		
		return InMemory;
	}
	

}
