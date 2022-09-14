package com.example.springboot_oracle_jpa.repository;

import com.example.springboot_oracle_jpa.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if (item.getId() == null) { // item은 jpa에 저장하기 전까지 id값이 없음. 즉, 새로운 객체
            em.persist(item);       //신규 등록
        } else {                    //이미 디비에 등록된 것을 어디서 가져옴.
            em.merge(item);         // 강제로 update랑 비슷
        }
    }

    public Item findOne(Long id){ // 한개 조회
        return em.find(Item.class, id);
    }

    public List<Item> findAll(){ // 여러개 조회
        return em.createQuery("select i from Item  i", Item.class)
                .getResultList();
    }
}
