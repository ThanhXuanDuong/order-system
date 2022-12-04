package de.neuefische;

import de.neuefische.model.Order;
import de.neuefische.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrderTest {

    @Test
    void returnOrderWhenAddProducts(){
        //given
        Product product1 =new Product(1, "t-shirt");
        Product product2 =new Product(2, "pants");
        //when
        Order newOrder= new Order(14,new ArrayList<>());
        newOrder.addProducts(List.of(product1,product2));
        //then
        Assertions.assertEquals(new Order(14,List.of(1,2)),newOrder);
    }
}