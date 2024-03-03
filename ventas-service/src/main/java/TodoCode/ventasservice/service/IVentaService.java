package TodoCode.ventasservice.service;

import TodoCode.ventasservice.model.Venta;

import java.util.List;

public interface IVentaService {

        public Venta getVentaById(Long id);

        public List<Venta> getVentas();

        public void saveVenta(Venta venta);

        public void deleteVenta(Long id);

        public void editVenta(Long id, Venta venta);
    }


