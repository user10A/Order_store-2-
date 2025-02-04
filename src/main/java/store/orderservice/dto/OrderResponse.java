package store.orderservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
public class OrderResponse {
    private Long orderId;
    private Long userId;
    private List<Long> productsId;
    private int totaQuantity;
    private double totalPrice;
    private LocalDateTime orderDate;
    public OrderResponse(Long orderId, Long userId, List<Long> productsId, int totalQuantity, double totalPrice, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.userId = userId;
        this.productsId = productsId;
        this.totaQuantity = totalQuantity;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
