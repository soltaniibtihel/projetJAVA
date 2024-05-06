package entities;

public class Accommodation {
    private int id,price;
    private String title,type, address;
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Accommodation() {
    }

    public Accommodation(String title, String address, String type,int price) {
        this.price = price;
        this.title = title;
        this.type = type;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "id=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
