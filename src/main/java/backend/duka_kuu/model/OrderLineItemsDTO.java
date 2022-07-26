package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import backend.duka_kuu.domain.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderLineItemsDTO {

    private Long id;


    private Long product;


    @NotNull
    @Size(max = 255)
    private Integer quantity;

//    @NotNull
//    private Long inventory;

//    @NotNull
//    private Long order;

}
