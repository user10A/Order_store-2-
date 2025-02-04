package store.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private Long userId;
    private List<Long> productsId;
    private int totaQuantity;
    private double totalPrice;
}
