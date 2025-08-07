package com.example.mainproject.RpRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mainproject.RpEntity.RpEntity;

public interface RpRepo extends JpaRepository <RpEntity,Integer> {

}
