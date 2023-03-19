
package com.example.sample.Service;

import com.example.sample.Model.Category;
import com.example.sample.Model.Coupons;
import com.example.sample.Repository.CouponsRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CouponsService {
    @Autowired
    CouponsRepository couponsRepository;
    
    
     public List<Coupons> getAllCoupons(){
    
    return  couponsRepository.findAll();
    }
     
     
     //Coupons object
    public void addCoupons(Coupons coupons){
        
    	couponsRepository.save(coupons);
    
    
    }
    
    
    //METHoD return deleteById public void ddeleteCouponsById
     public void deleteCouponsById(long id){couponsRepository.deleteById(id);}
     
     
     
     //optional whether id may present or not
     public Optional<Coupons> getCouponsById(long id){
         return couponsRepository.findById(id);
     }
    
     public Coupons getCouponsByName(String name)
     {
    	 return couponsRepository.findByName(name);
     }
     //user section
     
      public List<Coupons>getAllCouponsByEventsId(int id){
      return couponsRepository.findAllByEventsId(id);
      }
      
       public Coupons findByName(String applycoupon){
    	   return null;
       }
       
}
