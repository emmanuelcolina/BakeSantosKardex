/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.springboot.clienteapp.models.service;

import com.springboot.clienteapp.models.entity.Sell;
import com.springboot.clienteapp.models.repository.SellRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ecolina
 */

@Service
public class SellServiceImpl implements ISellService{
    @Autowired
    private SellRepository sellRepository;
    
    @Override
    public Sell guardar(Sell sell) {
        return sellRepository.save(sell);
    }
    
    @Override
    public List<Sell> mostrarHistorico() {
         return sellRepository.findAll();
    }
    
}
