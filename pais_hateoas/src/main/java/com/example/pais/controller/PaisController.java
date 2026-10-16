package com.example.pais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.pais.model.entities.Pais;
import com.example.pais.model.request.ActualizarPais;
import com.example.pais.model.request.AgregarPais;
import com.example.pais.service.PaisService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping("")
    public List<Pais> obtenerTodosLosPaises() {
        return paisService.obtenerTodosLosPaises();
    }

    @GetMapping("/{idPais}")
    public EntityModel<Pais> obtenerPaisporId(@PathVariable int idPais) {
        Pais pais = paisService.obtenerPaisPorID(idPais);
        Link obtenerTodosLink = linkTo(methodOn(PaisController.class).obtenerTodosLosPaises())
                .withRel("Obtener-todos-los-paises.");
        Link EliminarPais = linkTo(PaisController.class).slash(idPais).withRel("Eliminar-Pais-por-ID.");
        return EntityModel.of(pais, obtenerTodosLink, EliminarPais);

    }

    @PostMapping("")
    public Pais agregarPais(@Valid @RequestBody AgregarPais agregarpais) {
        return paisService.agregarPais(agregarpais);
    }

    @PutMapping("{idPais}")
    public Pais actualizarPais(@Valid @PathVariable int idPais, @RequestBody ActualizarPais actualizarPais) {
        return paisService.actualizarPais(actualizarPais, idPais);
    }

    @DeleteMapping("/{idPais}")
    public String eliminarPais(@PathVariable int idPais) {
        return paisService.eliminarPais(idPais);
    }

}
