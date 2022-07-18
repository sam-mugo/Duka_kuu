package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Getter
@Setter
public class OrderDTO {

    private Long orderId;

    @NotNull
    @Size(max = 255)
    private UUID orderNumber;

    @NotNull
    private Long customer;

}
