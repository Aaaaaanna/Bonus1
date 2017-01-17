package pl.polsl.bonus.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pl.polsl.bonus.model.Employee;
import pl.polsl.bonus.repository.EmployeeJpaRepository;

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  EmployeeJpaRepository employeeRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findEmployeesByLogin(login);
        System.out.println(employee.getRole());
        if(employee != null) {
        	if(employee.getRole().equalsIgnoreCase("ADMIN")){	
        		User currUser = new User(employee.getLogin(), employee.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList("ADMIN","MANAGER","USER"));
        		System.out.println("User admin: "+ currUser.getUsername() + " "+ currUser.getAuthorities());
        		return currUser;
        	}else if(employee.getRole().equalsIgnoreCase("MANAGER")){
        		User currUser = new User(employee.getLogin(), employee.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList("MANAGER","USER"));
                		System.out.println("User manager: "+ currUser.getUsername() + " "+ currUser.getAuthorities());
                		return currUser;
        	}
        	else {
        		User currUser = new User(employee.getLogin(), employee.getPassword(), true, true, true, true,
                        AuthorityUtils.createAuthorityList("USER"));
                		System.out.println("User: "+ currUser.getUsername() + " "+ currUser.getAuthorities());
                		return currUser;} 
        }
        else {
          throw new UsernameNotFoundException("could not find the user '"
                  + login + "'");
        }
      }
      
    };
  }
}
