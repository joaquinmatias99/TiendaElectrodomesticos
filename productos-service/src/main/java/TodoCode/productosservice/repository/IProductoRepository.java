package TodoCode.productosservice.repository;

import TodoCode.productosservice.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Long, Producto> {
}
