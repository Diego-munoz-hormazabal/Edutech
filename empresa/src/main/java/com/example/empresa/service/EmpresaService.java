/*
EmpresaService.java es un archivo que contiene los metodos principales de acciones, es decir la logica del negocio,
y los ejecuta segun la llamada del EmpresaController
*/

package com.example.empresa.service;

/*
 * ========================================
 * IMPORTS PARA SERVICIO DE EMPRESAS
 * ========================================
 */
import java.util.List; // Para listas de empresas

/*
 * IMPORTS DE SPRING FRAMEWORK
 */
import org.springframework.beans.factory.annotation.Autowired; // Inyección de dependencias
import org.springframework.http.HttpStatus; // Códigos de estado HTTP
import org.springframework.stereotype.Service; // Marca como servicio de Spring
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException; // Excepciones HTTP personalizadas

import com.example.empresa.model.dto.DireccionDTO;

/*
 * IMPORTS DE MODELOS Y REPOSITORY
 */
import com.example.empresa.model.entitie.Empresa; // Entidad Empresa (tabla BD)
import com.example.empresa.model.request.ActualizarEmpresa;
import com.example.empresa.model.request.AgregarEmpresa; // DTO para crear
import com.example.empresa.repository.EmpresaRepository; // Acceso a base de datos

/*
 * ========================================
 * SERVICIO DE NEGOCIO - LÓGICA EMPRESAS
 * ========================================
 * Capa intermedia entre Controller y Repository
 * Contiene la lógica de negocio y validaciones
 */
@Service // Spring gestiona este servicio como bean
public class EmpresaService {

    /*
     * ========================================
     * INYECCIÓN DEL REPOSITORY
     * ========================================
     */
    @Autowired // Spring inyecta EmpresaRepository automáticamente
    private EmpresaRepository empresaRepository; // Conexión directa con tabla "empresa"

    @Autowired
    private WebClient direccionWebClientConfig;

    /*
     * ========================================
     * GET ALL - OBTENER TODAS LAS EMPRESAS
     * ========================================
     */
    public List<Empresa> obtenerTodasLasEmpresas() {
        return empresaRepository.findAll(); // Llama al método automático del Repository
    }

    /*
     * ========================================
     * GET BY ID - BUSCAR EMPRESA POR ID
     * ========================================
     */
    public Empresa obtenerEmpresaPorID(int idEmpresa) {
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null); // Busca o null
        if (empresa == null) {
            /*
             * LANZA ERROR HTTP 404 si no existe
             * El Controller recibirá este error automáticamente
             */
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada.");
        }
        return empresa; // Devuelve la empresa encontrada
    }

    /*
     * ========================================
     * POST - CREAR NUEVA EMPRESA
     * ========================================
     */
    public Empresa agregarEmpresa(AgregarEmpresa nuevaEmpresa) {
        DireccionDTO direccionDto = null;

        // 1. Validar la FK llamando al microservicio externo
        try {
            direccionDto = direccionWebClientConfig.get()
                    // Asumo que la URL del otro servicio es /direccion/{id} o /ciudad/{id}
                    .uri("/direccion/{id_direccion}", nuevaEmpresa.getIdDireccion())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block(); // Esperamos la respuesta (síncrono)
        } catch (Exception e) {
            // Si el otro servicio da error o no responde (404, 500, etc.)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La dirección/ciudad con ID "
                    + nuevaEmpresa.getIdDireccion() + " no fue encontrada en el servicio externo.");
        }

        // 2. Validación extra de nulidad (igual que tu ejemplo)
        if (direccionDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El servicio de direcciones devolvió NULL.");
        }

        // 3. Crear y guardar la Empresa localmente
        Empresa empresaNueva = new Empresa();
        empresaNueva.setNombreEmpresa(nuevaEmpresa.getNombreEmpresa());
        empresaNueva.setRutEmpresa(nuevaEmpresa.getRutEmpresa());

        // Guardamos el ID validado
        empresaNueva.setIdDireccion(nuevaEmpresa.getIdDireccion());

        return empresaRepository.save(empresaNueva);
    }

    /*
     * ========================================
     * DELETE - ELIMINAR EMPRESA
     * ========================================
     */
    public String eliminarEmpresa(int idEmpresa) {
        if (empresaRepository.existsById(idEmpresa)) { // Verifica si existe ANTES de borrar
            empresaRepository.deleteById(idEmpresa); // Borra de BD (DELETE SQL)
            return "Empresa Eliminada correctamente."; // Mensaje de éxito
        } else {
            /*
             * ERROR 404 si no existe la empresa
             * Cliente recibe JSON con error
             */
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa no encontrada.");

        }
    }

    // ACTUALIZAR VEHICULO POR ID
    public Empresa actualizarEmpresa(ActualizarEmpresa actualizarEmpresa, int idEmpresa) {

        // 1. Validar que la EMPRESA existe
        // (Nota: Cambié el nombre de la variable 'vehiculoexistente' a
        // 'empresaExistente' para que sea más claro)
        Empresa empresaExistente = empresaRepository.findById(idEmpresa).orElse(null);

        if (empresaExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarEmpresa no ha encontrado el id de la empresa ingresado.");
        }

        // 2. Validar que la DIRECCIÓN existe en el microservicio externo
        DireccionDTO direccionDto = null;
        try {
            direccionDto = direccionWebClientConfig.get()
                    .uri("/direccion/{id_direccion}", actualizarEmpresa.getIdDireccion())
                    .retrieve()
                    .bodyToMono(DireccionDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La dirección a vincular no fue encontrada.");
        }

        if (direccionDto == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarEmpresa: La dirección retornó NULL.");
        }

        /*
         * * ==========================================
         * ESTO ES LO QUE TE FALTABA
         * ==========================================
         */

        // 3. Actualizar los campos del objeto existente con los datos nuevos
        empresaExistente.setNombreEmpresa(actualizarEmpresa.getNombreEmpresa());
        empresaExistente.setRutEmpresa(actualizarEmpresa.getRutEmpresa());
        empresaExistente.setIdDireccion(actualizarEmpresa.getIdDireccion());

        // 4. Guardar los cambios en la base de datos y RETORNAR el objeto
        return empresaRepository.save(empresaExistente);
    }

}
