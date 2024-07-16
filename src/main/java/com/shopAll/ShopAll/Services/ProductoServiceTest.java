package com.shopAll.ShopAll.Services;

import com.shopAll.ShopAll.Excepcion.NotFoundException;
import com.shopAll.ShopAll.Models.Producto;
import com.shopAll.ShopAll.Repository.ProductoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductoServiceTest {

    @InjectMocks
    private ProductoService productoService;

    @Mock
    private ProductoRepository productoRepository;

    public ProductoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto 1");
        when(productoRepository.save(producto)).thenReturn(producto);

        Producto result = productoService.guardar(producto);

        assertEquals("Producto 1", result.getNombre());
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    public void testObtenerProductoPorId() {
        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Producto 1");
        when(productoRepository.findById(1L)).thenReturn(Optional.of(producto));

        Producto result = productoService.obtenerPorId(1L);

        assertEquals("Producto 1", result.getNombre());
    }

    @Test
    public void testObtenerProductoPorIdNotFound() {
        when(productoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            productoService.obtenerPorId(1L);
        });
    }
}