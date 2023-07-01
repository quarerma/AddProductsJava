package org.example.model;


import org.example.Exception.NegativeQuantityException;
import org.example.Exception.NegativeValueException;

public class Product {
    private String name;
    private double value;
    private int quantity;
    private String description;

    public Product(String name, double value, int quantity, String description) throws NegativeValueException, NegativeQuantityException {
        this.name = name;
        if(value <= 0) throw new NegativeValueException();
        this.value = value;
        if(quantity <= 0) throw new NegativeQuantityException();
        this.quantity = quantity;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString(){
        return "Nome: " + getName() + "     Valor: " + getValue() + "      Quantidade: " + getQuantity() + "     Descrição: " + getDescription();
    }
}
