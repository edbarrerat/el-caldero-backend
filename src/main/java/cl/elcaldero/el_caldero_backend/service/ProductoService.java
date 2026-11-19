package cl.elcaldero.el_caldero_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.elcaldero.el_caldero_backend.model.Producto;
import cl.elcaldero.el_caldero_backend.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Integer id, Producto producto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);

        if (productoExistente == null) {
            return null;
        }

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setStock(producto.getStock());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setActivo(producto.getActivo());
        productoExistente.setIdExpansion(producto.getIdExpansion());
        productoExistente.setIdTipo(producto.getIdTipo());
        productoExistente.setIdMarca(producto.getIdMarca());

        return productoRepository.save(productoExistente);
    }

    public boolean eliminarProducto(Integer id) {
        if (productoRepository.existsById(id)) {
            productoRepository.deleteById(id);
            return true;
        }

        return false;
    }
}