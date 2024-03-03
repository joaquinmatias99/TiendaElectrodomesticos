package TodoCode.ventasservice.service;

import TodoCode.ventasservice.model.Venta;
import TodoCode.ventasservice.repository.ICarritosAPI;
import TodoCode.ventasservice.repository.IVentaRepository;
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
        //chequear si existe el carrito antes de agregarlo
            ventaRepo.save(venta);


    }

    @Override
    public void deleteVenta(Long id) {
        ventaRepo.deleteById(id);
    }

    @Override
    public void editVenta(Long id, Venta venta) {
        Venta ventaExistente = ventaRepo.findById(id).orElse(null);
        if (ventaExistente != null) {
            ventaExistente.setId(id);
            ventaExistente.setFecha(venta.getFecha());
            ventaExistente.setIdCarrito((venta.getIdCarrito()));

            this.saveVenta(ventaExistente);
        }
    }
}
