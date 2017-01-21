//package pl.polsl.bonus.configuration;
//
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
//@EnableWebSecurity
//@Configuration
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers(HttpMethod.GET, "/teams/**", "/employees/**", "/bonuses/**", "/nodes/**")
//				.hasAuthority("USER").antMatchers(HttpMethod.PUT, "/nodes/**").hasAuthority("MANAGER")
//				.antMatchers(HttpMethod.POST, "/*").permitAll().antMatchers("/**/**").hasAuthority("ADMIN").anyRequest()
//				.authenticated().and().httpBasic().and().csrf().disable();
//	}
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
//	}
//}