package de.neuefische;

import de.neuefische.model.Product;
import de.neuefische.repo.ProductRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class ProductRepoTest {
    @Test
    void addProductToProductRepoWithIdAsKey() {
        //given
        Map<Integer, Product> products= new HashMap<>();
        //when
        ProductRepo productRepo= new ProductRepo(products);
        Map<Integer,Product>  actual =productRepo.add(new Product(1,"t-shirt"));
        //then
        Assertions.assertEquals(Map.of(1,new Product(1,"t-shirt")),actual);
    }
    @Test
    void listAllProducts() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when

        ProductRepo productRepo= new ProductRepo(products);
        Map<Integer,Product>  actual =productRepo.list();
        //then
        Assertions.assertEquals(Map.of(
                1,new Product(1,"t-shirt"),
                2,new Product(2,"pants")),actual);
    }

    @Test
    void getOneProductByValidKeyId() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when

        ProductRepo productRepo= new ProductRepo(products);
        Product actual =productRepo.get(2);
        //then
        Assertions.assertEquals(new Product(2,"pants"),actual);
    }

    @Test
    void throwExceptionWhenInValidKeyId() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Product(1,"t-shirt"));
        products.put(2,new Product(2,"pants"));
        //when
        ProductRepo productRepo= new ProductRepo(products);
        //then
        IndexOutOfBoundsException thrown=Assertions.assertThrows(IndexOutOfBoundsException.class,()-> productRepo.get(3));
        Assertions.assertEquals("Product not found!",thrown.getMessage());
    }

}