package com.springboot.clienteapp;

import com.springboot.clienteapp.models.entity.Roles;
import com.springboot.clienteapp.models.entity.Users;
import com.springboot.clienteapp.models.repository.RolesRepository;
import com.springboot.clienteapp.models.repository.UsersRepository;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootClienteappApplicationTests {

	/*@Test
	public void contextLoads() {
	}*/
        
        @Autowired
        private UsersRepository repoUser;
        
        @Autowired RolesRepository repoRol;
        
        @Autowired
        private BCryptPasswordEncoder passEncoder;
        
        @Test
        public void crearUsuario(){
            
            Users user = new Users();
            
            user.setId(1);
            user.setUsername("admin");
            user.setPassword(passEncoder.encode("admin"));
            user.setEnabled(1);
            
            Users test = repoUser.save(user);
            
            Roles rol = new Roles();
            rol.setId(1);
            rol.setUser_id(1);
            rol.setRol("ROLE_ADMIN");
            
            repoRol.save(rol);
            
            assertTrue(test.getPassword().equalsIgnoreCase(user.getPassword()));
        
        }

}
