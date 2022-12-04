package de.neuefische.service;

import de.neuefische.model.Order;
import de.neuefische.model.Product;
import de.neuefische.repo.OrderRepo;
import de.neuefische.repo.ProductRepo;
import lombok.Data;

import java.util.*;

@Data
public class ShopService {
    private ProductRepo productRepo;
    private OrderRepo orderRepo;

    public ShopService(ProductRepo productRepo, OrderRepo orderRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
    }

    public Product getProduct(int id){
        return productRepo.get(id);
    }

    public Map<Integer,Product> listProducts(){
        return productRepo.list();
    }

    public Map<Integer, Order> addOrder(Order newOrder) throws Exception {
        if (!containsNonExistedProduct(newOrder)) {
            return orderRepo.add(newOrder);
        }else {
            throw new Exception("Product doesn't exist!");
        }
    }

    public Order getOrder(int id){
        return orderRepo.get(id);
    }

    public Map<Integer,Order> listOrders(){
        return orderRepo.list();
    }

    public boolean containsNonExistedProduct(Order newOrder){
        List<Integer> orderedProductIds=newOrder.getProductIds();
        Set<Integer> productRepoIds=productRepo.list().keySet();
        for (Integer orderedProduct: orderedProductIds){
            if (!productRepoIds.contains(orderedProduct)){
                return true;
            }
        }
        return false;

    }
}
