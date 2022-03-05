import java.util.ArrayList;
import java.util.List;

public class StackOfStrings {
    private List<String> data;

    public StackOfStrings() {
        this.data = new ArrayList<>();
    }

    public void push(String item) {
        this.data.add(item);
    }

    public String pop() {
        if (!data.isEmpty()) {
            return this.data.remove(this.data.size() - 1);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public String peek() {
        if (!data.isEmpty()) {
            return data.get(this.data.size() - 1);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
