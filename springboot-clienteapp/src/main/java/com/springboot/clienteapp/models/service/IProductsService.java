/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.springboot.clienteapp.models.service;
import com.springboot.clienteapp.models.entity.Products;
import java.util.List;

/**
 *
 * @author ecolina
 */
public interface IProductsService {
    
    public List<Products> listPorducts();
    
    public void guardar(Products products);
    
    public String buscarPorCodigo(Products cod);
    
    public Products buscarPorId(Integer id);
    
    public void eliminar(Integer id);
    
    public Products buscarPorCodigoObjeto(String cod);
    
}
