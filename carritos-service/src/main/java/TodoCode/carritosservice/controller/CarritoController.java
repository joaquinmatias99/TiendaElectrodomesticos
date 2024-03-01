package TodoCode.carritosservice.controller;

import TodoCode.carritosservice.dto.ProductoDTO;
import TodoCode.carritosservice.model.Carrito;
import TodoCode.carritosservice.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    @GetMapping("/{id}")
    public ResponseEntity<Carrito> getCarritoById(@PathVariable Long id) {
        Carrito carrito = carritoService.getCarritoById(id);
        if (carrito != null) {
            return ResponseEntity.ok(carrito);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Carrito>> getCarritos() {
        List<Carrito> carritos = carritoService.getCarritos();
        return ResponseEntity.ok(carritos);
    }

    @PostMapping("/agregar")
    public ResponseEntity<String> saveCarrito(@RequestBody Carrito carrito) {
        carritoService.saveCarrito(carrito);
        return ResponseEntity.ok("Carrito guardado exitosamente");
    }
//BORRAR ESTE
    @GetMapping("/getProductos")
    public ResponseEntity<ProductoDTO> getProducto(@RequestParam Long codigoProducto) {
        ProductoDTO producto = carritoService.getProducto((codigoProducto));

            return ResponseEntity.ok(producto);

    }

    @PutMapping("/agregar/producto")
    public Carrito addProductoCarrito(
            @RequestParam Long idCarrito,
            @RequestParam Long codigoProducto) {

        return carritoService.addProductoCarrito(idCarrito, codigoProducto);

    }
    @DeleteMapping("/eliminar/producto")
    public Carrito eliminarProductoCarrito(
            @RequestParam Long idCarrito,
            @RequestParam Long codigoProducto) {

        return carritoService.deleteProductoCarrito(idCarrito, codigoProducto);

    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> deleteCarrito(@PathVariable Long id) {
        if (carritoService.getCarritoById(id) != null) {
            carritoService.deleteCarrito(id);
            return ResponseEntity.ok("Carrito eliminado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El carrito no existe, no se puede eliminar");
        }
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<String> editCarrito(@PathVariable Long id, @RequestBody Carrito carrito) {
        if (carritoService.getCarritoById(id) != null) {
            carritoService.editCarrito(id, carrito);
            return ResponseEntity.ok("Carrito editado exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El carrito no existe, no se puede editar");
        }
    }

}
