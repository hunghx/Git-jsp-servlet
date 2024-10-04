package ra.ontap.config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class DispatcherConfig extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // khai baos cacs caaus hinhf ko liên quan tơ MVC
        return new Class[]{HibernateConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Cấu hin các thành phần lien quan tơi MVC
        return new Class[]{AppInit.class};
    }

    @Override
    protected String[] getServletMappings() {
        // cấu hình đờng dẫn ánh xạ
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter("UTF-8",true);
        return new Filter[]{filter};
    }
}
