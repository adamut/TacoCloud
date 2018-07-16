package cosmin.tacocloud.repository;

import cosmin.tacocloud.domain.Order;
import cosmin.tacocloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
