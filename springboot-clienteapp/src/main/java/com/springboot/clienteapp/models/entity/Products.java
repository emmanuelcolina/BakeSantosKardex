/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.models.entity;

import java.io.Serializable;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author ecolina
 */

@Entity
@Table(name="products")
public class Products implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @NotEmpty
    @NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
    private String name;
    
    @NotEmpty
    @NotNull(message = "Debes especificar el código")
    @Size(min = 1, max = 50, message = "El código debe medir entre 1 y 50")
    private String cod;
    
    @NotNull(message = "Debes especificar el precio")
    @Min(value = 0, message = "El precio mínimo es 0")
    private Float price;
    
    @NotNull(message = "Debes especificar la existencia")
    @Min(value = 0, message = "La existencia mínima es 0")
    private Float stock;  
    
    public Products() {
    }
    
    public Products(String name, String cod, Float price, Float stock, Integer id) {
        this.name = name;
        this.cod = cod;
        this.price = price;
        this.stock = stock;
        this.id = id;
    }

    public Products(String name, String cod, Float price, Float stock) {
        this.name = name;
        this.cod = cod;
        this.price = price;
        this.stock = stock;
    }
    
     public boolean noExist() {
        return this.stock <= 0;
    }
     
     public void restExist(Float stock) {
        this.stock -= stock;
    }
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * @param cod the cod to set
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the stock
     */
    public Float getStock() {
        return stock;
    }

    /**
     * @param stock the stock to set
     */
    public void setStock(Float stock) {
        this.stock = stock;
    }
    
    @Override
	public String toString() {
		return "Products [id=" + getId() + ", name=" + getName() + ", cod=" + getCod() + ", price=" + getPrice() + ", stock=" + getStock() +"]";
	}

    public Products orElse(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
