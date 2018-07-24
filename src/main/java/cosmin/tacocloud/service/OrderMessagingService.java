package cosmin.tacocloud.service;

import cosmin.tacocloud.domain.Order;
import org.springframework.jms.core.JmsTemplate;

public interface OrderMessagingService {
    void sendOrder(Order order);
}
