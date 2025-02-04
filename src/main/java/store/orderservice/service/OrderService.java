package store.orderservice.service;

import store.orderservice.dto.OrderRequest;
import store.orderservice.dto.OrderResponse;
import store.orderservice.dto.OrderUpdateRequest;
import store.orderservice.dto.SimpleResponse;

import java.util.List;

public interface OrderService {
    SimpleResponse createOrder(OrderRequest order);
    OrderResponse getOrderById(Long orderId);
    List<OrderResponse> getAllOrders();
    List<OrderResponse>getOrdersByCustomerId(Long customerId);
    SimpleResponse updateOrder(OrderUpdateRequest order);
    SimpleResponse deleteOrder(Long orderId);
    List<OrderResponse> getOrdersByDate(String startDate, String endDate);
    //()  calculateTotalPrice(Long orderId)
}
