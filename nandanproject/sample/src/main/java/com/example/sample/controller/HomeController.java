package com.example.sample.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.sample.Model.User;
import com.example.sample.Repository.UserRepository;
import com.example.sample.Service.CategoryService;
import com.example.sample.Service.ProductService;
import com.example.sample.Service.UserService;
import com.example.sample.global.GlobalData;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	
	 
    @Autowired
    ProductService productService;
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserRepository userRepository;
    
    
  //====================User login starts here================================================  
    
    
    
    //getmapping for login
	@GetMapping("/login")
	public String login(Model model)
	{
		
//		GlobalData.cart.clear();
		return "login";
	}
	
	@PostMapping(value="/login")
	public String isLogin(HttpSession httpSession,@RequestParam("email")String email,
			@RequestParam("password")String password,Model model) {
		
		User user = userService.isUser(email,password);
		Boolean msg = false; 
		if(user!= null)
		{
			httpSession.setAttribute("user",user);
			return "redirect:/shop";
		}
		else {
			
			msg = true; 
			 model.addAttribute("msg", msg);
//			return "404";
			return "login";
		}
	}
	
	
//getmapping for register
	@GetMapping("/register")
	public String registerGet(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}
	
//postmapping for register
	/*
	 * @PostMapping("/register") public String registerPost(@ModelAttribute("user")
	 * User user,HttpSession httpSession, HttpServletRequest request) throws
	 * ServletException { user = userRepository.save(user);
	 * 
	 * 
	 * 
	 * if(userRepository.findUserByEmail(user.getEmail())==null); user =
	 * userRepository.save(user);
	 * 
	 * 
	 * user =
	 * userRepository.findUserByEmailAndPassword(user.getEmail(),user.getPassword())
	 * ; httpSession.setAttribute("user",user); return "redirect:/"; }
	 */
	
	@GetMapping(value="/index")
    public String home(){
        return "index";
   }
	
	
	@GetMapping(value="/logout")
    public String logout(HttpSession httpSession){
        httpSession.invalidate();
        return "redirect:/index";
	}
    
	
	
	 //====================User login ends here================================================  
	
	
	
	
	
	
	
	
  //==============adding category=========================
    
    @PostMapping("/register")
    public String postcatadd(@ModelAttribute("user") User customer){
            userService.addUser(customer);
        
return "redirect:/login";
    }
    
    //====================shop page================================================  
    @GetMapping({"/","/home"})
    
    public String Home(Model model){
    	return "index";
    	    }
    
    
    @GetMapping("/shop")
    
    public String shop(Model model){
    	
    	
    	 model.addAttribute("categories", categoryService.getAllCategory());
    	 
    	 model.addAttribute("products", productService.getAllProduct());
    	 model.addAttribute("cartCount", GlobalData.cart.size());
    	return "shop";
    	    }
    
    
    // once link click route===================th:href="@{/shop/category/{id}(id=${category.id})}"><li
    

    @GetMapping("/shop/category/{id}")
    
    //id basis categories_id
    
    public String shopBycategory(@PathVariable int id, Model model){
    	
    	
    	 model.addAttribute("categories", categoryService.getAllCategory());
    	 
    	 model.addAttribute("products", productService.getAllProductsByCategoryById(id));
    	 
    	 model.addAttribute("cartCount", GlobalData.cart.size());
    	 
    	return "shop";
    	    }
    
    // <a href="#" th:href="@{/shop/viewproduct/{id}(id=${product.id})}" class="btn btn-primary">View Product</a>
    //========================view product=================================
    
    
    
 @GetMapping("/shop/viewproduct/{id}")
    
    
    
    public String viewProduct(@PathVariable int id, Model model){
    	
    	
    	 
    	 //==================================.get() from optional is needed otherwise it wont get
    	 model.addAttribute("product", productService.getProductById(id).get());
    	 
    	 model.addAttribute("cartCount", GlobalData.cart.size());
    	 
    	return "viewProduct";
    	    }
    
    

}
