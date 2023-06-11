import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class JoinedData<U> {
    private final List<U> values;
    private final List<String> keys;
    private final MergeUtils.Getter<U> dataGetter;
}
