package com.example.mainproject.RpController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mainproject.RpEntity.RpEntity;
import com.example.mainproject.RpService.RpService;
import java.util.*;

@RequestMapping("/startweb")
@RestController("/rpfirst")
public class RpController {
	@Autowired
	RpService rs;
	
	@PostMapping("/posts")
	public           RpEntity method1(@RequestBody RpEntity detail) {
		return rs.method1(detail);
	}
	
	@GetMapping("/getAll")
	public   long method2(){
	
		return rs.method2();
		}

	@DeleteMapping("/delete/{id}")
	public String deleteMohithById(@PathVariable int id) {
    	return this.mohithService.deleteById(id);
}
	 @GetMapping("/getids/{ids}")
	 public  Optional<RpEntity> method3(@PathVariable(value="ids")  int id) {
		 return rs.method3(id);
	 }
	  @PutMapping("/up/{ids}")
	 public RpEntity method4(@RequestBody RpEntity datass ,@PathVariable(value="ids")  int idd) {
		return rs.method4(datass, idd); 
	 }
  	

}
