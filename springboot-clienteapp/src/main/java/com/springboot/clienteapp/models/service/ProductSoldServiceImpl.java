/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.ProductSold;
import com.springboot.clienteapp.models.repository.ProductSoldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecolina
 */

@Service
public class ProductSoldServiceImpl implements IProductSoldService{
    
    @Autowired
    private ProductSoldRepository productSoldRepository;
    
    @Override
    public void guardar(ProductSold productSold) {
        productSoldRepository.save(productSold);
    }
}
