package com.example.springboot_oracle_jpa.domain.item;


import com.example.springboot_oracle_jpa.domain.Category;
import com.example.springboot_oracle_jpa.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)// 상속 시 전략 정하기ㅣ
@DiscriminatorColumn(name="dtype")//싱글테이블일경우 자식테이블들 구분을 위해 지정.
@Getter@Setter
public abstract class Item { // 구현체를 갖고 할 것이기에 추상
    @Id @GeneratedValue
    @Column(name="item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany
    private List<Category> categories = new ArrayList<>();


//    비즈니스 로직 그냥 entity 안에 작성.
//    stock 증가
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
//    stock 감소
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock<0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }

}
