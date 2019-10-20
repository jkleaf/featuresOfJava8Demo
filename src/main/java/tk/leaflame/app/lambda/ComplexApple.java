package tk.leaflame.app.lambda;

public class ComplexApple {

    private String color;

    private int weight;

    private String name;

    public ComplexApple() {
    }

    public ComplexApple(String color, int weight, String name) {
        this.color = color;
        this.weight = weight;
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ComplexApple{" +
                "color='" + color + '\'' +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                '}';
    }
}
