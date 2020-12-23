package myPackage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(schema = "basket")
public class Basket  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_user")
    private int id_user;
    @Column(name = "id_product")
    private int id_product;
    @Column(name = "quantuty")
    private int quantuty;
    @Column(name = "old_cost")
    private int old_cost;
    @Column(name = "data")
    private String data;

    public Basket(int id_user, int id_product, int quantuty, int old_cost, String data){
        this.id_user = id_user;
        this.id_product = id_product;
        this.quantuty = quantuty;
        this.old_cost = old_cost;
        this.data = data;
    }


}
