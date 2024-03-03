package TodoCode.ventasservice.repository;

import TodoCode.ventasservice.dto.CarritoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="carritos-service")
public interface ICarritosAPI
{
    @GetMapping("/carritos/{idCarrito}")
    public CarritoDTO getCarritoById(@PathVariable("idCarrito") Long idCarrito);

    @GetMapping("/carritos/existe/{idCarrito}")
    public boolean existeCarrito(@PathVariable Long idCarrito);
}
