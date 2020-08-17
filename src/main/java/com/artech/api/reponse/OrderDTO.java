package com.artech.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private int orderId;

    private double totalPrice;

    private Date createdAt;


    private Date updatedAt;

    private int status;


    private UserResponse users;


    private List<OrderDetailDTO> orderDetailEntities;
}
