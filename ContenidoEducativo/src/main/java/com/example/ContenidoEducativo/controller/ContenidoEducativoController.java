package com.example.ContenidoEducativo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ContenidoEducativo.model.entitie.ContenidoEducativo;
import com.example.ContenidoEducativo.model.request.ActualizarContenidoEducativo;
import com.example.ContenidoEducativo.model.request.AgregarContenidoEducativo;
import com.example.ContenidoEducativo.service.ContenidoEducativoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contenidos-educativos")
public class ContenidoEducativoController {

    @Autowired
    private ContenidoEducativoService contenidoEducativoService;

    @GetMapping("")
    public List<ContenidoEducativo> obtenerTodosLosContenidoEducativos() {
        return contenidoEducativoService.obtenerTodosLosContenidos();
    }

    @GetMapping("/{idContenido}") // {idMarca} es parámetro de la URL
    public ContenidoEducativo obtenerContenidoPorId(@PathVariable int idContenido) { // Recibe el ID de la URL
        return contenidoEducativoService.obtenerContenidoPorID(idContenido); // Busca en BD y devuelve Marca
    }

    /*
     * ========================================
     * POST - CREAR NUEVA MARCA
     * ========================================
     * URL: POST /marca
     * Body: {"nombre_marca": "Tesla"}
     */
    @PostMapping("") // POST a /marca (ruta vacía)
    public ContenidoEducativo agregarContenidoEducativo(@Valid @RequestBody AgregarContenidoEducativo nuevaMarca) { // JSON
                                                                                                                    // →
                                                                                                                    // Objeto
        return contenidoEducativoService.agregarContenidoEducativo(nuevaMarca); // Guarda en BD y devuelve nueva Marca
    }

    /*
     * ========================================
     * PUT - ACTUALIZAR MARCA
     * ========================================
     * URL: PUT /marca
     * Body: {"id_marca": 5, "nombre_marca": "Tesla Motors"}
     */
    /*
     * ========================================
     * PUT - ACTUALIZAR CONTENIDO
     * ========================================
     */
    // 1. Agregamos el ID en la URL
    @PutMapping("/{idContenido}")
    public ContenidoEducativo actualizarContenidoEducativo(
            @Valid @RequestBody ActualizarContenidoEducativo actualizar,
            @PathVariable int idContenido) { // 2. Recibimos el ID

        // 3. Enviamos ambos al servicio (Objeto, ID)
        return contenidoEducativoService.actualizarContenidoEducativo(actualizar, idContenido);
    }

    /*
     * ========================================
     * DELETE - ELIMINAR MARCA
     * ========================================
     * URL: DELETE /marca/5 (elimina marca con id=5)
     */
    @DeleteMapping("/{idContenido}") // {idMarca} de la URL
    public String eliminarContenido(@PathVariable int idContenido) { // Recibe ID de URL
        return contenidoEducativoService.eliminarContenido(idContenido); // Elimina de BD, devuelve mensaje
    }
}

// ==================================================================================================================
// CODIGO SIN COMENTARIOS, SOLO SACAR EL COMENTARIOS GENERAL
// ==================================================================================================================

/*
 * package com.example.automora.controller;
 * 
 * import java.util.List;
 * 
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.web.bind.annotation.RequestMapping;
 * import org.springframework.web.bind.annotation.RestController;
 * 
 * import com.example.automora.models.entities.Marca;
 * import com.example.automora.models.requests.ActualizarMarca;
 * import com.example.automora.models.requests.AgregarMarca;
 * import com.example.automora.services.MarcaService;
 * 
 * import org.springframework.web.bind.annotation.DeleteMapping;
 * import org.springframework.web.bind.annotation.GetMapping;
 * import org.springframework.web.bind.annotation.PathVariable;
 * import org.springframework.web.bind.annotation.RequestBody;
 * import org.springframework.web.bind.annotation.PutMapping;
 * import org.springframework.web.bind.annotation.PostMapping;
 * 
 * @RequestMapping("marca")
 * 
 * @RestController
 * public class MarcaController {
 * 
 * @Autowired
 * private MarcaService marcaService;
 * 
 * @GetMapping("")
 * public List<Marca> obteneTodasLasMarcas(){
 * return marcaService.obteneTodasLasMarcas();
 * }
 * 
 * @GetMapping("/{idMarca}")
 * public Marca obtenerMarcaPorId(@PathVariable int idMarca) {
 * return marcaService.obtenerMarcaPorID(idMarca);
 * }
 * 
 * @PostMapping("")
 * public Marca agregarMarca(@RequestBody AgregarMarca nuevaMarca) {
 * return marcaService.agregMarca(nuevaMarca);
 * }
 * 
 * @PutMapping("")
 * public Marca actualizarMarca (@RequestBody ActualizarMarca actualizar){
 * return marcaService.actualizarMarca(actualizar);
 * }
 * 
 * @DeleteMapping("/{idMarca}")
 * public String eliminarMarca(@PathVariable int idMarca){
 * return marcaService.eliminarMarca(idMarca);
 * }
 * 
 * }
 */
