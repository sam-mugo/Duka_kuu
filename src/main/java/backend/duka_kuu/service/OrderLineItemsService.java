package backend.duka_kuu.service;

//import backend.duka_kuu.domain.Inventory;
import backend.duka_kuu.domain.Order;
import backend.duka_kuu.domain.OrderLineItems;
import backend.duka_kuu.model.OrderLineItemsDTO;
//import backend.duka_kuu.repos.InventoryRepository;
import backend.duka_kuu.repos.OrderLineItemsRepository;
import backend.duka_kuu.repos.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderLineItemsService {

    private final OrderLineItemsRepository orderLineItemsRepository;
    private final OrderRepository orderRepository;
    //private final InventoryRepository inventoryRepository;

    public OrderLineItemsService(final OrderLineItemsRepository orderLineItemsRepository,
            final OrderRepository orderRepository/*,final InventoryRepository inventoryRepository*/) {
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.orderRepository = orderRepository;
        //this.inventoryRepository = inventoryRepository;
    }

    public List<OrderLineItemsDTO> findAll() {
        return orderLineItemsRepository.findAll(Sort.by("id"))
                .stream()
                .map(orderLineItems -> mapToDTO(orderLineItems, new OrderLineItemsDTO()))
                .collect(Collectors.toList());
    }

    public OrderLineItemsDTO get(final Long id) {
        return orderLineItemsRepository.findById(id)
                .map(orderLineItems -> mapToDTO(orderLineItems, new OrderLineItemsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final OrderLineItemsDTO orderLineItemsDTO) {
        final OrderLineItems orderLineItems = new OrderLineItems();
        mapToEntity(orderLineItemsDTO, orderLineItems);
        return orderLineItemsRepository.save(orderLineItems).getId();
    }

    public void update(final Long id, final OrderLineItemsDTO orderLineItemsDTO) {
        final OrderLineItems orderLineItems = orderLineItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderLineItemsDTO, orderLineItems);
        orderLineItemsRepository.save(orderLineItems);
    }

    public void delete(final Long id) {
        orderLineItemsRepository.deleteById(id);
    }

    private OrderLineItemsDTO mapToDTO(final OrderLineItems orderLineItems,
            final OrderLineItemsDTO orderLineItemsDTO) {
        orderLineItemsDTO.setId(orderLineItems.getId());
        orderLineItemsDTO.setSkuCode(orderLineItems.getSkuCode());
        orderLineItemsDTO.setPrice(orderLineItems.getPrice());
        orderLineItemsDTO.setQuantity(orderLineItems.getQuantity());
        orderLineItemsDTO.setOrder(orderLineItems.getOrder() == null ? null : orderLineItems.getOrder().getId());
        //orderLineItemsDTO.setInventory(orderLineItems.getInventory() == null ? null : orderLineItems.getInventory().getId());
        return orderLineItemsDTO;
    }

    private OrderLineItems mapToEntity(final OrderLineItemsDTO orderLineItemsDTO,
            final OrderLineItems orderLineItems) {
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
        final Order order = orderLineItemsDTO.getOrder() == null ? null : orderRepository.findById(orderLineItemsDTO.getOrder())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
        orderLineItems.setOrder(order);
//        final Inventory inventory = orderLineItemsDTO.getInventory() == null ? null : inventoryRepository.findById(orderLineItemsDTO.getInventory())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "inventory not found"));
//        orderLineItems.setInventory(inventory);
        return orderLineItems;
    }

}
