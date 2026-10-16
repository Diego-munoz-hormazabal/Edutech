
package com.example.pais.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.pais.model.entities.Pais;
import com.example.pais.model.request.ActualizarPais;
import com.example.pais.model.request.AgregarPais;
import com.example.pais.repository.PaisRepository;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;


    //LISTAR TODOS LOS VEHICULOS
    public List<Pais> obtenerTodosLosPaises(){
        return paisRepository.findAll();
    }

     //OBTENER VEHICULO POR ID
    public Pais obtenerPaisPorID(int idPais){
        Pais pais = paisRepository.findById(idPais).orElse(null);
        if (pais == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pais no encontrado.");
        }
        return pais;
    }

    //AGREGAR VEHICULO
     public Pais agregarPais(AgregarPais nuevoPais){
        Pais p = new Pais();
        p.setNombrePais(nuevoPais.getNombrePais());
        return paisRepository.save(p); 
    }

    //ACTUALIZAR Pais POR ID
    public Pais actualizarPais(ActualizarPais actualizarPais, int id_pais){
        //validar id_pais
        Pais paisexistente = paisRepository.findById(id_pais).orElse(null);
        if (paisexistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ENDPOINT actualizar Pais no ha encontrado el id ingresado.");
        }
        //validar id_pais

        paisexistente.setNombrePais(actualizarPais.getNombrePais());
      
        return paisRepository.save(paisexistente);

    }



    //ELIMINAR VEHICULO POR ID
    public String eliminarPais(int idPais){
        if (paisRepository.existsById(idPais)) {
            paisRepository.deleteById(idPais);
            return "Pais eliminado correctamente";
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"ENDPOINT eliminarPais no ha encontrado.");
        } 
    }



}


   
