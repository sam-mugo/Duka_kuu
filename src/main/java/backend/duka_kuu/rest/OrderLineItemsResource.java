package backend.duka_kuu.rest;

import backend.duka_kuu.model.OrderLineItemsDTO;
import backend.duka_kuu.service.OrderLineItemsService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/orderLineItemss", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderLineItemsResource {

    private final OrderLineItemsService orderLineItemsService;

    public OrderLineItemsResource(final OrderLineItemsService orderLineItemsService) {
        this.orderLineItemsService = orderLineItemsService;
    }

    @GetMapping
    public ResponseEntity<List<OrderLineItemsDTO>> getAllOrderLineItemss() {
        return ResponseEntity.ok(orderLineItemsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderLineItemsDTO> getOrderLineItems(@PathVariable final Long id) {
        return ResponseEntity.ok(orderLineItemsService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrderLineItems(
            @RequestBody @Valid final OrderLineItemsDTO orderLineItemsDTO) {
        return new ResponseEntity<>(orderLineItemsService.create(orderLineItemsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderLineItems(@PathVariable final Long id,
            @RequestBody @Valid final OrderLineItemsDTO orderLineItemsDTO) {
        orderLineItemsService.update(id, orderLineItemsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrderLineItems(@PathVariable final Long id) {
        orderLineItemsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
