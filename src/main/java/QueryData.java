import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryData<T> {
    private final List<T> values;
    private final List<String> keys;
    private final APICompositionUtil.Getter<T> dataGetter;
    private final APICompositionUtil.Setter<T> dataSetter;
    private final APICompositionUtil.ObjectCreator<T> objectCreator;
}
