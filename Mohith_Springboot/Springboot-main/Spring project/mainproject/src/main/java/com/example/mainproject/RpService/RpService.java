package com.example.mainproject.RpService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.example.mainproject.RpEntity.RpEntity;
import com.example.mainproject.RpRepo.RpRepo;

@Service
public class RpService {
	@Autowired
	RpRepo rr;
	public RpEntity method1(RpEntity datas) {
		return rr.save(datas);
	}
    public  long method2(){
   return rr.count();
    }
    public Optional<RpEntity> method3(int ids) {
    	return rr.findById(ids);
    	
    }
    public String deleteById(int id) {
    if (repo.existsById(id)) {
        repo.deleteById(id);
        return "Successfully deleted";
    }
    return "Id not found";
}

   public RpEntity method4(RpEntity newData ,int id  ) {
	   RpEntity	 oldData = rr.findById(id).orElseThrow(()-> new NullPointerException("Id is not found"));
	      oldData.setStudent_name(newData.getStudent_name());
           oldData.setDepartment(newData.getDepartment());
           oldData.setLocation(newData.getLocation());
           return rr.save(oldData);
      
   }
    
}
