//값타입.

package com.example.springboot_oracle_jpa.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable//내장타입. 어딘가 내장될 수 잇다.
@Getter //변경이 되면 안됨. 생성될때만 생성.
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address(){}//jpa는 이거 생성해둬야함. 무슨 기술 사용할 때 생성자 필요하다는데 .. 몬말이야

    public Address(String city,String street, String zipcode){
        this.city=city;
        this.street=street;
        this.zipcode=zipcode;
    }
}
