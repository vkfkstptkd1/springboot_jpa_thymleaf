package com.example.springboot_oracle_jpa.controller;


import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ItemForm {

    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String author;
    private String isbn;
}
