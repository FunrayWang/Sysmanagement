package com.sys.lunasysmanagement;

import com.sys.lunasysmanagement.filter.SysFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan(basePackages = {"com.sys.lunasysmanagement.mapper"})
@SpringBootApplication
@EnableSwagger2
public class LunasysmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LunasysmanagementApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        //将权限过滤类加入过滤器中
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SysFilter());
        return filterRegistrationBean;
    }

}
