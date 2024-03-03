package TodoCode.ventasservice.controller;

import TodoCode.ventasservice.model.Venta;
import TodoCode.ventasservice.service.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentaController  {

    @Autowired
    private IVentaService ventaService;

    @GetMapping("/{idVenta}")
    public ResponseEntity<Venta> getVentaById(@PathVariable Long idVenta) {
        Venta venta = ventaService.getVentaById(idVenta);
        if (venta != null) {
            return ResponseEntity.ok(venta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Venta>> getVentas() {
        List<Venta> ventas = ventaService.getVentas();
        return ResponseEntity.ok(ventas);
    }

    @PostMapping
    public ResponseEntity<String> saveVenta(@RequestBody Venta venta) {
        try {
            ventaService.saveVenta(venta);
            return ResponseEntity.status(HttpStatus.CREATED).body("Venta guardada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la venta: " + e.getMessage());
        }
    }


    @DeleteMapping("/{idVenta}")
    public ResponseEntity<String> deleteVenta(@PathVariable Long idVenta) {
        if (ventaService.getVentaById(idVenta) != null) {
            ventaService.deleteVenta(idVenta);
            return ResponseEntity.ok("Venta eliminada exitosamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El Venta no existe, no se puede eliminar");
        }
    }
    @PutMapping("/{idVenta}")
    public ResponseEntity<String> editVenta(@PathVariable Long idVenta, @RequestBody Venta venta) {
        try {
            ventaService.editVenta(idVenta, venta);
            return ResponseEntity.ok("Venta editada exitosamente");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error en editar la venta: " + e.getMessage());
        }  catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en editar la venta: " + e.getMessage());
        }
    }



}