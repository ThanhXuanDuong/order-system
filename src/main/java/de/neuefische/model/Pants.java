package de.neuefische.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Pants implements Product{
    private int id;
    private String name;
    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }
}
