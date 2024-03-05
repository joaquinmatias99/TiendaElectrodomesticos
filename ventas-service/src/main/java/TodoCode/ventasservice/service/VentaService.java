package TodoCode.ventasservice.service;

import TodoCode.ventasservice.model.Venta;
import TodoCode.ventasservice.repository.ICarritosAPI;
import TodoCode.ventasservice.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService implements IVentaService{
    @Autowired
    private IVentaRepository ventaRepo;

    @Autowired
    private ICarritosAPI carritosAPI;
    @Override
    public Venta getVentaById(Long id) {
        return ventaRepo.findById(id).orElse(null);
    }

    @Override
    public List<Venta> getVentas() {
        return ventaRepo.findAll();
    }

    @Override
    public void saveVenta(Venta venta) {
         if(existeCarrito(venta.getIdCarrito()))
         {
            ventaRepo.save(venta);
         }
         else {
             throw new RuntimeException("No existe el carrito asociado a la venta");
         }


    }
    @CircuitBreaker(name="carritos-service")
    @Retry(name="carritos-service")
    private boolean existeCarrito(Long idCarrito) {
        return carritosAPI.existeCarrito(idCarrito);
    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public void editVenta(Long id, Venta venta) {
        Venta ventaExistente = ventaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la venta solicitada con id: "+id));

        Long idCarrito = venta.getIdCarrito();
        if (idCarrito == null || !existeCarrito(idCarrito)) {
            throw new IllegalArgumentException("No existe el carrito con id: "+idCarrito);
        }

        ventaExistente.setFecha(venta.getFecha());
        ventaExistente.setIdCarrito(idCarrito);

        this.saveVenta(ventaExistente);
    }

}
