package de.neuefische.repo;

import de.neuefische.model.Order;
import de.neuefische.repo.OrderRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class OrderRepoTest {

    @Test
    void addOrderToOrderRepoWithIdAsKey() {
        //given
        Map<Integer, Order> orders= new HashMap<>();
        //when
        OrderRepo orderRepo= new OrderRepo(orders);
        Map<Integer,Order>  actual =orderRepo.add(
                new Order(5, List.of(1,2)));
        //then
        Assertions.assertEquals(Map.of(5,new Order(5,List.of(1,2))),actual);
    }

    @Test
    void listAllOrders() {
        //given
        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(1,2)));
        orders.put(15,new Order(15,List.of(1,3)));
        //when
        OrderRepo orderRepo= new OrderRepo(orders);
        Map<Integer,Order>  actual =orderRepo.list();
        //then
        Assertions.assertEquals(Map.of(
                14,new Order(14,List.of(1, 2)),
                15,new Order(15,List.of(1, 3))
                ),actual);

    }

    @Test
    void getOneOrderByKeyId() {
        //given
        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(1,2)));
        orders.put(15,new Order(15,List.of(1,3)));
        //when
        OrderRepo orderRepo= new OrderRepo(orders);
        Order  actual =orderRepo.get(15);
        //then
        Assertions.assertEquals(new Order(15,List.of(1,3)),actual);
    }

    @Test
    void throwExceptionWhenInValidKeyId() {
        //given
        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(1,2)));
        orders.put(15,new Order(15,List.of(1,3)));
        //when
        OrderRepo orderRepo= new OrderRepo(orders);
        //then
        IndexOutOfBoundsException thrown=Assertions.assertThrows(IndexOutOfBoundsException.class,()-> orderRepo.get(20));
        Assertions.assertEquals("Order not found!",thrown.getMessage());
    }


}