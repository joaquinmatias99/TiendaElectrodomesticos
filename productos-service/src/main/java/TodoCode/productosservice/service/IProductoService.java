package TodoCode.productosservice.service;

import TodoCode.productosservice.model.Producto;

import java.util.List;

public interface IProductoService
{
    public Producto getProductoById(Long id);

    public List<Producto> getProductos();

    public void saveProducto(Producto producto);

    public void deleteProducto(Long id);

    public void editProducto(Long id, Producto producto);

    List<Long> getCodigoProductos();
}
