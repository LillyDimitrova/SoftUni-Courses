import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String call() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isValidNumber = true;
        for (String number : numbers) {
            for (int i = 0; i < number.length(); i++) {
                char currentSymbol = number.charAt(i);
                if (!Character.isDigit(currentSymbol)) {
                    isValidNumber = false;
                }
            }
            if (isValidNumber) {
                stringBuilder.append("Calling... ").append(number).append("\n");
            } else {
                stringBuilder.append("Invalid number!").append("\n");
            }

        }
        return stringBuilder.toString().trim();
    }

    @Override
    public String browse() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isValidUrl = true;
        for (String url : urls) {
            for (int i = 0; i < url.length(); i++) {
                char currentSymbol = url.charAt(i);
                if (Character.isDigit(currentSymbol)) {
                    isValidUrl = false;
                }
            }
            if (isValidUrl) {
                stringBuilder.append("Browsing: ").append(url).append("!").append("\n");
            } else
                stringBuilder.append("Invalid URL!").append("\n");
        }
        return stringBuilder.toString().trim();
    }
}
