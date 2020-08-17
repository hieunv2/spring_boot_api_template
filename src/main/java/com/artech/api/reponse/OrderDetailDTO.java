package com.artech.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailDTO {

    private int deltailId;

    private int quantity;

    private double price;

    private Date createdAt;


    private Date updatedAt;

    private int status;


    private ProductDTO product;


    private OrderDTO orders;
}
