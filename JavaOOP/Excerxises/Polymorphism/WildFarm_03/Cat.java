package WildFarm_03;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String bread;

    public Cat(String animalType, String animalName, Double animalWeight, String livingRegion, String bread) {
        super(animalType, animalName, animalWeight, livingRegion);
        this.bread = bread;
    }

    @Override
    public void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return String.format("%s[%s, %s, %s, %s, %d]"
                , this.getClass().getSimpleName()
                , this.getAnimalName()
                , this.bread
                , decimalFormat.format(this.getAnimalWeight())
                ,this.getLivingRegion()
                , this.getFoodEaten());
    }
}
