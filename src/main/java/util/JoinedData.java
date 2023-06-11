package util;

import lombok.Value;

import java.util.List;

@Value
public class JoinedData<U> {
    List<U> values;
    List<String> keys;
    APICompositionUtil.Getter<U> dataGetter;
}
