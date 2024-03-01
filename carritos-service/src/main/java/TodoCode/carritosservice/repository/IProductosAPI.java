package TodoCode.carritosservice.repository;

import TodoCode.carritosservice.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="producto-service")
public interface IProductosAPI {
    @GetMapping("/productos/{codigo}")
    public ProductoDTO getProductoById(@PathVariable ("codigo") Long codigoProducto);

}
