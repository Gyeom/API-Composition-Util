import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class MultiKey {
    private final List<Object> keys;

    public MultiKey(Object... keys) {
        this.keys = Arrays.asList(keys);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultiKey multiKey = (MultiKey) o;
        return Objects.equals(getKeyValues(), multiKey.getKeyValues());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getKeyValues());
    }

    private List<Object> getKeyValues() {
        return keys;
    }
}
