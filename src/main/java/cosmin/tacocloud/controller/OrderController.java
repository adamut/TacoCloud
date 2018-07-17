package cosmin.tacocloud.controller;


import cosmin.tacocloud.domain.Order;
import cosmin.tacocloud.domain.User;
import cosmin.tacocloud.props.OrderProps;
import cosmin.tacocloud.repository.JdbcOrderRepository;
import cosmin.tacocloud.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix = "taco.orders")
public class OrderController {

    private JdbcOrderRepository jdbcOrderRepository;
    private OrderRepository orderRepository;

    @Autowired
    OrderProps orderProps;


    public OrderController(JdbcOrderRepository jdbcOrderRepository, OrderRepository orderRepository) {
        this.jdbcOrderRepository = jdbcOrderRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderFrom(Model model) {
        model.addAttribute("order", new Order());
        return "orderForm";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        jdbcOrderRepository.save(order);
        order.setUser(user);

        sessionStatus.setComplete();
        return "redirect:/";
    }
}
