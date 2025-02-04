package store.orderservice.repo.dao.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import store.orderservice.dto.OrderResponse;
import store.orderservice.repo.dao.OrderDao;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderDaoImpl implements OrderDao {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderResponse> getOrdersByDate(String startDate, String endDate) {
        String sql = """
                    SELECT id, userId, productsId, totalQuantity, totalPrice, orderDate 
                    FROM orders 
                    WHERE orderDate BETWEEN ? AND ?
                """;

        return jdbcTemplate.query(sql, ps -> {
            ps.setDate(1, Date.valueOf(startDate));
            ps.setDate(2, Date.valueOf(endDate));
        }, (rs, rowNum) -> new OrderResponse(
                rs.getLong("id"),
                rs.getLong("userId"),
                Arrays.stream(rs.getString("productsId").split(","))
                        .map(Long::parseLong)
                        .toList(),
                rs.getInt("totalQuantity"),
                rs.getDouble("totalPrice"),
                rs.getTimestamp("orderDate").toLocalDateTime()
        ));
    }

    @Override
    public List<OrderResponse> getOrderById(Long userId) {
        String sql = """
                    SELECT o.id, o.user_id, o.products_id, o.totalQuantity, o.totalPrice, o.orderDate
                    FROM orders o
                    WHERE o.user_id = ?
                """;

        return jdbcTemplate.query(sql, ps -> ps.setLong(1, userId), (rs, rowNum) -> new OrderResponse(
                rs.getLong("id"),
                rs.getLong("userId"),
                Arrays.stream(rs.getString("productsId").split(","))
                        .map(Long::parseLong)
                        .toList(),
                rs.getInt("totalQuantity"),
                rs.getDouble("totalPrice"),
                rs.getTimestamp("orderDate").toLocalDateTime()
        ));
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        String sql = """
                    SELECT id, userId, productsId, totalQuantity, totalPrice, orderDate 
                    FROM orders
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> new OrderResponse(
                rs.getLong("id"),
                rs.getLong("userId"),
                Arrays.stream(rs.getString("productsId").split(","))
                        .map(Long::parseLong)
                        .toList(),
                rs.getInt("totalQuantity"),
                rs.getDouble("totalPrice"),
                rs.getTimestamp("orderDate").toLocalDateTime()
        ));
    }
}