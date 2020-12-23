package myPackage.repositories;

import myPackage.model.Product;
import myPackage.model.User;
import myPackage.session.SessionHibernate;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
@Scope(scopeName = "prototype")
public class ProductRepositories {
    private SessionHibernate sessionHibernate;
    private List<Product> productList;


    @Autowired
    public ProductRepositories(SessionHibernate sessionHibernate) {
        this.sessionHibernate = sessionHibernate;
    }


    public void creatPriduct(Product product) {
        try (Session session = sessionHibernate.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.save(product);
            session.getTransaction().commit();
        }
    }

    public Product getProduct(int id) {
        try (Session session = sessionHibernate.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            Product p = session.get(Product.class, id);
            if (p == null) {
                return null;
            }
            return p;
        }
    }

    public List<Product> allProduct() {
        String sql = "SELECT * FROM magproduct.product";
        try (Session session = sessionHibernate.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<Object[]> list = session.createSQLQuery(sql).list();
            List<Product> productList = new ArrayList<>();
            for(Object[] o : list){
                productList.add(new Product(Integer.valueOf(o[0].toString()), o[1].toString(), Integer.valueOf(o[2].toString())));
            }
            return Collections.unmodifiableList(productList);
        }
    }

    public void getProductBy(int idProduct){
        try(Session session = sessionHibernate.getSessionFactory().getCurrentSession()){
            session.beginTransaction();
            Product product = session.get(Product.class, idProduct);
            System.out.println("Product: ");
            for (User user : product.getUserList()){
                System.out.println(product.getId()+ " " + user.getLogin());
            }
        }
    }

    public void editProduct(int id, String titile, int cost) {
        System.out.println("до метода Allproduct");
        List<Product> productList = allProduct();
        try (Session session = sessionHibernate.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
        for (int i = 0; i < productList.size(); i++) {
            if (id == productList.get(i).getId()) {

                    Product product1 = session.get(Product.class, id);
                    if (product1 == null) {
                        throw new NullPointerException();
                    }
                    product1.setTitle(titile);
                    product1.setCost(cost);
                }
            }
            session.getTransaction().commit();
        }
    }
}
