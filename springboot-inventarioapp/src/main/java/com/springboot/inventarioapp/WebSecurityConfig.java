package com.springboot.inventarioapp;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
@Autowired
private javax.sql.DataSource dataSource;

@Autowired
private BCryptPasswordEncoder passEncoder;




@Override
protected void configure(HttpSecurity http) throws Exception {
	
	// Proteccion de las rutas

	http.authorizeRequests()
	
	.antMatchers("/","/home","/css/**","/js/**","/images/**").permitAll()
	.antMatchers("/views/productos/").hasAnyRole("USER")
	.antMatchers("/views/tiendas/").hasAnyRole("USER")
	.antMatchers("/views/empleados/").hasAnyRole("USER")
	.antMatchers("/views/productos/create").hasAnyRole("ADMIN")
	.antMatchers("/views/tiendas/create").hasAnyRole("ADMIN")
	.antMatchers("/views/empleados/create").hasAnyRole("ADMIN")
	.antMatchers("/views/productos/save").hasAnyRole("ADMIN")
	.antMatchers("/views/tiendas/save").hasAnyRole("ADMIN")
	.antMatchers("/views/empleados/save").hasAnyRole("ADMIN")
	.antMatchers("/views/productos/edit/**").hasAnyRole("ADMIN")
	.antMatchers("/views/tiendas/edit/**").hasAnyRole("ADMIN")
	.antMatchers("/views/tiendas/empleados/**").hasAnyRole("ADMIN")
	.antMatchers("/views/productos/delete/**").hasAnyRole("ADMIN")
	.antMatchers("/views/tiendas/delete/**").hasAnyRole("ADMIN")
	.antMatchers("/views/tiendas/empleados/**").hasAnyRole("ADMIN")
	.anyRequest().authenticated()
	.and().formLogin().loginPage("/login").permitAll()
	.and().logout().permitAll();
}




@Autowired
public void configurerSecurityGlobal(AuthenticationManagerBuilder builder) throws Exception {
	
	builder.jdbcAuthentication()
	.dataSource(dataSource)
	.passwordEncoder(passEncoder)
	.usersByUsernameQuery("SELECT username,PASSWORD,enabled FROM users WHERE username=?;")
	.authoritiesByUsernameQuery("SELECT u.username, r.rol FROM roles r INNER JOIN users u ON r.user_id = u.id WHERE username =?;");
	
}

}
