/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author ecolina
 */
@Entity
@Table(name = "venta")
public class Sell implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String date;

    @OneToMany(mappedBy = "sell", cascade = CascadeType.ALL)
    private Set<ProductSold> productos;

    public Sell() {
        //this.date = util.obtenerFechaYHoraActual();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getTotal() {
        Float total = 0f;
        for (ProductSold productoVendido : this.productos) {
            total += productoVendido.getTotal();
        }
        return total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Set<ProductSold> getProductos() {
        return productos;
    }

    public void setProductos(Set<ProductSold> productos) {
        this.productos = productos;
    }
    
}
