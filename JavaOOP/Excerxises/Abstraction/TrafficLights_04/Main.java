package TrafficLights_04;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] colors = scanner.nextLine().split("\\s+");
        int n = Integer.parseInt(scanner.nextLine());
        List<Light> lights = new ArrayList<>();
        for (String color : colors) {
            Light light = new Light(Colors.valueOf(color));
            lights.add(light);
        }
        for (int i = 0; i < n; i++) {
            for (Light l : lights) {
                l.changeColor();
                System.out.print(l.getColor() + " ");
            }
            System.out.println();
        }
    }
}
