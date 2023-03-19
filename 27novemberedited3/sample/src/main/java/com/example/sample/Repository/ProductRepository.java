
package com.example.sample.Repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sample.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product>findAllByCategory_Id(int id);
}
