package myPackage.repositories;

import myPackage.model.Basket;
import myPackage.model.Product;
import myPackage.model.User;
import myPackage.session.SessionHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "prototype")
public class UserRepositories {
    private SessionHibernate sessionHibernate;
    @Autowired
    public UserRepositories(SessionHibernate sessionHibernate){
        this.sessionHibernate = sessionHibernate;
    }

    public void creatUser(User user){
        try(Session sesion = sessionHibernate.getSessionFactory().getCurrentSession()){
            sesion.beginTransaction();
            sesion.save(user);
            sesion.getTransaction().commit();
        }
    }

    public void getUserBasket(int id){
        try(Session session = sessionHibernate.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            for (Product product : user.getProductList()){
                System.out.println(user.getLogin() +" "+ product.getTitle() +" " + product.getCost());
            }
            session.getTransaction().commit();
        }
    }



//    public void getUserBasketOldCost(int id){
//        try(Session session = sessionHibernate.getSessionFactory().getCurrentSession()){
//            session.beginTransaction();
//            User user = session.get(User.class, id);
//            System.out.println("Basket: ");
//            System.out.println(user.getBasketList());
//            for(Basket basket : user.getBasketList()){
//                System.out.println(user.getLogin() + " "+basket.getOld_cost());
//            }
//        }
//    }




}
