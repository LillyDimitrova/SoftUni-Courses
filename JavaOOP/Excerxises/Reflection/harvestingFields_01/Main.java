package harvestingFields;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		String input = scanner.nextLine();

		Field[] fields = RichSoilLand.class.getDeclaredFields();

		while (!input.equals("HARVEST")) {

			switch (input) {
				case "private":
					Arrays.stream(fields).filter(f -> Modifier.isPrivate(f.getModifiers()))
							.forEach(f -> System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName()));
					break;
				case "protected":
					Arrays.stream(fields).filter(f -> Modifier.isProtected(f.getModifiers()))
							.forEach(f -> System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName()));
					break;
				case "public":
					Arrays.stream(fields).filter(f -> Modifier.isPublic(f.getModifiers()))
							.forEach(f -> System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName()));
					break;
				case "all":
					Arrays.stream(fields)
							.forEach(f -> System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getType().getSimpleName() + " " + f.getName()));
					break;
			}
			input = scanner.nextLine();
		}

	}
}
