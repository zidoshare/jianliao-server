package site.zido.jianliao.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * cyd.
 * Date: 2017/3/22 0022
 * Time: 19:33
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Value("${spring.profiles.active}")
    private String profile;
    @Resource
    private AuthFailureHandler failureHandler;
    @Resource
    private HttpAuthenticationEntryPoint entryPoint;
    @Resource
    private AuthSuccessHandler successHandler;
    @Resource
    private HttpLogoutSuccessHandler logoutSuccessHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        if (profile != null && Objects.equals(profile, "dev"))
//            http.csrf().disable(); //开发模式下禁用csrf
        http.csrf().disable().exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .authorizeRequests()
                //admin/superAdmin可访问
                .antMatchers(HttpMethod.POST,"/api/admin/**").hasAnyRole("管理员", "超级管理员")
                //superAdmin可访问
                .antMatchers(HttpMethod.POST,"/api/superAdmin/**").hasRole("管理员")
                //user/superAdmin/admin可访问
                .antMatchers(HttpMethod.POST,"/api/user/**").hasAnyRole("用户", "管理员", "超级管理员")
                //其他均可匿名访问
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginProcessingUrl("/auth/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .permitAll()
                //.and().rememberMe() //if the HTTP parameter named "remember-me" exists ,then the user will be remembered even after their always
                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/auth/loginOut"))
                .and()
                .sessionManagement()
                .maximumSessions(1) //only one can login in
                .expiredUrl("/auth/shotOff");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getService());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Bean
    public UserDetailsService getService() {
        return new CustomUserService();
    }
}
