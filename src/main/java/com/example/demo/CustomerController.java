package com.example.demo;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookstore")
public class CustomerController {
	@PostMapping("/api_uri/{account_uid}")
	public String testTerraform(@PathVariable String account_uid) {
		return new String("{\"region\": \"g\",\"access_key\": \"dfg\",\"secret_key\": \"gdg\"}");
	}
}
