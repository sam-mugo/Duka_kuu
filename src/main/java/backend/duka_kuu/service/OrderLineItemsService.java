package backend.duka_kuu.service;

//import backend.duka_kuu.domain.Inventory;
import backend.duka_kuu.domain.Order;
import backend.duka_kuu.domain.OrderLineItem;
import backend.duka_kuu.domain.Product;
import backend.duka_kuu.model.OrderLineItemsDTO;
//import backend.duka_kuu.repos.InventoryRepository;
import backend.duka_kuu.repos.OrderLineItemsRepository;
import backend.duka_kuu.repos.OrderRepository;
import java.util.List;
import java.util.stream.Collectors;

import backend.duka_kuu.repos.ProductRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderLineItemsService {

    private final OrderLineItemsRepository orderLineItemsRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderLineItemsService(final OrderLineItemsRepository orderLineItemsRepository,
            final OrderRepository orderRepository, final ProductRepository productRepository) {
        this.orderLineItemsRepository = orderLineItemsRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
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
        final OrderLineItem orderLineItems = new OrderLineItem();
        mapToEntity(orderLineItemsDTO, orderLineItems);
        return orderLineItemsRepository.save(orderLineItems).getId();
    }

    public void update(final Long id, final OrderLineItemsDTO orderLineItemsDTO) {
        final OrderLineItem orderLineItems = orderLineItemsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderLineItemsDTO, orderLineItems);
        orderLineItemsRepository.save(orderLineItems);
    }

    public void delete(final Long id) {
        orderLineItemsRepository.deleteById(id);
    }

    private OrderLineItemsDTO mapToDTO(final OrderLineItem orderLineItems,
            final OrderLineItemsDTO orderLineItemsDTO) {
        orderLineItemsDTO.setId(orderLineItems.getId());
        orderLineItemsDTO.setProduct(orderLineItems.getProduct() == null ? null : orderLineItems.getProduct().getId());
        //orderLineItemsDTO.setPrice(orderLineItems.getPrice());
        //orderLineItemsDTO.setQuantity(orderLineItems.getQuantity());
        //orderLineItemsDTO.setOrder(orderLineItems.getOrder() == null ? null : orderLineItems.getOrder().getOrderId());

        //orderLineItemsDTO.setInventory(orderLineItems.getInventory() == null ? null : orderLineItems.getInventory().getId());
        return orderLineItemsDTO;
    }

    private OrderLineItem mapToEntity(final OrderLineItemsDTO orderLineItemsDTO,
                                      final OrderLineItem orderLineItems) {
        //orderLineItems.setProduct(orderLineItems.getProduct() == null ? null : orderLineItems.getProduct().getId());
        //orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        //orderLineItems.setQuantity(orderLineItemsDTO.getQuantity());
//        final Order order = orderLineItemsDTO.getOrder() == null ? null : orderRepository.findById(orderLineItemsDTO.getOrder())
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "order not found"));
//        orderLineItems.setOrder(order);
       final Product product = orderLineItemsDTO.getProduct() == null ? null : productRepository.findById(orderLineItemsDTO.getProduct())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "product not found"));
        orderLineItems.setProduct(product);
        return orderLineItems;
    }
}
