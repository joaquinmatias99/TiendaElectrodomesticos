package TodoCode.productosservice.service;

import TodoCode.productosservice.model.Producto;
import TodoCode.productosservice.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoService implements IProductoService
{
@Autowired
private IProductoRepository productoRepo;

    @Override
    public Producto getProductoById(Long id) {
        return productoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Producto> getProductos() {
        return productoRepo.findAll();
    }

    @Override
    public void saveProducto(Producto producto) {
    productoRepo.save(producto);
    }

    @Override
    public void deleteProducto(Long id) {
    productoRepo.deleteById(id);
    }

    @Override
    public void editProducto(Long id, Producto producto) {
        Producto productoExistente = productoRepo.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setMarca(producto.getMarca());
            productoExistente.setNombre(producto.getNombre());
            productoExistente.setPrecio(producto.getPrecio());
            this.saveProducto(productoExistente);
        }
    }

}
