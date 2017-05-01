package site.zido.jianliao.common.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        if (profile != null && Objects.equals(profile, "dev"))
//            http.csrf().disable(); //开发模式下禁用csrf
        http.csrf().disable();

        http.authorizeRequests()
                //admin/superAdmin可访问
                .antMatchers("/api/admin/**").hasAnyRole("管理员", "超级管理员")
                //superAdmin可访问
                .antMatchers("/api/superAdmin/**").hasRole("管理员")
                //user/superAdmin/admin可访问
                .antMatchers("/api/user/**").hasAnyRole("用户", "管理员", "超级管理员")
                //其他均可匿名访问
                .anyRequest().anonymous();

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
