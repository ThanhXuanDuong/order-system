package de.neuefische.service;

import de.neuefische.model.*;
import de.neuefische.repo.OrderRepo;
import de.neuefische.repo.ProductRepo;
import de.neuefische.service.ShopService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class ShopServiceTest {

    @Test
    void getProductWithValidKeyId() {
        //given
        ProductRepo productRepo= createProductRepoWith3Products();
        //when
        ShopService service= new ShopService(productRepo,new OrderRepo());
        Product actual =service.getProduct(2);
        //then
        Assertions.assertEquals(productRepo.get(2),actual);
    }
    @Test
    void getProduct_WhenInvalidKeyId_returnNull() {
        //given
        ProductRepo productRepo= createProductRepoWith3Products();
        //when
        ShopService service= new ShopService(productRepo,new OrderRepo());
        Product actual =service.getProduct(11);
        //then
        Assertions.assertNull(actual);
    }
    @Test
    void listAllProducts() {
        //given
        ProductRepo productRepo= createProductRepoWith3Products();
        //when
        ShopService service= new ShopService(productRepo,new OrderRepo());
        Map<Integer,Product> actual =service.listProducts();
        //then
        Assertions.assertEquals(productRepo.getProducts(),actual);
    }

    @Test
    void getOneOrderByValidKeyId() {
        //given
        OrderRepo orderRepo= createOrderRepoWith2Orders();
        //when
        ShopService service= new ShopService(new ProductRepo(),orderRepo);
        Order  actual =service.getOrder(1);
        //then
        Assertions.assertEquals(orderRepo.get(1),actual);
    }

    @Test
    void getOrderByInValidKeyId_returnNull() {
        //given
        OrderRepo orderRepo= createOrderRepoWith2Orders();
        //when
        ShopService service= new ShopService(new ProductRepo(),orderRepo);
        Order  actual =service.getOrder(11);
        //then
        Assertions.assertNull(actual);
    }

    @Test
    void listAllOrders() {
        //given
        OrderRepo orderRepo= createOrderRepoWith2Orders();
        //when
        ShopService service= new ShopService(new ProductRepo(),orderRepo);
        Map<Integer,Order>  actual =service.listOrders();
        //then
        Assertions.assertEquals(orderRepo.list(),actual);
    }

    @Test
    void returnTrueWhenOrderContainsNonExistedProduct(){
        //given
        Order newOrder =createNewOrderWith1ValidAnd1InValidProduct();
        ProductRepo productRepo= createProductRepoWith3Products();
        //when
        ShopService service= new ShopService(productRepo,new OrderRepo());
        boolean actual =service.containsNonExistedProduct(newOrder);
        //then
        Assertions.assertTrue(actual);
    }
    @Test
    void returnFalseWhenNotContainNonExistedProduct(){
        //given
        Order newOrder =createNewOrderWith2ValidProducts();
        ProductRepo productRepo= createProductRepoWith3Products();
        //when
        ShopService service= new ShopService(productRepo,new OrderRepo());
        boolean actual =service.containsNonExistedProduct(newOrder);
        //then
        Assertions.assertFalse(actual);
    }

    @Test
    void returnNewOrderRepoWhenContainValidProductsAndAddOrderToOrderRepo() throws Exception {
        ///given
        Order newOrder =createNewOrderWith2ValidProducts();
        ProductRepo productRepo= createProductRepoWith3Products();
        OrderRepo orderRepo= createOrderRepoWith2Orders();
        //when
        ShopService service= new ShopService(productRepo,orderRepo);
        //then
        Map<Integer,Order> actual =service.addOrder(newOrder);
        Assertions.assertEquals(orderRepo.getOrders(),actual);
    }

    @Test
    void throwExceptionWhenContainNonExistedProduct()  {
        ///given
        Order newOrder =createNewOrderWith1ValidAnd1InValidProduct();
        ProductRepo productRepo= createProductRepoWith3Products();
        OrderRepo orderRepo= createOrderRepoWith2Orders();
        //when
        ShopService service= new ShopService(productRepo,orderRepo);
        try {
            Map<Integer, Order> actual = service.addOrder(newOrder);
            Assertions.fail();
        } catch (Exception e) {
            Assertions.assertEquals("Product doesn't exist!",e.getMessage());
        }
    }


    //helper
    public ProductRepo createProductRepoWith3Products(){
        Map<Integer,Product> products= new HashMap<>();
        ProductRepo productRepo= new ProductRepo(products);
        productRepo.add(new Tshirt(1,"t-shirt bluebird"));
        productRepo.add(new Pants(2,"pants neon"));
        productRepo.add(new Socks(3,"socks black and white"));
        return productRepo;
    }

    public OrderRepo createOrderRepoWith2Orders(){
        Map<Integer,Order> orders= new HashMap<>();
        OrderRepo orderRepo= new OrderRepo(orders);
        orderRepo.add(new Order(1,List.of(1,2)));
        orderRepo.add(new Order(2,List.of(2,3)));
        return orderRepo;
    }

    public Order createNewOrderWith2ValidProducts(){
        Order newOrder= new Order(14,new ArrayList<>());
        newOrder.addProducts(List.of(
                new Tshirt(1,"t-shirt bluebird"),
                new Socks(3,"socks black and white")));
        return newOrder;
    }

    public Order createNewOrderWith1ValidAnd1InValidProduct(){
        Product product1 =new Tshirt(1,"t-shirt bluebird");
        Product product2 =new Pants(6,"pants flower");
        Order newOrder= new Order(14,new ArrayList<>());
        newOrder.addProducts(List.of(product1,product2));
        return newOrder;
    }
}