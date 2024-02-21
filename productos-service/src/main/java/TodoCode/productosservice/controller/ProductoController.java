package TodoCode.productosservice.controller;

import TodoCode.productosservice.model.Producto;
import TodoCode.productosservice.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
        Producto producto = productoService.getProductoById(id);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
        List<Producto> productos = productoService.getProductos();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<String> saveProducto(@RequestBody Producto producto) {
        productoService.saveProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Producto guardado exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long id) {
        if (productoService.getProductoById(id) != null) {
            productoService.deleteProducto(id);
            return ResponseEntity.ok("Producto eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe, no se puede eliminar");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> editProducto(@PathVariable Long id, @RequestBody Producto producto) {
        if (productoService.getProductoById(id) != null) {
            productoService.editProducto(id, producto);
            return ResponseEntity.ok("Producto editado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El producto no existe, no se puede editar");
        }
    }


}
