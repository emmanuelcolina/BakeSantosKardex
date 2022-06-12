/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.controller;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ecolina
 */

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String login(@RequestParam(value="error", required=false) String error,
            @RequestParam(value="logout", required=false) String logout,
            Model model, Principal principal, RedirectAttributes attribute){
        
        if(error!=null){
            model.addAttribute("error", "ERROR DE ACCESO: Usuario y/o contraseña son incorrectos");
        }
        //SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal != null){
            attribute.addFlashAttribute("warning", "ATENCION: Us. ya ha iniciado sesion previamente");
            return "redirect:/index";
        }
        
        if(logout != null){
            model.addAttribute("success", "ATENCION: Ha finalizado sesion con exito");
        }
        
        return "login";
    }
    
}
