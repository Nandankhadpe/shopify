package com.example.sample.global;

import java.util.ArrayList;
import java.util.List;

import com.example.sample.Model.Product;

public class GlobalData {
	
	public static List<Product> cart;
	
	static {
		 cart = new ArrayList<Product>();
	}

}
