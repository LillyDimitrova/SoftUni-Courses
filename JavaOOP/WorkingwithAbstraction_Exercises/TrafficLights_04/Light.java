package TrafficLights_04;

public class Light {
    private Colors color;

    public Light(Colors color) {
        this.color = color;
    }

    public Colors getColor() {
        return color;
    }

    public void setColor(Colors color) {
        this.color = color;
    }
    public void changeColor() {
        //ако червен -> става зелен
        //ако зелен -> става жълт
        //ако жълт -> става червен
        if (this.color == Colors.RED) {
            this.color = Colors.GREEN;
        }else if (this.color == Colors.GREEN) {
           this.color = Colors.YELLOW;
        }else if (this.color == Colors.YELLOW) {
            this.color = Colors.RED;
        }
    }
}
