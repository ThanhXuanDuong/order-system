package de.neuefische.repo;

import de.neuefische.model.Product;
import lombok.Data;

import java.util.Map;

@Data
public class ProductRepo {
    private Map<Integer, Product> products;

    public ProductRepo() {
    }

    public ProductRepo(Map<Integer,Product> products) {
        this.products = products;
    }

    public Map<Integer,Product> add(Product product) {
        products.put(product.getId(), product);
        return products;
    }

    public Map<Integer,Product> list(){
        return products;
    }
    public Product get(int id){
        return products.get(id);
    }

}
