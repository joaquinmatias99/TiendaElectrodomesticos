package TodoCode.carritosservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ProductoDTO
{
    private Long codigo;
    private String nombre;
    private Double precio;
}

