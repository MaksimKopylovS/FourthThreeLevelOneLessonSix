package myPackage.session;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SessionHibernate {
    private SessionFactory sessionFactory;
    private Session session;
    @PostConstruct
    public void init(){
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}

