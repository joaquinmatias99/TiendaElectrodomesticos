package TodoCode.productosservice.controller;

import TodoCode.productosservice.model.Producto;
import TodoCode.productosservice.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/{id}")
    public Producto getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoService.getProductos();
    }

    @PostMapping
    public void saveProducto(@RequestBody Producto producto) {
        productoService.saveProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void deleteProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
    }

    @PutMapping("/{id}")
    public void editProducto(@PathVariable Long id, @RequestBody Producto producto) {
        productoService.editProducto(id, producto);
    }
}
