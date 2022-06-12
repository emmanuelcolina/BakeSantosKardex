/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.springboot.clienteapp.models.repository;

import com.springboot.clienteapp.models.entity.Products;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author ecolina
 */
public interface ProductsRepository extends CrudRepository <Products, Integer>{
    
}
