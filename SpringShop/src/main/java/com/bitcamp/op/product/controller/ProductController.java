package com.bitcamp.op.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bitcamp.op.product.model.ProductList;
import com.bitcamp.op.product.service.ProductListService;
import com.bitcamp.op.product.service.ProductSelectService;

@Controller
public class ProductController {

	@Autowired
	ProductListService productListService;
	
	@Autowired
	ProductSelectService productSelectService;
	
	@RequestMapping("/product/productList")
	public String productList(Model model, 
			@RequestParam(value="page", defaultValue="1") int pageNumber,
			@RequestParam(value="keyword", required=false) String keyword,
			@RequestParam(value="category", required=false) String category,
			@RequestParam(value="row_price", defaultValue="0") int row_price,
			@RequestParam(value="high_price", defaultValue="0") int high_price
			
			) {
		
			System.out.println(category);
		model.addAttribute("product", productListService.getProductList(pageNumber, keyword, category, row_price, high_price ));
		return "product/productList";
	}
	
	
	@RequestMapping("/product/productDetail/{productNo}")
	public String productDetail(@PathVariable("productNo") int productNo, Model model) {
		
		model.addAttribute("productDTO", productSelectService.selectProduct(productNo));
		
		return "product/productDetail";
	}
}
