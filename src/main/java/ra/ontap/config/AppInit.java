package ra.ontap.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration // đây là lớp cấu hình
@EnableWebMvc // cấu hình theo MVC
@ComponentScan(basePackages = "ra.ontap") // phát hiện cách thành phần và khởi tạo bean (@Component,@Controller, @Service, @Repository)
public class AppInit {
    // khai báp cac bean
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
