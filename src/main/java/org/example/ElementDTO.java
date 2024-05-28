package org.example;

public class ElementDTO {
    private String name;
    private String symbol;
    private String atomicNumber;

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getAtomicNumber() {
        return atomicNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAtomicNumber(String atomicNumber) {
        this.atomicNumber = atomicNumber;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
