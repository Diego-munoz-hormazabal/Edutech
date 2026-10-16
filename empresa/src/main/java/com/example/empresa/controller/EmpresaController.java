/*
MarcaController.java es un archivo que funciona como APIRest, es decir recibe las peticiones HTTP.
Cada @XxXMapping, es un endpoint, ya que realiza una función en especiFico, en este caso tenemos 5 endpoint, lo cual forma
nuestra API.
*/

package com.example.empresa.controller;

/*
 * ========================================
 * IMPORTS BÁSICOS DE SPRING WEB
 * ========================================
 */
import java.util.List; // Para listas de marcas

/*
 * IMPORTS PARA INYECCIÓN DE DEPENDENCIAS Y ANOTACIONES WEB
 */
import org.springframework.beans.factory.annotation.Autowired; // Inyecta servicios automáticamente
import org.springframework.web.bind.annotation.RequestMapping; // Define ruta base del controlador
import org.springframework.web.bind.annotation.RestController; // Controlador REST que devuelve JSON

/*
 * IMPORTS DE MODELOS Y SERVICIOS
 */
import com.example.empresa.model.entitie.Empresa; // Entidad Marca (tabla BD)
import com.example.empresa.model.request.ActualizarEmpresa; // DTO para actualizar marca
import com.example.empresa.model.request.AgregarEmpresa; // DTO para crear nueva marca
import com.example.empresa.service.EmpresaService; // Lógica de negocio de marcas

import jakarta.validation.Valid;

/*
 * IMPORTS DE ANOTACIONES HTTP (CRUD)
 */
import org.springframework.web.bind.annotation.DeleteMapping; // DELETE HTTP
import org.springframework.web.bind.annotation.GetMapping; // GET HTTP
import org.springframework.web.bind.annotation.PathVariable; // Parámetros en URL
import org.springframework.web.bind.annotation.RequestBody; // Datos JSON del body
import org.springframework.web.bind.annotation.PutMapping; // PUT HTTP
import org.springframework.web.bind.annotation.PostMapping; // POST HTTP

@RestController
@RequestMapping("empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("")
    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaService.obtenerTodasLasEmpresas();
    }

    @GetMapping("/{idEmpresa}")
    public Empresa obtenerEmpresaPorId(@PathVariable int idEmpresa) {
        return empresaService.obtenerEmpresaPorID(idEmpresa);
    }

    @PostMapping("")
    public Empresa agregarEmpresa(@Valid @RequestBody AgregarEmpresa nuevaEmpresa) {
        return empresaService.agregarEmpresa(nuevaEmpresa);
    }

    @PutMapping("/{idEmpresa}")
    public Empresa actualizarEmpresa(
            @PathVariable int idEmpresa,
            @Valid @RequestBody ActualizarEmpresa actualizar) {
        // Debes enviar primero los datos y luego el ID (según como definiste tu
        // servicio en el paso anterior)
        return empresaService.actualizarEmpresa(actualizar, idEmpresa);
    }

    @DeleteMapping("/{idEmpresa}")
    public String eliminarEmpresa(@PathVariable int idEmpresa) {
        return empresaService.eliminarEmpresa(idEmpresa);
    }
}
