package de.neuefische;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ShopServiceTest {

    @Test

    void getProductWithKeyId() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when
        ProductRepo productRepo= new ProductRepo(products);
        ShopService service= new ShopService(productRepo);
        Product actual =service.getProduct(1);
        //then
        Assertions.assertEquals(new Product(1,"t-shirt"),actual);
    }
    @Test
    void listAllProducts() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when
        ProductRepo productRepo= new ProductRepo(products);
        ShopService service= new ShopService(productRepo);
        Map<Integer,Product> actual =service.listProducts();
        //then
        Assertions.assertEquals(products,actual);
    }


    @Test
    void getOneOrderByKeyId() {
        //given
        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(2,3)));
        orders.put(15,new Order(15,List.of(3,4)));
        //when
        OrderRepo orderRepo= new OrderRepo(orders);
        ShopService service= new ShopService(orderRepo);
        Order  actual =service.getOrder(15);
        //then
        Assertions.assertEquals(new Order(15,List.of(3,4)),actual);

    }

    @Test
    void listAllOrders() {
        //given
        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(2,3)));
        orders.put(15,new Order(15,List.of(3,4)));
        //when
        OrderRepo orderRepo= new OrderRepo(orders);
        ShopService service= new ShopService(orderRepo);
        Map<Integer,Order>  actual =service.listOrders();
        //then
        Assertions.assertEquals(Map.of(
                14,new Order(14,List.of(2,3)),
                15,new Order(15,List.of(3,4))
        ),actual);
    }

    @Test
    void returnTrueWhenContainsNonExistedProduct(){
        //given
        //given
        Order newOrder =new Order(14,List.of(1,3));

        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when
        ProductRepo productRepo= new ProductRepo(products);
        ShopService service= new ShopService(productRepo);
        boolean actual =service.containsNonExistedProduct(newOrder);
        //then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnFalseWhenNotContainNonExistedProduct(){
        //given
        Order newOrder =new Order(14,List.of(1,2));

        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when
        ProductRepo productRepo= new ProductRepo(products);
        ShopService service= new ShopService(productRepo);
        boolean actual =service.containsNonExistedProduct(newOrder);
        //then
        Assertions.assertFalse(actual);
    }



    @Test
    void returnNewOrderRepoWhenNotContainNonExistedProductAndAddOrderToOrderRepo() throws Exception {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        products.put(3, new Product(3,"socks"));

        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(1,2)));
        Order newOrder =new Order(15,List.of(2,3));

        //when
        ProductRepo productRepo= new ProductRepo(products);
        OrderRepo orderRepo= new OrderRepo(orders);
        ShopService service= new ShopService(productRepo,orderRepo);

        Map<Integer,Order> actual =service.addOrder(newOrder);
        Assertions.assertEquals(Map.of(
                14, new Order(14,List.of(1,2)),
                15,new Order(15,List.of(2,3))),actual);
    }

    @Test
    void throwExceptionWhenContainNonExistedProduct()  {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        products.put(3, new Product(3,"socks"));

        Map<Integer,Order> orders= new HashMap<>();
        orders.put(14,new Order(14,List.of(1,2)));
        Order newOrder =new Order(15,List.of(2,7));

        //when
        ProductRepo productRepo= new ProductRepo(products);
        OrderRepo orderRepo= new OrderRepo(orders);
        ShopService service= new ShopService(productRepo,orderRepo);

        try {
            Map<Integer, Order> actual = service.addOrder(newOrder);
        } catch (Exception e) {
            Assertions.assertEquals("Product doesn't exist!",e.getMessage());
        }
    }

}