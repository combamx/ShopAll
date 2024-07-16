package com.shopAll.ShopAll.Controller;

import com.shopAll.ShopAll.Models.Producto;
import com.shopAll.ShopAll.Services.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ProductoControllerTest {

    @InjectMocks
    private ProductoController productoController;

    @Mock
    private ProductoService productoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testObtenerTodos() {
        Producto producto1 = new Producto();
        producto1.setNombre("Producto 1");
        Producto producto2 = new Producto();
        producto2.setNombre("Producto 2");

        when(productoService.obtenerTodos()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoController.obtenerTodos();

        assertEquals(2, productos.size());
        assertEquals("Producto 1", productos.get(0).getNombre());
        assertEquals("Producto 2", productos.get(1).getNombre());
    }

    @Test
    public void testGuardarProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto 1");

        when(productoService.guardar(producto)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.guardar(producto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Producto 1", response.getBody().getNombre());
    }

    @Test
    public void testActualizarProducto() {
        Producto producto = new Producto();
        producto.setNombre("Producto 1");

        when(productoService.actualizar(1L, producto)).thenReturn(producto);

        ResponseEntity<Producto> response = productoController.actualizar(1L, producto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Producto 1", response.getBody().getNombre());
    }

    @Test
    public void testEliminarProducto() {
        ResponseEntity<Void> response = productoController.eliminar(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}