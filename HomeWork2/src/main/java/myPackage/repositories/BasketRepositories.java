package myPackage.repositories;

import myPackage.model.Basket;
import myPackage.model.Product;
import myPackage.model.User;
import myPackage.session.SessionHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class BasketRepositories {
    private SessionHibernate sessionHibernate;

    @Autowired
    public BasketRepositories(SessionHibernate sessionHibernate){
        this.sessionHibernate = sessionHibernate;
    }

    public void creatBasket(Basket basket) {
        try (Session session = sessionHibernate.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(basket);
            session.getTransaction().commit();
        }
    }

    public void getBasketOldCost(int idUser){
        String sql = "SELECT `user`.login, product.title, product.cost, basket.old_cost  FROM basket \n" +
                "INNER JOIN product on basket.id_product = product.id\n" +
                "INNER JOIN `user` on basket.id_user = `user`.id\n" +
                "WHERE id_user = "+idUser+";";
        try(Session session = sessionHibernate.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            List<Object[]> listObject = session.createSQLQuery(sql).list();
            for (Object[] o : listObject){
                System.out.println("Имя: "+o[0].toString() +" " +
                        "Продукт: "+o[1].toString()+ " " +
                        "Новая цена: "+o[2].toString()+ " " +
                        "Старая цена: "+o[3].toString());
            }
        }
    }

}
