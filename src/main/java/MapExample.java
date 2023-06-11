import java.util.*;

public class MapExample {
    public static void main(String[] args) {
        mapExample();
    }

    private static void mapExample() {
        // Retrieve the users and their orders
        List<Map<String, Object>> users = findAllUsersMap();
        List<Map<String, Object>> orders = findAllOrdersMap();

        // Define the query data (users) and the data to be joined (orders)
        QueryData<Map<String, Object>> queryData = new QueryData<>(users, List.of("id"), Map::get, Map::put);
        JoinedData<Map<String, Object>> joinedData = new JoinedData<>(orders, List.of("userId"), Map::get);

        // Perform the left join
        List<Map<String, Object>> mergedMapList = MergeUtils.leftJoin(
                queryData,
                joinedData,
                () -> Set.of("productName", "orderId"),
                HashMap::new
        );

        // Print the joined data
        for (Map<String, Object> map : mergedMapList) {
            System.out.println(map);
        }
    }

    private static List<Map<String, Object>> findAllOrdersMap() {
        return List.of(
                Map.of("orderId", 1, "userId", 1, "productName", "Apple"),
                Map.of("orderId", 2, "userId", 2, "productName", "Banana"),
                Map.of("orderId", 3, "userId", 3, "productName", "Orange"),
                Map.of("orderId", 4, "userId", 1, "productName", "Grape")
        );
    }

    private static List<Map<String, Object>> findAllUsersMap() {
        return List.of(
                Map.of("id", 1, "name", "John"),
                Map.of("id", 2, "name", "Jane"),
                Map.of("id", 3, "name", "Mike")
        );
    }
}
