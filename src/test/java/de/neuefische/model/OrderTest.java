package de.neuefische.model;

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
        Product product1 =new Tshirt(1, "t-shirt bluebird");
        Product product2 =new Pants(2, "pants neon");
        //when
        Order newOrder= new Order(14,new ArrayList<>());
        newOrder.addProducts(List.of(product1,product2));
        //then
        Assertions.assertEquals(new Order(14,List.of(1,2)),newOrder);
    }


}