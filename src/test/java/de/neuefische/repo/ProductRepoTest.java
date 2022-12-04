package de.neuefische.repo;

import de.neuefische.model.Pants;
import de.neuefische.model.Product;
import de.neuefische.model.Tshirt;
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
        Map<Integer,Product>  actual =productRepo.add(new Tshirt(1,"t-shirt bluebird"));
        //then
        Assertions.assertEquals(Map.of(1,new Tshirt(1,"t-shirt bluebird")),actual);
    }
    @Test
    void listAllProducts() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Tshirt(1,"t-shirt bluebird"));
        products.put(2,new Pants(2,"pants neon"));
        //when

        ProductRepo productRepo= new ProductRepo(products);
        Map<Integer,Product>  actual =productRepo.list();
        //then
        Assertions.assertEquals(Map.of(
                1,new Tshirt(1,"t-shirt bluebird"),
                2,new Pants(2,"pants neon")),actual);
    }

    @Test
    void getOneProductByKeyId() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Tshirt(1,"t-shirt bluebird"));
        products.put(2,new Pants(2,"pants neon"));
        //when

        ProductRepo productRepo= new ProductRepo(products);
        Product actual =productRepo.get(2);
        //then
        Assertions.assertEquals(new Pants(2,"pants neon"),actual);
    }

    @Test
    void throwExceptionWhenInValidKeyId() {
        //given
        Map<Integer,Product> products= new HashMap<>();
        products.put(1,new Tshirt(1,"t-shirt bluebird"));
        products.put(2,new Pants(2,"pants neon"));
        //when
        ProductRepo productRepo= new ProductRepo(products);
        //then
        IndexOutOfBoundsException thrown=Assertions.assertThrows(IndexOutOfBoundsException.class,()-> productRepo.get(3));
        Assertions.assertEquals("Product not found!",thrown.getMessage());
    }


}