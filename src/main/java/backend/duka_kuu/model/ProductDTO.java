package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String productName;

    @NotNull
    private String productDescription;

    @NotNull
    private String productImage;

    @NotNull
    private Double productPrice;

    private Integer productQuantity;

    @NotNull
    private Long category;

    @NotNull
    private Long subcategory;

    @NotNull
    private Long inventory;

}
