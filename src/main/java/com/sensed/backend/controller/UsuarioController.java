package com.sensed.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

// Si tenías otros imports de tus propios archivos (como UsuarioRepository), ponlos aquí arriba

@RestController
@RequestMapping("/api/auth") // Esta es la ruta base que busca tu game.js
@CrossOrigin(origins = {"https://sensed.es", "http://localhost:5500", "http://127.0.0.1:5500"}) // ¡La llave maestra!
public class UsuarioController {

    // ---------------------------------------------------------
    // AQUÍ DEBES PONER TU REPOSITORIO (Si lo tenías antes)
    // Ejemplo:
    // @Autowired
    // private UsuarioRepository usuarioRepository;
    // ---------------------------------------------------------


    @PostMapping("/registro") // Esta es la ruta final (/api/auth/registro)
    public ResponseEntity<?> registrarUsuario(@RequestBody Map<String, String> datosUsuario) {
        
        try {
            // Extraemos los datos que envía tu game.js
            String username = datosUsuario.get("username");
            String password = datosUsuario.get("password");

            // ---------------------------------------------------------
            // AQUÍ VA TU LÓGICA DE GUARDAR EN MYSQL
            // (Copia aquí lo que ya tuvieras hecho para guardar el usuario)
            // Ejemplo:
            // Usuario nuevoUsuario = new Usuario();
            // nuevoUsuario.setUsername(username);
            // nuevoUsuario.setPassword(password); 
            // usuarioRepository.save(nuevoUsuario);
            // ---------------------------------------------------------

            // Si todo va bien, le enviamos un 200 OK al frontend
            return ResponseEntity.ok().body(Map.of("mensaje", "Registro exitoso"));

        } catch (Exception e) {
            // Si algo explota en el servidor, le avisamos al frontend
            return ResponseEntity.internalServerError().body(Map.of("error", "Error al registrar el usuario"));
        }
    }

    // Si tienes otra función para el Login, iría justo aquí abajo
    // @PostMapping("/login")
    // public ResponseEntity<?> loginUsuario(...) { ... }
}