package com.example.springboot_oracle_jpa.domain;

import com.example.springboot_oracle_jpa.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 바깥에서 새로운 객체 인스턴스 생성하지마.
public class OrderItem {

    @Id @GeneratedValue
    @Column(name="order_item_id")

    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    private int orderPrice;// 주문 당시 가격
    
    private int count;//주문 수량

//    생성 메서드
    public static OrderItem createOrderItem(Item item, int orderPrice, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        //주문 하는 순간 재고 빼줌.
        item.removeStock(count);
        return orderItem;
    }

//    비즈니스 로직
//    취소한 주문수량 만큼 재고 늘려줌.
    public void cancel() {
        getItem().addStock(count);
    }


//    조회 로직
//    주문상품 전체 가격 조회.
    public int getTotalPrice() {
        return getOrderPrice() * getCount();
    }

    //protected OrderItem(){
    //    //바깥에서 새로운 객체인스턴스 만드는데 쓰지 말라고 명시.
    //}
}
