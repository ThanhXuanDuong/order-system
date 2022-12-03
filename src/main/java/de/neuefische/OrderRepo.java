package de.neuefische;

import java.util.Map;
import java.util.Objects;


public class OrderRepo {
    private Map<Integer,Order> orders;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRepo orderRepo = (OrderRepo) o;
        return Objects.equals(orders, orderRepo.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders);
    }

    @Override
    public String toString() {
        return orders.toString();
    }
}
