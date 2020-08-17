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
public class CategoryDTO {

    private int categoryId;

    private String name;

    private String description;

    private Date createdAt;

    private Date updatedAt;

    private int status;



    private List<ProductDTO> products;
}
