package TodoCode.carritosservice.service;

import TodoCode.carritosservice.dto.ProductoDTO;
import TodoCode.carritosservice.model.Carrito;
import TodoCode.carritosservice.model.Producto;
import TodoCode.carritosservice.repository.ICarritoRepository;
import TodoCode.carritosservice.repository.IProductosAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService implements ICarritoService {
    @Autowired
    private ICarritoRepository carritoRepo;
    @Autowired
    private IProductosAPI productosAPI;

    @Override
    public Carrito getCarritoById(Long id) {
        return carritoRepo.findById(id).orElse(null);
    }

    @Override
    public List<Carrito> getCarritos() {
        return carritoRepo.findAll();
    }


    public Carrito addProductoCarrito(Long idCarrito, Long codigoProducto) {
        Carrito carrito = this.getCarritoById(idCarrito);
        ProductoDTO producto = productosAPI.getProductoById(codigoProducto);

        if (carrito != null && producto != null) {
            carrito.getListaProductos().add(producto.getCodigo());

            double precioTotal = carrito.getPrecioTotal() != null ? carrito.getPrecioTotal() : 0.0;
            precioTotal += producto.getPrecio();

            carrito.setPrecioTotal(precioTotal);

            this.editCarrito(idCarrito, carrito);
            return carrito;
        } else {
            return null;
        }
    }


    @Override
    public ProductoDTO getProducto(Long codigoProducto) {
        return productosAPI.getProductoById(codigoProducto);

    }


    @Override
    public Carrito deleteProductoCarrito(Long idCarrito, Long codigoProducto) {
        Carrito carrito = this.getCarritoById(idCarrito);
        ProductoDTO producto = productosAPI.getProductoById(codigoProducto);

        if (carrito != null && producto != null) {
            List<Long> listaProductos = carrito.getListaProductos();

            for (int i=0; i<listaProductos.size();i++) {
                if (listaProductos.get(i).equals(codigoProducto))
                {
                    listaProductos.remove(i);
                    carrito.setPrecioTotal(carrito.getPrecioTotal() - producto.getPrecio());
                    carrito.setListaProductos(listaProductos);

                    this.editCarrito(idCarrito, carrito);
                    return carrito;
                }
            }


        } else {
            return null;
        }


        return null;
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
            carritoExistente.setListaProductos((carrito.getListaProductos()));
            this.saveCarrito(carritoExistente);
        }
    }
}
