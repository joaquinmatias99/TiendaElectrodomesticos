package TodoCode.carritosservice.service;

import TodoCode.carritosservice.model.Carrito;

import java.util.List;

public interface ICarritoService
{
    public Carrito getCarritoById(Long id);

    public List<Carrito> getCarritos();

    public void saveProducto(Long idProducto);

    public void deleteProducto(Long idProducto);
    public void saveCarrito(Carrito carrito);

    public void deleteCarrito(Long id);

    public void editCarrito(Long id, Carrito carrito);
}
