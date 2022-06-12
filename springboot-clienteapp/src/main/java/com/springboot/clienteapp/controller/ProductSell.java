/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.controller;

import com.springboot.clienteapp.models.entity.Products;

/**
 *
 * @author ecolina
 */
public class ProductSell extends Products{
    
    private Float cant;

    public ProductSell(String name, String cod, Float precio, Float stock, Integer id, Float cant) {
        super(name, cod, precio, stock, id);
        this.cant = cant;
    }

    public ProductSell(String name, String cod, Float price, Float stock, Float cant) {
        super(name, cod, price, stock);
        this.cant = cant;
    }

    public void aumentarCantidad() {
        this.cant++;
    }

    public Float getCantidad() {
        return cant;
    }

    public Float getTotal() {
        return this.getPrice() * this.cant;
    }
}
