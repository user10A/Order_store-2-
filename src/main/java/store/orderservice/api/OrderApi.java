package store.orderservice.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import store.orderservice.dto.OrderRequest;
import store.orderservice.dto.OrderResponse;
import store.orderservice.dto.SimpleResponse;
import store.orderservice.service.OrderService;
import java.util.List;

@RestController
@RequestMapping("/api/v3/orders")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Orders API", description = "API for order CRUD methods")
public class OrderApi {

    private final OrderService orderService;

    @PostMapping("created/orders")
    @Operation(summary = "Created orders", description = "Создание заказа")
    public SimpleResponse createOrder(@RequestBody OrderRequest orderRequest) {
       return orderService.createOrder(orderRequest);
    }

    // Получение всех заказов
    @GetMapping("getAllOrders")
    @Operation(summary = "Orders get", description = "Get all orders")

    public ResponseEntity<List<OrderResponse>> getAllOrders() {
        List<OrderResponse> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // Получение заказов по userId
    @GetMapping("/user/{userId}")
    @Operation(summary = "get order", description = "Get orders by User id")
    public List<OrderResponse> getOrdersByUserId(@PathVariable Long userId) {
        return orderService.getOrdersByCustomerId(userId);
    }

    // Получение заказов по датам
    @GetMapping("/date")
    @Operation(summary = "Get ", description = "Register a new user")
    public List<OrderResponse> getOrdersByDate(@RequestParam String startDate, @RequestParam String endDate) {
        List<OrderResponse> orders = orderService.getOrdersByDate(startDate, endDate);
        return orderService.getOrdersByDate(startDate,endDate);
    }

}
