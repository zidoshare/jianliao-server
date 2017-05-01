package site.zido.jianliao.common;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.util.StringUtils;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 数据源配置类，使用druid作为数据库连接池.
 * Date: 2017/1/5 0005
 * Time: 2:50
 *
 * @author <a href="http://zido.site">wuhongxu</a>.
 * @version 1.0.0
 */
@Configuration
@ConfigurationProperties(prefix = "spring.datasource.druid")
public class DatabaseConfiguration{
    private String loginUserName;
    private String loginPassword;

    /**
     * 配置druid界面，配置账号密码
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings("/druid/*");
        //设置访问监控账号密码
        reg.addInitParameter("loginUsername", loginUserName);
        reg.addInitParameter("loginPassword", loginPassword);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }

    public String getLoginUserName() {
        return loginUserName;
    }

    public DatabaseConfiguration setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
        return this;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public DatabaseConfiguration setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
        return this;
    }
}
