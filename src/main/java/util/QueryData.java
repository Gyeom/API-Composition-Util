package util;

import lombok.Value;

import java.util.List;

@Value
public class QueryData<T> {
    List<T> values;
    List<String> keys;
    APICompositionUtil.Getter<T> dataGetter;
    APICompositionUtil.Setter<T> dataSetter;
    APICompositionUtil.ObjectCreator<T> objectCreator;
}
