package com.freddy.rest.rest.Controllers;

import java.util.List;

import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.freddy.rest.rest.Entities.Producto;
import com.freddy.rest.rest.Repositories.ProductoRepository;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    // crea instancia para la conexion a una base de datos
    @AutoConfigureOrder
    private ProductoRepository productoRepository;

    // metodo get
    @GetMapping
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    // metodo get indivudual
    @GetMapping("/{id}")
    public Producto getProtuctoById(@PathVariable Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro el producto con el id: " + id));
    }

    // metodo post
    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoRepository.save(producto);
    }

    // metodo put
    @PutMapping("/{id}")
    public Producto putMethodName(@PathVariable Long id, @RequestBody Producto detallesProducto) {
        // obtienen el producto que se actualizara
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro el producto con el id; " + id));

        // seteo de nueva informacion
        producto.setNombre(detallesProducto.getNombre());
        producto.setPrecio(detallesProducto.getPrecio());

        // retorno de informacion actualizada
        return productoRepository.save(producto);
    }

    // metodo delete
    @DeleteMapping("/{id}")
    public String deleteProducto(@PathVariable Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no se encontro el producto con el id " + id));

        productoRepository.delete(producto);
        return "el producto con el id " + id + " a sido eliminado de manera correcta ";
    }
}
