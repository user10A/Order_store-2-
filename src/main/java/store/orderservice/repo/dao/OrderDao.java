package store.orderservice.repo.dao;

import store.orderservice.dto.OrderResponse;

import java.util.List;

public interface OrderDao {
    List<OrderResponse> getOrdersByDate(String startDate, String endDate);
    public List<OrderResponse> getOrderById(Long userId);
    public List<OrderResponse> getAllOrders();

}
