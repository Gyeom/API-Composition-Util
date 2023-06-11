import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryData<T> {
    private final List<T> values;
    private final List<String> keys;
    private final MergeUtils.Getter<T> dataGetter;
    private final MergeUtils.Setter<T> dataSetter;
}
