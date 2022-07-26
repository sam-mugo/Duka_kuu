package backend.duka_kuu.service;

import backend.duka_kuu.domain.AppUser;
import backend.duka_kuu.domain.Order;
import backend.duka_kuu.domain.OrderLineItem;
import backend.duka_kuu.model.OrderDTO;
import backend.duka_kuu.repos.AppUserRepo;
import backend.duka_kuu.repos.OrderRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final AppUserRepo appUserRepository;

    public OrderService(final OrderRepository orderRepository,
            final AppUserRepo appUserRepository) {
        this.orderRepository = orderRepository;
        this.appUserRepository = appUserRepository;
    }

    public List<OrderDTO> findAll() {
        return orderRepository.findAll(Sort.by("orderId"))
                .stream()
                .map(order -> mapToDTO(order, new OrderDTO()))
                 .collect(Collectors.toList());
    }

    public OrderDTO get(final Long orderId) {
        return orderRepository.findById(orderId)
                .map(order -> mapToDTO(order, new OrderDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final OrderDTO orderDTO) {
        final Order order = new Order();
        mapToEntity(orderDTO, order);
        return orderRepository.save(order).getOrderId();
    }

    public void update(final Long id, final OrderDTO orderDTO) {
        final Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(orderDTO, order);
        orderRepository.save(order);
    }

    public void delete(final Long orderId) {
        orderRepository.deleteById(orderId);
    }

    private OrderDTO mapToDTO(final Order order, final OrderDTO orderDTO) {
        orderDTO.setOrderId(order.getOrderId());
        UUID uuid = UUID.randomUUID();
        orderDTO.setOrderNumber(uuid.toString());
        List<OrderLineItem> orderItems = new ArrayList<>();
        orderDTO.setOrderItems(orderItems);
        orderDTO.setUser(order.getUser() == null ? null : order.getUser().getUserId());
        return orderDTO;
    }

    private Order mapToEntity(final OrderDTO orderDTO, final Order order) {
        UUID uuid = UUID.randomUUID();
        order.setOrderNumber(uuid.toString());
        final AppUser user = orderDTO.getUser() == null ? null : appUserRepository.findById(orderDTO.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
       order.setUser(user);
       List<OrderLineItem> orderItems = new ArrayList<>();
       order.setOrderItems(orderItems);
        return order;
    }

}
