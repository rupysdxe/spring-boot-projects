package com.dev.rupesh.springbootmongo.product.repository;

import com.dev.rupesh.springbootmongo.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rupesh Dangi
 * @date: 2023/07/24 16/23
 */
@Repository
public interface ProductRepository  extends MongoRepository<Product,String>
{
}
