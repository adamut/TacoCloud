package cosmin.tacocloud.repository;

import cosmin.tacocloud.domain.Order;

public interface OrderRepository {

    Order save(Order order);
}
