package com.example.proveedores.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import com.example.proveedores.model.dto.EmpresaDTO;
import com.example.proveedores.model.entitie.Proveedor;
import com.example.proveedores.model.request.ActualizarProveedores;
import com.example.proveedores.model.request.AgregarProveedores;
import com.example.proveedores.repository.ProveedorRepository;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private WebClient empresaWebClient; // WebClient apuntando al servicio de empresas

    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    public Proveedor obtenerProveedorPorID(int idProveedor) {
        Proveedor proveedor = proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedor == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Proveedor no encontrado.");
        }
        return proveedor;
    }

    public Proveedor agregarProveedor(AgregarProveedores agregarProveedores) {
        EmpresaDTO empresaDTO = null;
        try {
            empresaDTO = empresaWebClient.get()
                    .uri("/empresas/{id_empresa}", agregarProveedores.getIdEmpresa())
                    .retrieve()
                    .bodyToMono(EmpresaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Empresa del proveedor a ingresar no encontrada.");
        }

        if (empresaDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT agregarProveedor no ha encontrado la empresa ingresada (IF).");
        }

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor(agregarProveedores.getNombreProveedor());
        proveedor.setRutProveedor(agregarProveedores.getRutProveedor());
        proveedor.setIdEmpresa(agregarProveedores.getIdEmpresa());

        return proveedorRepository.save(proveedor);
    }

    public Proveedor actualizarProveedor(int idProveedor, ActualizarProveedores actualizarProveedores) {
        Proveedor proveedorExistente = proveedorRepository.findById(idProveedor).orElse(null);
        if (proveedorExistente == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarProveedor no ha encontrado el id del proveedor ingresado.");
        }

        EmpresaDTO empresaDTO = null;
        try {
            empresaDTO = empresaWebClient.get()
                    .uri("/empresas/{id_empresa}", actualizarProveedores.getIdEmpresa())
                    .retrieve()
                    .bodyToMono(EmpresaDTO.class)
                    .block();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Empresa del proveedor a actualizar no encontrada.");
        }

        if (empresaDTO == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT actualizarProveedor no ha encontrado la empresa ingresada (IF).");
        }

        proveedorExistente.setNombreProveedor(actualizarProveedores.getNombreProveedor());
        proveedorExistente.setRutProveedor(actualizarProveedores.getRutProveedor());
        proveedorExistente.setIdEmpresa(actualizarProveedores.getIdEmpresa());

        return proveedorRepository.save(proveedorExistente);
    }

    public String eliminarProveedor(int idProveedor) {
        if (proveedorRepository.existsById(idProveedor)) {
            proveedorRepository.deleteById(idProveedor);
            return "Proveedor eliminado correctamente";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "ENDPOINT eliminarProveedor no ha encontrado el id del proveedor ingresado (IF).");
        }
    }
}
