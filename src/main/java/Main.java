import java.util.*;

public class Main {
    public static void main(String[] args) {
        dtoExample();
        mapExample();
    }

    private static void mapExample() {
        // Fruit List 조회
        List<Map<String, Object>> fruits = findAllFruits();

        // Price List 조회
        List<Map<String, Object>> prices = findAllPrices();

        QueryData<Map<String, Object>> queryData = new QueryData<>(
                fruits,
                List.of("fruit"),
                Map::get,
                Map::put
        );

        JoinedData<Map<String, Object>> joinedData = new JoinedData<>(
                prices,
                List.of("fruit"),
                Map::get
        );

        // left join
        List<Map<String, Object>> mergedMapList = MergeUtils.leftJoin(
                queryData,
                joinedData,
                () -> Set.of("price"),
                HashMap::new
        );

        // 결과 출력
        for (Map<String, Object> map : mergedMapList) {
            System.out.println(map);
        }
    }

    private static List<Map<String, Object>> findAllPrices() {
        return List.of(
                Map.of("fruit", "apple", "price", 100),
                Map.of("fruit", "banana", "price", 200),
                Map.of("fruit", "orange", "price", 300)
        );
    }

    private static List<Map<String, Object>> findAllFruits() {
        return List.of(
                Map.of("fruit", "apple"),
                Map.of("fruit", "banana"),
                Map.of("fruit", "orange"),
                Map.of("fruit", "grape")
        );
    }

    private static void dtoExample() {
        // User List 조회
        List<UserOrder> userOrder = getUserOrders();

        // Order List 조회
        List<Order> orders = Arrays.asList(
                new Order(1, 1, "Apple"),
                new Order(2, 2, "Banana"),
                new Order(3, 3, "Orange"),
                new Order(4, 1, "Grape")
        );

        QueryData<UserOrder> queryData = new QueryData<>(
                userOrder,
                List.of("id"),
                new UserOrderGetter(),
                new UserOrderSetter()
        );

        JoinedData<Order> joinedData = new JoinedData<>(
                orders,
                List.of("userId"),
                new OrderGetter()
        );

        // left join
        List<UserOrder> userOrders = MergeUtils.leftJoin(
                queryData,
                joinedData,
                () -> Set.of("productName", "orderId"),
                data -> new UserOrder(data.getId(), data.getName())
        );

        // 결과 출력
        for (UserOrder user : userOrders) {
            System.out.println(user.toString());
        }
    }

    private static List<UserOrder> getUserOrders() {
        return Arrays.asList(
                new UserOrder(1, "John"),
                new UserOrder(2, "Jane"),
                new UserOrder(3, "Mike")
        );
    }
}
