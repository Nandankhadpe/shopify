
package com.example.sample.Service;

import com.example.sample.Model.Category;
import com.example.sample.Model.Events;
import com.example.sample.Repository.CategoryRepository;
import com.example.sample.Repository.EventsRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class EventsService {
    @Autowired
    
    EventsRepository  eventsRepository;
    
    //
    
    public List<Events>getAllevents(){
    
    return  eventsRepository.findAll();
    }
    //events object
    public void addevents(Events events){
        
        eventsRepository.save(events);
    
    
    }
    //METHoD return deleteByIdpublic void deleteCatego
     public void deleteeventsById(int id){eventsRepository.deleteById(id);}
    
     
     //optional whetrher id may present or not
    
	public Optional<Events> geteventsById(int id) {
		// TODO Auto-generated method stub
		return eventsRepository.findById(id);
	}
	

   
    
}
