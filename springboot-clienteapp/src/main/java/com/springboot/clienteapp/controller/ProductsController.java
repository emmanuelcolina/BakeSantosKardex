/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.controller;

import com.springboot.clienteapp.models.entity.Products;
import com.springboot.clienteapp.models.service.IProductsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ecolina
 */

@Controller
@RequestMapping(path = "/productos")
public class ProductsController {
    
    @Autowired
    private IProductsService productsService;
    
    @GetMapping(value = "/mostrar")
    public String showProducts(Model model) {
        model.addAttribute("productos", productsService.listPorducts());
        return "productos/verProductos";
    }
    
    @GetMapping(value = "/agregar")
    public String agregarProducto(Model model) {
        model.addAttribute("producto", new Products());
        model.addAttribute("titulo", "Formulario: Nuevo Cliente");
        return "productos/agregarProductos";
    }
    
    @PostMapping(value = "/agregar")
    public String guardarProducto(@Valid Products producto, BindingResult result,
			Model model, RedirectAttributes redirectAttrs) {
        if (result.hasErrors()) {

            model.addAttribute("titulo", "Formulario: Nuevo Cliente");
            model.addAttribute("producto", producto);
            redirectAttrs
                    .addFlashAttribute("mensaje", "Los campos no deben estar vacios")
                    .addFlashAttribute("clase", "danger");
            return "redirect:/productos/agregar";
        }
        
        //Si encuentra algo en BD es porque estamos en modo edicion
        if(producto.getId() != null){
            if(productsService.buscarPorId(producto.getId()) != null){
            
                if (producto.getPrice() <= 0 || producto.getStock() <= 0){
                    redirectAttrs
                            .addFlashAttribute("mensaje", "Los campos precio y existencia deben ser mayor a 0")
                            .addFlashAttribute("clase", "danger");
                    return "redirect:/productos/agregar";
                }

                productsService.guardar(producto);
                redirectAttrs
                        .addFlashAttribute("mensaje", "Producto Editado")
                        .addFlashAttribute("clase", "success");
                return "redirect:/productos/agregar";

            }
        }
        
        if (productsService.buscarPorCodigo(producto) != null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "Ya existe un producto con ese código")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/productos/agregar";
        }
        
        if (producto.getPrice() <= 0 || producto.getStock() <= 0){
            redirectAttrs
                    .addFlashAttribute("mensaje", "Los campos precio y existencia deben ser mayor a 0")
                    .addFlashAttribute("clase", "danger");
            return "redirect:/productos/agregar";
        }
        productsService.guardar(producto);
        redirectAttrs
                .addFlashAttribute("mensaje", "Agregado correctamente")
                .addFlashAttribute("clase", "success");
        return "redirect:/productos/agregar";
    }
    
    @GetMapping(value = "/editar/{id}")
    public String editar(@PathVariable("id") Integer idProduct, Model model, RedirectAttributes redirectAttrs) {

            Products producto = null;

            if (idProduct > 0) {
                    producto = productsService.buscarPorId(idProduct);

                    if (producto == null) {
                            System.out.println("Error: El ID del cliente no existe!");
                            redirectAttrs.addFlashAttribute("error", "ATENCION: El ID del cliente no existe!");
                            return "redirect:/productos/agregar";
                    }
            }else {
                  
                    redirectAttrs.addFlashAttribute("error", "ATENCION: Error con el ID del cliente");
                    return "redirect:/productos/agregar";
            }

            model.addAttribute("titulo", "Formulario: Editar Cliente");
            model.addAttribute("producto", producto);

            return "productos/agregarProductos";
    }
    
    @GetMapping(value = "/delete/{id}")
    public String eliminar(@PathVariable("id") Integer idProduct, Model model, RedirectAttributes redirectAttrs) {

           Products producto = null;

            if (idProduct > 0) {
                    producto = productsService.buscarPorId(idProduct);

                    if (producto == null) {
                            
                            redirectAttrs.addFlashAttribute("error", "ATENCION: El ID del cliente no existe!");
                            return "redirect:/productos/mostrar/";
                    }
            }else {
                   
                    redirectAttrs.addFlashAttribute("error", "ATENCION: Error con el ID del Cliente!");
                    return "redirect:/productos/mostrar/";
            }		

            productsService.eliminar(idProduct);
           
            redirectAttrs.addFlashAttribute("warning", "Registro Eliminado con Exito!");

            return "redirect:/productos/mostrar/";
    }
    
}
