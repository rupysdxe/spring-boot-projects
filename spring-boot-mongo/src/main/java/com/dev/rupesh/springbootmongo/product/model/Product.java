package com.dev.rupesh.springbootmongo.product.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author Rupesh Dangi
 * @date: 2023/07/24 15/54
 */
@Data
@Document(collection = "product")
public class Product
{
    @Id
    private String id;
    private String name;
}
