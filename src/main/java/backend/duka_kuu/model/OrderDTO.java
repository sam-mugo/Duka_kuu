package backend.duka_kuu.model;

import javax.validation.constraints.NotNull;

import backend.duka_kuu.domain.OrderLineItem;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrderDTO {

    private Long orderId;

    @NotNull
    private String orderNumber;

    @NotNull
    private List<OrderLineItem> orderItems;

    @NotNull
    private Long user;

}
