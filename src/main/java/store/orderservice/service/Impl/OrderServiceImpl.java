package store.orderservice.service.Impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import store.orderservice.dto.OrderRequest;
import store.orderservice.dto.OrderResponse;
import store.orderservice.dto.OrderUpdateRequest;
import store.orderservice.dto.SimpleResponse;
import store.orderservice.entities.Order;
import store.orderservice.repo.OrderRepo;
import store.orderservice.service.OrderService;
import store.orderservice.repo.dao.OrderDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl  implements OrderService {
    private final OrderRepo orderRepo;
    private final OrderDao orderDao;
    @Override
    public SimpleResponse createOrder(OrderRequest request) {
        Order order = new Order();
        log.info("request: {}", request);
        order.setProductsId(request.getProductsId());
        order.setUserId(request.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setTotalQuantity(request.getTotaQuantity());
        order.setTotalPrice(request.getTotalPrice());
        orderRepo.save(order);
        log.info("Order created successfully order: {}", order);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).messageCode("Order created successfully order").build();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        Optional<Order> order = orderRepo.findById(orderId);
        return OrderResponse.builder()
                .orderId(orderId)
                .productsId(order.get().getProductsId())
                .userId(order.get().getUserId())
                .orderDate(order.get().getOrderDate())
                .totalPrice(order.get().getTotalPrice())
                .totaQuantity(order.get().getTotalQuantity())
                .build();
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {
        return orderDao.getOrderById(customerId);
    }

    @Override
    public SimpleResponse updateOrder(OrderUpdateRequest request) {
        Order order = orderRepo.findById(request.getOrderId()).orElse(null);
        order.setProductsId(request.getProductsId());
        order.setTotalPrice(request.getTotalPrice());
        order.setTotalQuantity(request.getTotaQuantity());
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).messageCode("Order updated successfully order").build();
    }

    @Override
    public SimpleResponse deleteOrder(Long orderId) {
        Order order = orderRepo.findById(orderId).orElse(null);
        assert order != null;
        orderRepo.delete(order);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).messageCode("Order deleted successfully order").build();
    }

    @Override
    public List<OrderResponse> getOrdersByDate(String startDate, String endDate) {
        return List.of();
    }
}
