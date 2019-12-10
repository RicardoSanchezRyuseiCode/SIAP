package com.ryuseicode.siap.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * @name WebSecurityConfig
 * {@summary Configuration class for Web Security}
 * @author Ricardo Sanchez Romero (ricardo.sanchez@ryuseicode.com)
 * @since 2019-11-19
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	/**
	 * DataSource
	 */
	@Autowired
	private DataSource dataSource;
	/**
	 * @name httpSessionEventPublisher
	 * {@summary Method to enable session control}
	 * @return HttpSessionEventPublisher
	 */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}
	/**
	 * @name configureGlobal
	 * {@summary Method to configure global authentication}
	 * @param auth Authentication Manager Builder
	 * @throws Exception
	 */
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	auth.jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("select nickname AS email, password, active as enable from UserData where nickname =?")
        .authoritiesByUsernameQuery("select  ud.nickname  AS  email,  r.name  as  authority  from  UserData  UD  inner join  UserData_Role  UDR  on  UD.UserDataId  =  UDR.UserDataID  inner join  Role  R  on  UDR.RoleId  =  R.RoleId  where  ud.nickname  =  ?")
        .passwordEncoder(new BCryptPasswordEncoder());
    }
	/**
	 * @name configure
	 * {@summary Method to configure the security of application}
	 * @param http HttpSecurity
	 * @throws Exception
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		// Menu security configuration
        http.csrf().disable()
	                .authorizeRequests()
	                .antMatchers("/App/**").permitAll()
	                .antMatchers("/Elysium/**").permitAll()
	                .antMatchers("/Xtreme/**").permitAll()
	                .antMatchers("/", "/home", "/util").hasAnyAuthority("ADMIN","AWARD","REQUI","AWREQ")
	                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
	                .antMatchers("/award/**").hasAnyAuthority("ADMIN","AWARD", "AWREQ")
	                .antMatchers("/requisition/**").hasAnyAuthority("ADMIN","REQUI", "AWREQ")
	                .anyRequest().authenticated()
                .and()
	                .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/home")
	                .permitAll()
                .and()
	                .logout()
	    	        .logoutSuccessUrl("/login?logout=true")
	    	        .invalidateHttpSession(true)
	    	        .permitAll();
        // Session control
        http.sessionManagement().maximumSessions(1);
        
    	        
    }
}
