/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Sell;
import java.util.List;

/**
 *
 * @author ecolina
 */
public interface ISellService {
    
    public Sell guardar(Sell sell);
    
    public List<Sell> mostrarHistorico();
    
}
