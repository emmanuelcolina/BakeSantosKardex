/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Products;
import com.springboot.clienteapp.models.repository.ProductsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecolina
 */

@Service
public class ProductsServiceImpl implements IProductsService{
    
    @Autowired
    private ProductsRepository productsRepository;
    
    @Override
    public List<Products> listPorducts() {
        return (List<Products>) productsRepository.findAll();
    }

    @Override
    public void guardar(Products products) {
        productsRepository.save(products);
    }

    @Override
    public String buscarPorCodigo(Products products) {
        
        for(Products p :  productsRepository.findAll()){
            
            if(p.getCod().equalsIgnoreCase(products.getCod())){
                return p.getCod();
            }
        }
        
        return null;    
    }
    
    @Override
    public Products buscarPorCodigoObjeto(String cod) {
        
        for(Products p :  productsRepository.findAll()){
            
            if(p.getCod().equalsIgnoreCase(cod)){
                return p;
            }
        }
        
        return null;    
    }
    
    @Override
    public Products buscarPorId(Integer id)  {		
            return productsRepository.findById(id).orElse(null);
    }
    
    @Override
    public void eliminar(Integer id){		
            productsRepository.deleteById(id);
    }
}
