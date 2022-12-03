package de.neuefische;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class OrderTest {

    @Test
    void returnProductIdsWhenAddProduct(){
        //given
        Product product1 =new Product(1, "t-shirt");
        Product product2 =new Product(2, "pants");
        //when
        Order newOrder= new Order(14,new ArrayList<>());
        List<Integer> actual=newOrder.addProduct(List.of(product1,product2));
        //then
        Assertions.assertEquals(List.of(1,2),actual);
    }
}