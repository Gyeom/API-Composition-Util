import lombok.experimental.UtilityClass;

import java.util.*;
import java.util.stream.Collectors;

@UtilityClass
public class APICompositionUtil {

    public interface Getter<T> {
        Object getValue(T data, String key);
    }

    public interface Setter<T> {
        void setValue(T data, String key, Object value);
    }

    public interface JoinKeyGetter {
        Set<String> getKeys();
    }

    public interface ObjectCreator<T> {
        T create(T data);
    }

    public <T, U> List<T> leftJoin(QueryData<T> queryData, JoinedData<U> joinedData, APICompositionUtil.JoinKeyGetter mergeKeys) {

        List<T> queryDataValues = queryData.getValues();
        Setter<T> queryDataSetter = queryData.getDataSetter();
        Getter<T> queryDataGetter = queryData.getDataGetter();
        ObjectCreator<T> objectCreator = queryData.getObjectCreator();
        List<String> queryKeys = queryData.getKeys();

        List<U> joinedDataValues = joinedData.getValues();
        Getter<U> joinedDataGetter = joinedData.getDataGetter();
        List<String> joinedKeys = joinedData.getKeys();

        Map<MultiKey, List<U>> joinedDataMap = createJoinedDataMap(joinedDataValues, joinedDataGetter, joinedKeys);

        List<T> mergedItems = new ArrayList<>();

        for (T queryDataValue : queryDataValues) {

            MultiKey queryMultiKey = createQueryMultiKey(queryDataGetter, queryKeys, queryDataValue);

            Optional.ofNullable(joinedDataMap.get(queryMultiKey)).ifPresentOrElse(
                    joinedItemList -> {
                        for (U joinedItem : joinedItemList) {
                            T mergedData = createMergedData(objectCreator, queryDataValue);
                            mergeFields(mergedData, joinedItem, mergeKeys, queryDataSetter, joinedDataGetter);
                            mergedItems.add(mergedData);
                        }
                    },
                    () -> mergedItems.add(queryDataValue)
            );
        }

        return mergedItems;
    }

    private <T> MultiKey createQueryMultiKey(final Getter<T> queryDataGetter, final List<String> queryKeys, final T queryDataValue) {
        return new MultiKey(
                queryKeys.stream()
                        .filter(key -> queryDataGetter.getValue(queryDataValue, key) != null)
                        .map(key -> queryDataGetter.getValue(queryDataValue, key))
                        .collect(Collectors.toList())
        );
    }

    private <U> Map<MultiKey, List<U>> createJoinedDataMap(final List<U> joinedDataValues, final Getter<U> joinedDataGetter, final List<String> joinedKeys) {
        return joinedDataValues.stream().collect(
                Collectors.groupingBy(item -> new MultiKey(
                                joinedKeys.stream()
                                        .map(key -> joinedDataGetter.getValue(item, key))
                                        .collect(Collectors.toList())
                        )
                )
        );
    }

    private <T, U> void mergeFields(T queryData, U joinedData, JoinKeyGetter mergeKeys, Setter<T> queryDataSetter, Getter<U> joinedDataGetter) {
        for (String key : mergeKeys.getKeys()) {
            Object value = joinedDataGetter.getValue(joinedData, key);
            if (value != null) {
                queryDataSetter.setValue(queryData, key, value);
            }
        }
    }

    private <T> T createMergedData(ObjectCreator<T> objectCreator, T queryData) {
        return objectCreator.create(queryData);
    }
}

