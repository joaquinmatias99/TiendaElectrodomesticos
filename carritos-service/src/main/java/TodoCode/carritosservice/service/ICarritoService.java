package TodoCode.carritosservice.service;

import TodoCode.carritosservice.dto.ProductoDTO;
import TodoCode.carritosservice.model.Carrito;


import java.util.List;

public interface ICarritoService
{
    public Carrito getCarritoById(Long idCarrito);

    public List<Carrito> getCarritos();

    public Carrito addProductoCarrito(Long idCarrito, Long codigoProducto);

    public ProductoDTO getProducto(Long codigoProducto);
    public Carrito deleteProductoCarrito(Long idCarrito, Long codigoProducto);
    public void saveCarrito(Carrito carrito);

    public void deleteCarrito(Long idCarrito);

    public void editCarrito(Long idCarrito, Carrito carrito);

    boolean existeCarrito(Long idCarrito);
}
