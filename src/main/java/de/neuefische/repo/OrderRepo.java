package de.neuefische.repo;

import de.neuefische.model.Order;
import lombok.Data;

import java.util.Map;

@Data
public class OrderRepo {
    private Map<Integer, Order> orders;

    public OrderRepo() {
    }

    public OrderRepo(Map<Integer, Order> orders) {
        this.orders = orders;
    }

    public Map<Integer,Order> add(Order order) {
        orders.put(order.getId(), order);
        return orders;
    }

    public Map<Integer,Order> list(){
        return orders;
    }

    public Order get(int id){
        return orders.get(id);
    }

}
