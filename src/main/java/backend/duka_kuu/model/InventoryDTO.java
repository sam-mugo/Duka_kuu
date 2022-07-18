package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class InventoryDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String skuCode;

    @NotNull
    @Size(max = 255)
    private String quantity;

    private Double storePrice;

    private Double salePrice;

    private Double weight;

}
