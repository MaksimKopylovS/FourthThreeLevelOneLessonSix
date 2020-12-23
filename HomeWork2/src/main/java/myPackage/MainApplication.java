package myPackage;

import myPackage.configuration.MagazinConfiguration;
import myPackage.model.Basket;
import myPackage.model.Product;
import myPackage.model.User;
import myPackage.repositories.BasketRepositories;
import myPackage.repositories.ProductRepositories;
import myPackage.repositories.UserRepositories;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;


public class MainApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MagazinConfiguration.class);
        //Beans
        ProductRepositories productRepositories = context.getBean("productRepositories", ProductRepositories.class);
        UserRepositories userRepositories = context.getBean("userRepositories", UserRepositories.class);
        BasketRepositories basketRepositories = context.getBean("basketRepositories", BasketRepositories.class);

        //productRepositories.creatPriduct(new Product("Огурчик",55));
        //userRepositories.creatUser(new User("User2", "User2"));
        //basketRepositories.creatBasket(new Basket(2, 4, 5, productRepositories.getProduct(4).getCost(), LocalDateTime.now().toString()));
        //productRepositories.editProduct(4, "Огурчики", 150);

//      позволяет по id покупателя узнать список купленных товаров
        userRepositories.getUserBasket(2);

//      позволяет по id товара узнать список покупателей этого товара;
        productRepositories.getProductBy(4);

//     детализация по паре «покупатель — товар»: сколько стоил товар в момент покупки клиентом;
        basketRepositories.getBasketOldCost(2);
    }
}
