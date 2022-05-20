package com.starry.comment.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    /**
     * 配置绑定 application.properties
     * @return
     */
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }

    /**
     * 后台监控
     * 注册一个bean到servlet容器
     * 因为springboot内置了servlet,没有web.xml,所以我们直接注册进去就行了
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet(){
        //使用指定的Servlet和URL映射创建一个新的ServletRegistrationBean实例
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");

        HashMap<String, String> initParameters = new HashMap<>();
        // 配置后台的账号和密码 key必须是loginUsername loginPassword
        initParameters.put("loginUsername", "admin");
        initParameters.put("loginPassword", "123456");
        // 运行谁可以访问
        initParameters.put("allow", "localhost");
        // 禁止谁访问
        //initParameters.put("guest", "192.168.1.13");

        // 设置初始化参数
        bean.setInitParameters(initParameters);

        return bean;
    }
}
