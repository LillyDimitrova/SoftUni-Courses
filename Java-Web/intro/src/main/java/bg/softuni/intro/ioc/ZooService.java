package bg.softuni.intro.ioc;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZooService {



    private final Animal animal;
    private final Animal animal2;
    private final Animal animal3;

    public ZooService(@Qualifier("mySuperDog") Animal animal,
                      @Qualifier("normalDog") Animal animal2,
                      @Qualifier("cat") Animal animal3) {
        this.animal = animal;
        this.animal2 = animal2;
        this.animal3 = animal3;
    }

    public void doWork() {
        animal.makeNoise();
        animal2.makeNoise();
        animal3.makeNoise();
    }

//    private final List<Animal> animals;
//    public ZooService(List<Animal> animals) {
//        this.animals = animals;
//    }
//    public void doWork() {
//        animals.stream().forEach(Animal::makeNoise);
//    }
}
