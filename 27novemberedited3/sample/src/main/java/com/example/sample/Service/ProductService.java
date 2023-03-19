
package com.example.sample.Service;


import com.example.sample.Model.Product;
import com.example.sample.Repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    
    
     public List<Product>getAllProduct(){
    
    return  productRepository.findAll();
    }
     
     
     //Product object
    public void addProduct(Product product){
        
        productRepository.save(product);
    
    
    }
    
    
    //METHoD return deleteById public void ddeleteProductById
     public void deleteProductById(long id){productRepository.deleteById(id);}
     
     
     
     //optional whetrher id may present or not
     public Optional<Product> getProductById(long id){
         return productRepository.findById(id);
     }
    
     //user section
     
      public List<Product>getAllProductsByCategoryById(int id){
      return productRepository.findAllByCategory_Id(id);
      }
}
