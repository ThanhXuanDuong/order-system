package de.neuefische;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Order {
    private int id;
    private List<Integer> productIds;

    public List<Integer> addProduct(List<Product> products){
        for (Product product:products) {
            productIds.add(product.getId());
        }
        return productIds;
    }
}
