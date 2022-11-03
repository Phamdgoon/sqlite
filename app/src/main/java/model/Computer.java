package model;

public class Computer {
    private String idComputer, name, idCategory;
    private int price;

    public Computer() {

    }

    public Computer(String idComputer, String name, String idCategory, int price) {
        this.idComputer = idComputer;
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
    }

    public String getIdComputer() {
        return idComputer;
    }

    public void setIdComputer(String idComputer) {
        this.idComputer = idComputer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
