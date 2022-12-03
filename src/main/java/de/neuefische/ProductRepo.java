package de.neuefische;

import java.util.Map;
import java.util.Objects;


public class ProductRepo {
    private Map<Integer,Product> products;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductRepo that = (ProductRepo) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }

    @Override
    public String toString() {
        return products.toString();
    }
}
