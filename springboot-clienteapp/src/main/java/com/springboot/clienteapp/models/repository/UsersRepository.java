/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.springboot.clienteapp.models.repository;

import com.springboot.clienteapp.models.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ecolina
 */
public interface UsersRepository extends JpaRepository <Users, Integer>{
    
}
