
package com.example.sample.controller;

import com.example.sample.DTO.CouponDTO;
import com.example.sample.DTO.ProductDTO;
import com.example.sample.Model.Category;
import com.example.sample.Model.Coupons;
import com.example.sample.Model.Events;
import com.example.sample.Model.Product;
import com.example.sample.Service.CategoryService;
import com.example.sample.Service.CouponsService;
import com.example.sample.Service.EventsService;
import com.example.sample.Service.ProductService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import javax.persistence.CacheRetrieveMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller

//////===================Category Section==============================////
public class AdminController {

	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

	@Autowired
	CategoryService categoryService;

	@Autowired
	ProductService productService;
	@Autowired
	EventsService eventsService;
	@Autowired
	CouponsService couponsService;

	@GetMapping("/admin")
	public String adminHome() {
		return "adminHome";
	}

	@GetMapping("/admin/categories")
	public String getcategories(Model model) {

		model.addAttribute("categories", categoryService.getAllCategory());
		return "categories";
	}

	@GetMapping("/admin/categories/add")
	public String getcatadd(Model model) {

		// object pass
		model.addAttribute("category", new Category());
		return "categoriesAdd";
	}

	@PostMapping("/admin/categories/add")
	public String postcatadd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);

		return "redirect:/admin/categories";
	}

	/// deleting on basis category_id id is fetch to delete
	// route
	@GetMapping("/admin/categories/delete/{id}")

	public String deletecat(@PathVariable int id,Model model) {
		try {
			categoryService.deleteCategoryById(id);
			return "redirect:/admin/categories";
		} catch (Exception s) {
			boolean msg = true; 
			model.addAttribute("categories", categoryService.getAllCategory());
			 model.addAttribute("msg", msg);
			return "categories";
		}
	}

	// update
	// route

	@GetMapping("/admin/categories/update/{id}")

	/// check
	public String updateecat(@PathVariable int id, Model model) {
		Optional<Category> category = categoryService.getCategoryById(id);

		if (category.isPresent()) {
			model.addAttribute("category", category.get());
			return "categoriesAdd";
		} else
			return "404";

	}

	////// ===================Product Section==============================////

	// route
//     
	@GetMapping("/admin/products")

	public String products(Model model) {

		model.addAttribute("products", productService.getAllProduct());
		return "products";
	}

	@GetMapping("/admin/products/add")
	public String productadd(Model model) {

		// object pass
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productsAdd";

		//
	}

	@PostMapping("/admin/products/add")
	// frontend to html-
	// productImage in param request
	// productImage client to request in request param object
	// DTO is handy
	// multipart file
	//
	public String productaddpost(@ModelAttribute("productDTO") ProductDTO productDTO,
			@RequestParam("productImage") MultipartFile file, @RequestParam("imgName") String imgName)
			throws IOException {

		// productDTO object into product object
		// map conceptis there too

		Product product = new Product();

		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		// product model whole category object provides we have just id we need to fetch
		// catgory id

		// categoryService-id basis origin category object fetch
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());

		product.setPrice(productDTO.getPrice());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());

		// image 2 part 1.database 2.static1

		// self generated uuid-128bit no token,images names. we cant use original name
		// of img
		String imageUUID;

		// false value
		if (!file.isEmpty()) {

			imageUUID = file.getOriginalFilename();

			// path class
			Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());

		} else {

			imageUUID = imgName;
		}

		product.setImageName(imageUUID);
		productService.addProduct(product);

		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/delete/{id}")

	public String deleteProduct(@PathVariable long id) {

		productService.deleteProductById(id);
		return "redirect:/admin/products";
	}

	@GetMapping("/admin/product/update/{id}")

	public String updateProductGet(@PathVariable long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setPrice(product.getPrice());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setImageName(product.getImageName());

		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("productDTO", productDTO);
		return "productsAdd";
	}

//	==========================================================================================================================================================
//////===================Events Section==============================////

	// route
//  

	@GetMapping("/admin/events")
	public String getEvents(Model model) {

		model.addAttribute("events", eventsService.getAllevents());
		return "events";
	}

	@GetMapping("/admin/events/add")
	public String geteventsadd(Model model) {

		// object pass
		model.addAttribute("events", new Events());
		return "eventsAdd";
	}

	@PostMapping("/admin/events/add")
	public String posteventsadd(@ModelAttribute("events") Events events) {
		
		eventsService.addevents(events);
		return "redirect:/admin/events";
	}

	/// deleting on basis category_id id is fetch to delete
	// route
	@GetMapping("/admin/events/delete/{id}")

	public String deleteevents(@PathVariable int id,Model model) {
		try {
			eventsService.deleteeventsById(id);
			return "redirect:/admin/events";
		} catch (Exception s) {
			boolean msg = true; 
			model.addAttribute("events", eventsService.getAllevents());
			 model.addAttribute("msg", msg);
			return "events";
		}
	}

	// update
	// route

	@GetMapping("/admin/events/update/{id}")

	/// check
	public String updateevents(@PathVariable int id, Model model) {
		Optional<Events> events = eventsService.geteventsById(id);

		if (events.isPresent()) {
			model.addAttribute("events", events.get());
			return "eventsAdd";
		} else
			return "404";

	}
//	===============================================================================================================

	@GetMapping("/admin/coupons")

	public String coupons(Model model) {

		model.addAttribute("coupons", couponsService.getAllCoupons());
		System.out.println(couponsService.getAllCoupons() + "get coupons");
		return "coupons";
	}

//	
	@GetMapping("/admin/coupons/Add")
	public String couponsAddGet(Model model) {

		// object pass
		model.addAttribute("couponsDTO", new CouponDTO());
		model.addAttribute("events", eventsService.getAllevents());
		return "couponsAdd";

		//
	}

	@PostMapping("/admin/coupons/Add")

	//
	public String couponsAddPost(@ModelAttribute("couponsDTO") CouponDTO couponsDTO, MultipartFile file)
			throws IOException {

		Coupons coupons = new Coupons();

		coupons.setId(couponsDTO.getId());
		System.out.println(couponsDTO.getId());
		coupons.setName(couponsDTO.getName());
		coupons.setEvents(eventsService.geteventsById(couponsDTO.getEventsId()).get());

//		coupons.setCategory(categoryService.getCategoryById(couponsDTO.getCategoryId()).get());

		couponsService.addCoupons(coupons);

		return "redirect:/admin/coupons";

	}

	@GetMapping("/admin/coupons/delete/{id}")

	public String deleteCoupons(@PathVariable long id) {

		couponsService.deleteCouponsById(id);
		return "redirect:/admin/coupons";
	}

	@GetMapping("/admin/coupons/update/{id}")
	public String updateCouponsGet(@PathVariable long id, Model model) {

		Coupons coupons = couponsService.getCouponsById(id).get();
		CouponDTO couponsDTO = new CouponDTO();
		couponsDTO.setId(coupons.getId());
		couponsDTO.setName(coupons.getName());
//			couponsDTO.setCategoryId(coupons.getCategory().getId());
		couponsDTO.setEventsId(coupons.getEvents().getId());

		model.addAttribute("events", eventsService.getAllevents());
//		model.addAttribute("categories",categoryService.getAllCategory());
		model.addAttribute("couponsDTO", couponsDTO);
		return "couponsAdd";

	}
}
