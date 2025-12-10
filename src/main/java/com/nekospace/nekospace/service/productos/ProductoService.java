package com.nekospace.nekospace.service.productos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.nekospace.nekospace.model.productos.Producto;
import com.nekospace.nekospace.repository.productos.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
@SuppressWarnings("null")
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> findAll() {
        return productoRepository.findAll();
    }
    public Producto findById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto partialUpdate(Producto producto) {
        Producto existingProducto = productoRepository.findById(producto.getId()).orElse(null);
        if(existingProducto != null) {
            if(producto.getNombreProducto() != null) {
                existingProducto.setNombreProducto(producto.getNombreProducto());
            }
            if(producto.getDescripcionProducto() != null) {
                existingProducto.setDescripcionProducto(producto.getDescripcionProducto());
            }
            if(producto.getPrecio() != null) {
                existingProducto.setPrecio(producto.getPrecio());
            }
            if(producto.getMarca() != null) {
                existingProducto.setMarca(producto.getMarca());
            }
            if(producto.getOrigen() != null) {
                existingProducto.setOrigen(producto.getOrigen());
            }
            if(producto.getMaterial() != null) {
                existingProducto.setMaterial(producto.getMaterial());
            }
            if(producto.getCategoria() != null) {
                existingProducto.setCategoria(producto.getCategoria());
            }
            if(producto.getFranquicia() != null) {
                existingProducto.setFranquicia(producto.getFranquicia());
            }
            return productoRepository.save(existingProducto);
        }
        return null;
    }
    
    public void deleteById(Integer id) {
        productoRepository.deleteById(id);
    }

    public void deleteByCategoriaId(Integer categoriaId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            if(producto.getCategoria() != null && producto.getCategoria().getId().equals(categoriaId)) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }

    public void deleteByFranquiciaId(Integer franquiciaId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            if(producto.getFranquicia() != null && producto.getFranquicia().getId().equals(franquiciaId)) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }

    public void deleteByMarcaId(Integer marcaId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            if(producto.getMarca() != null && producto.getMarca().getId().equals(marcaId)) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }

    public void deleteByOrigenId(Integer origenId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            if(producto.getOrigen() != null && producto.getOrigen().getId().equals(origenId)) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }

    public void deleteByMaterialId(Integer materialId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            if (producto.getMaterial() != null && producto.getMaterial().getId().equals(materialId)) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }

    public void deleteByImagenId(Integer imagenId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            if (producto.getImagen() != null && producto.getImagen().getId().equals(imagenId)) {
                productoRepository.deleteById(producto.getId());
            }
        }
    }
    public void deleteByColoresId(Integer coloresId) {
        List<Producto> productos = productoRepository.findAll();
        for (Producto producto : productos) {
            for (var color : producto.getColores()) {
                if (color.getId().equals(coloresId)) {
                    productoRepository.deleteById(producto.getId());
                }
            }
        }
    }

    public List<Producto> findByCategoriaId(Integer categoriaId) {
        return productoRepository.findByCategoriaId(categoriaId);
    }

    public List<Producto> findByFranquiciaId(Integer franquiciaId) {
        return productoRepository.findByFranquiciaId(franquiciaId);
    }

    public List<Producto> findByMarcaId(Integer marcaId) {
        return productoRepository.findByMarcaId(marcaId);
    }

    public List<Producto> findByOrigenId(Integer origenId) {
        return productoRepository.findByOrigenId(origenId);
    }

    public List<Producto> findByMaterialId(Integer materialId) {
        return productoRepository.findByMaterialId(materialId);
    }

    public List<Producto> findByImagenId(Integer imagenId) {
        return productoRepository.findByImagen_Id(imagenId);
    }

    public List<Producto> findByColoresId(Integer coloresId) {
        return productoRepository.findByColoresId(coloresId);
    }
}
