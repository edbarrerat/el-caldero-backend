package cl.elcaldero.el_caldero_backend.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data 
public class ProductoDTO {
    private Integer idProducto;

    @NotBlank(message = "El nombre del producto es obligatorio")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que cero")
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    private String descripcion;

    @NotNull(message = "El estado del producto es obligatorio")
    private Boolean activo;

    private OffsetDateTime fechaCreacion;

    @NotNull(message = "La expansion es obligatoria")
    private Integer idExpansion;

    @NotNull(message = "El tipo es obligatorio")
    private Integer idTipo;

    @NotNull(message = "La marca es obligatoria")
    private Integer idMarca;

}