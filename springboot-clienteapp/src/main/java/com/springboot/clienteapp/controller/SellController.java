/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.controller;

import com.springboot.clienteapp.models.entity.ProductSold;
import com.springboot.clienteapp.models.entity.Products;
import com.springboot.clienteapp.models.entity.Sell;
import com.springboot.clienteapp.models.service.IProductSoldService;
import com.springboot.clienteapp.models.service.IProductsService;
import com.springboot.clienteapp.models.service.ISellService;
import com.springboot.clienteapp.util.Util;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ecolina
 */

@Controller
@RequestMapping(path = "/vender")
public class SellController {
    
    @Autowired
    private IProductsService productsService;
    
    @Autowired
    private ISellService sellService;
    
    @Autowired
    private IProductSoldService productSoldService;
    
    @GetMapping(value = "/")
    public String interfazVender(Model model, HttpServletRequest request) {
        
        Products producto = new Products();
        
        model.addAttribute("producto", producto);
        
        float total = 0;
        ArrayList<ProductSell> car = this.obtenerCarrito(request);
        for (ProductSell p: car) {
            
            total += p.getTotal();
        }
        
        model.addAttribute("titulo","Vender Producto");
        model.addAttribute("total", total);
        
        return "vender/vender";
    }

    private ArrayList<ProductSell> obtenerCarrito(HttpServletRequest request) {
        ArrayList<ProductSell> car = (ArrayList<ProductSell>) request.getSession().getAttribute("carrito");
        if (car == null) {
            car = new ArrayList<>();
        }
        return car;
    }
    
    @PostMapping(value = "/agregar")
    public String addCar(@ModelAttribute Products producto, HttpServletRequest request, RedirectAttributes redirectAttrs) {
        ArrayList<ProductSell> car = this.obtenerCarrito(request);
        Products productFindByCode = productsService.buscarPorCodigoObjeto(producto.getCod());
        if (productFindByCode == null) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto con el código " + producto.getCod() + " no existe")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        if (productFindByCode.noExist()) {
            redirectAttrs
                    .addFlashAttribute("mensaje", "El producto está agotado")
                    .addFlashAttribute("clase", "warning");
            return "redirect:/vender/";
        }
        boolean encontrado = false;
        for (ProductSell productoParaVenderActual : car) {
            if (productoParaVenderActual.getCod().equals(productFindByCode.getCod())) {
                productoParaVenderActual.aumentarCantidad();
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            car.add(new ProductSell(productFindByCode.getName(), productFindByCode.getCod(), productFindByCode.getPrice(), productFindByCode.getStock(), productFindByCode.getId(), 1f));
        }
        this.saveCar(car, request);
        return "redirect:/vender/";
    }
    
    private void saveCar(ArrayList<ProductSell> carrito, HttpServletRequest request) {
        request.getSession().setAttribute("carrito", carrito);
    }
    
    @PostMapping(value = "/quitar/{indice}")
    public String quitarDelCarrito(@PathVariable int indice, HttpServletRequest request) {
        ArrayList<ProductSell> carrito = this.obtenerCarrito(request);
        if (carrito != null && carrito.size() > 0 && carrito.get(indice) != null) {
            carrito.remove(indice);
            this.saveCar(carrito, request);
        }
        return "redirect:/vender/";
    }
    
    @GetMapping(value = "/limpiar")
    public String quitSell(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        this.cleanCar(request);
        redirectAttrs
                .addFlashAttribute("mensaje", "Su venta ha sido Cancelada")
                .addFlashAttribute("clase", "info");
        return "redirect:/vender/";
    }
    
    private void cleanCar(HttpServletRequest request) {
        this.saveCar(new ArrayList<>(), request);
    }
    
    @PostMapping(value = "/terminar")
    public String sold(HttpServletRequest request, RedirectAttributes redirectAttrs) {
        
        Util util = new Util();
        
        ArrayList<ProductSell> car = this.obtenerCarrito(request);
        
        if (car == null || car.size() <= 0) {
            return "redirect:/vender/";
        }
        Sell s = new Sell();
        
        s.setDate(util.obtenerFechaYHoraActual());
        
        Sell v = sellService.guardar(s);
       
        for (ProductSell productoParaVender : car) {
           
            Products p = productsService.buscarPorId(productoParaVender.getId());
            
            if (p == null) 
                continue; 
            
            p.restExist(productoParaVender.getCantidad());
           
            productsService.guardar(p);
            
            ProductSold productoVendido = new ProductSold(productoParaVender.getCantidad(), productoParaVender.getPrice(), productoParaVender.getName(), productoParaVender.getCod(), v);
            
            productSoldService.guardar(productoVendido);
           
        }

        
        this.cleanCar(request);
        
        redirectAttrs
                .addFlashAttribute("mensaje", "Su venta ha sido Concretada")
                .addFlashAttribute("clase", "success");
        return "redirect:/vender/";
    }
}
