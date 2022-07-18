package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String orderNumber;

    @NotNull
    private Long customer;

}
