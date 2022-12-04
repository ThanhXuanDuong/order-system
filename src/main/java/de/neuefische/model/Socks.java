package de.neuefische.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Socks implements Product{
    private int id;
    private String name;
    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
