package com.sensed.backend.controller;

import com.sensed.backend.model.Usuario;
import com.sensed.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/registro")
    public ResponseEntity<String> registrarUsuario(@RequestBody Usuario nuevoUsuario) {
        
        // 1. Comprobamos si el nombre de usuario ya está pillado
        if (usuarioRepository.findByUsername(nuevoUsuario.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Error: El usuario ya existe");
        }
        
        // 2. Guardamos el usuario en MySQL usando el Repositorio
        usuarioRepository.save(nuevoUsuario);
        
        // 3. Le respondemos a tu JavaScript que todo ha ido bien
        return ResponseEntity.ok("Usuario registrado correctamente");
    }

    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody Usuario loginUsuario) {
        
        // 1. Buscamos al usuario en la base de datos
        Optional<Usuario> usuarioDb = usuarioRepository.findByUsername(loginUsuario.getUsername());
        
        // 2. Comprobamos si existe y si la contraseña coincide
        if (usuarioDb.isPresent() && usuarioDb.get().getPassword().equals(loginUsuario.getPassword())) {
            return ResponseEntity.ok("Login exitoso. ¡Bienvenido!");
        } else {
            // Error 401 significa "No autorizado"
            return ResponseEntity.status(401).body("Error: Usuario o contraseña incorrectos");
        }
    }
}