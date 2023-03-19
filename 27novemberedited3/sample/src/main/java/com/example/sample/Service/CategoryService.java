
package com.example.sample.Service;

import com.example.sample.Model.Category;
import com.example.sample.Repository.CategoryRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class CategoryService {
    @Autowired
    
    CategoryRepository  categoryRepository;
    
    //
    
    public List<Category>getAllCategory(){
    
    return  categoryRepository.findAll();
    }
    //Category object
    public void addCategory(Category category){
        
        categoryRepository.save(category);
    
    
    }
    //METHoD return deleteByIdpublic void deleteCatego
     public void deleteCategoryById(int id){categoryRepository.deleteById(id);}
    
     
     //optional whetrher id may present or not
     public Optional<Category> getCategoryById(int id){
         return categoryRepository.findById(id);
     }
    
}
