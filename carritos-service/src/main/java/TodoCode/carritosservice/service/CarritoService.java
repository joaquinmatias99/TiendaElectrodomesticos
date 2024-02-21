package TodoCode.carritosservice.service;

import TodoCode.carritosservice.model.Carrito;
import TodoCode.carritosservice.repository.ICarritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarritoService implements ICarritoService
{
    @Autowired
    private ICarritoRepository carritoRepo;
    @Override
    public Carrito getCarritoById(Long id) {
        return carritoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Carrito> getCarritos() {
        return carritoRepo.findAll();
    }

    @Override
    public void saveProducto(Long idProducto) {
        //falta implementar
    }

    @Override
    public void deleteProducto(Long idProducto) {
    //falta implementar
    }

    @Override
    public void saveCarrito(Carrito carrito) {
        carritoRepo.save(carrito);
    }

    @Override
    public void deleteCarrito(Long id) {
        carritoRepo.deleteById(id);
    }

    @Override
    public void editCarrito(Long id, Carrito carrito) {
        Carrito carritoExistente = carritoRepo.findById(id).orElse(null);
        if (carritoExistente != null) {
            carritoExistente.setPrecioTotal((carrito.getPrecioTotal()));
            carritoExistente.setListaProductosIds((carrito.getListaProductosIds()));
            this.saveCarrito(carritoExistente);
        }
    }
}
