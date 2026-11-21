package cl.elcaldero.el_caldero_backend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.elcaldero.el_caldero_backend.dto.ProductoDTO;
import cl.elcaldero.el_caldero_backend.model.Producto;
import cl.elcaldero.el_caldero_backend.repository.ProductoRepository;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        List<ProductoDTO> productosDTO = new ArrayList<>();

        for (Producto producto : productos) {
            ProductoDTO dto = new ProductoDTO();

            dto.setIdProducto(producto.getIdProducto());
            dto.setNombre(producto.getNombre());
            dto.setPrecio(producto.getPrecio());
            dto.setStock(producto.getStock());
            dto.setDescripcion(producto.getDescripcion());
            dto.setActivo(producto.getActivo());
            dto.setFechaCreacion(producto.getFechaCreacion());
            dto.setIdExpansion(producto.getIdExpansion());
            dto.setIdTipo(producto.getIdTipo());
            dto.setIdMarca(producto.getIdMarca());

            productosDTO.add(dto);
        }

        return productosDTO;
    }

    public ProductoDTO guardarProducto(ProductoDTO dto) {
        Producto producto = new Producto();

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setDescripcion(dto.getDescripcion());
        producto.setActivo(dto.getActivo());
        producto.setIdExpansion(dto.getIdExpansion());
        producto.setIdTipo(dto.getIdTipo());
        producto.setIdMarca(dto.getIdMarca());

        Producto productoGuardado = productoRepository.save(producto);

        ProductoDTO respuesta = new ProductoDTO();

        respuesta.setIdProducto(productoGuardado.getIdProducto());
        respuesta.setNombre(productoGuardado.getNombre());
        respuesta.setPrecio(productoGuardado.getPrecio());
        respuesta.setStock(productoGuardado.getStock());
        respuesta.setDescripcion(productoGuardado.getDescripcion());
        respuesta.setActivo(productoGuardado.getActivo());
        respuesta.setFechaCreacion(productoGuardado.getFechaCreacion());
        respuesta.setIdExpansion(productoGuardado.getIdExpansion());
        respuesta.setIdTipo(productoGuardado.getIdTipo());
        respuesta.setIdMarca(productoGuardado.getIdMarca());

        return respuesta;
    }

    public ProductoDTO actualizarProducto(Integer id, ProductoDTO dto) {
        Producto producto = productoRepository.findById(id).orElse(null);

        if (producto == null) {
            return null;
        }

        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setDescripcion(dto.getDescripcion());
        producto.setActivo(dto.getActivo());
        producto.setIdExpansion(dto.getIdExpansion());
        producto.setIdTipo(dto.getIdTipo());
        producto.setIdMarca(dto.getIdMarca());

        Producto productoActualizado = productoRepository.save(producto);

        ProductoDTO respuesta = new ProductoDTO();

        respuesta.setIdProducto(productoActualizado.getIdProducto());
        respuesta.setNombre(productoActualizado.getNombre());
        respuesta.setPrecio(productoActualizado.getPrecio());
        respuesta.setStock(productoActualizado.getStock());
        respuesta.setDescripcion(productoActualizado.getDescripcion());
        respuesta.setActivo(productoActualizado.getActivo());
        respuesta.setFechaCreacion(productoActualizado.getFechaCreacion());
        respuesta.setIdExpansion(productoActualizado.getIdExpansion());
        respuesta.setIdTipo(productoActualizado.getIdTipo());
        respuesta.setIdMarca(productoActualizado.getIdMarca());

        return respuesta;
    }

    public boolean eliminarProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            return false;
        }

        productoRepository.deleteById(id);
        return true;
    }
}
