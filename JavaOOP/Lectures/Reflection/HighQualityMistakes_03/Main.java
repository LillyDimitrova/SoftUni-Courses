import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Field[] fields = Reflection.class.getDeclaredFields();
        Arrays.stream(fields)
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(f -> System.out.printf("%s must be private!%n",f.getName()));

        Method[] methods = Reflection.class.getDeclaredMethods();
        Method[] getters = Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("get") && m.getParameterCount() == 0 && !Modifier.isPublic(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);
        Arrays.stream(getters).forEach(m ->
                System.out.printf("%s have to be public!%n",
                        m.getName()));

        Method[] setters = Arrays.stream(methods)
                .filter(m -> m.getName().startsWith("set") && m.getParameterCount() == 1 && !Modifier.isPrivate(m.getModifiers()))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);
        Arrays.stream(setters).forEach(m ->
                System.out.printf("%s have to be private!%n", m.getName()));
    }
}
