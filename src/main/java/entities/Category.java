package entities;

public class Category {
    private int id,statistic;
    private String categoryName,description;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatistic() {
        return statistic;
    }

    public void setStatistic(int statistic) {
        this.statistic = statistic;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Category() {
    }

    public Category(String categoryName, String description,int statistic) {
        this.statistic = statistic;
        this.categoryName = categoryName;
        this.description = description;

    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", statistic=" + statistic +
                ", categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
