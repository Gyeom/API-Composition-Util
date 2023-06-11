# MergeUtils - Joining Solution for Microservices Architecture 

Welcome to MergeUtils, Java utility for solving a common issue encountered in a Microservices Architecture (MSA): the need to join data from disparate services at the application level. Due to the inherent separation of databases in MSA, it is often necessary to perform joins in the application layer itself. This is where MergeUtils comes in handy.

## Overview

MergeUtils provides a streamlined, easy-to-use mechanism for executing left joins across data from different services. It is implemented with a keen focus on customization, allowing users to tailor the joining process to their specific requirements. The solution accomplishes this through interfaces that the users can implement with their own logic.

## Features

- **Getter Interface:** Allows the extraction of values from a data object, given a specific key. This interface provides the `getValue()` method, which is useful in retrieving the desired data value.
  
- **Setter Interface:** Allows the setting of values in a data object based on a specific key. This interface offers the `setValue()` method, which is vital in updating data object values.
  
- **JoinKeyGetter Interface:** Gives a set of keys based on which the join will be executed.
  
- **ObjectCreator Interface:** Allows the creation of new objects based on a given template object.
  
- **leftJoin Method:** This is the core functionality of MergeUtils. It facilitates the left join operation across different services. The method utilizes the Getter, Setter, JoinKeyGetter, and ObjectCreator interfaces to perform this task in a highly customizable manner.
  
- **MultiKey Class:** This helper class allows MergeUtils to deal with multiple keys when performing the join. MultiKey uses the equals and hashCode methods to allow an efficient grouping of data based on multiple key fields.

## Usage

1. Define the interfaces based on your requirements (Getter, Setter, JoinKeyGetter, ObjectCreator).
2. Collect your data from the different services.
3. Call the `leftJoin` method, passing in the interfaces and the data you collected.

MergeUtils will return a List of your templated data type containing the joined data. With MergeUtils, you can focus on what you do best: creating exceptional software solutions, while we take care of the rest.

## Sample Example using `Map<String, Object>`

In the first example, we demonstrate how to use MergeUtils to perform left joins on two `Map<String, Object>` lists representing users and their orders. This comes handy when dealing with separate microservices that manage user data and order data, respectively.

```java
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
```

## Sample Example using DTO

In the second example, we perform the same operation but this time using DTOs. The DtoExample class shows how to use MergeUtils to join data from UserOrder and Order objects.

```java
// Retrieve the users and their orders
List<UserOrder> userOrder = findAllUsersMapDto();
List<Order> orders = findAllOrdersMapDto();

// Define the query data (users) and the data to be joined (orders)
QueryData<UserOrder> queryData = new QueryData<>(userOrder, List.of("id"), new UserOrderGetter(), new UserOrderSetter());
JoinedData<Order> joinedData = new JoinedData<>(orders, List.of("userId"), new OrderGetter());

// Perform the left join
List<UserOrder> userOrders = MergeUtils.leftJoin(
        queryData,
        joinedData,
        () -> Set.of("productName", "orderId"),
        data -> new UserOrder(data.getId(), data.getName())
);

// Print the joined data
for (UserOrder user : userOrders) {
    System.out.println(user.toString());
}
```
