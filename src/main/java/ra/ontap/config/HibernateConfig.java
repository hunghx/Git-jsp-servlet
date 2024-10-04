package ra.ontap.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class HibernateConfig {
    // câú hình kết nối CSDL
    @Bean
    public SessionFactory sessionFactory(){
        return  new org.hibernate.cfg.Configuration()
                .configure("hibernate-config.xml")
                .buildSessionFactory();
    }

    @Bean
    public EntityManager entityManager(){
        return sessionFactory().createEntityManager();
    }
}
