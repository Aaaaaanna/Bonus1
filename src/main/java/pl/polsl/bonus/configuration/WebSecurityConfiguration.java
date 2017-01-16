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
        if(employee != null) {
        return new User(employee.getLogin(), employee.getPassword(), true, true, true, true,
                AuthorityUtils.createAuthorityList("ROLE_BASIC"));
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + login + "'");
        }
      }
      
    };
  }
}
