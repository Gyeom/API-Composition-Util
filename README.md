# MergeUtils - API Composition Solution for Microservices Architecture

Welcome to MergeUtils, a potent Java utility designed to handle one of the significant challenges faced in Microservices Architecture (MSA): amalgamating data from disparate services at the application level. Due to the isolated nature of databases in MSA, the need for performing joins at the application level becomes imperative. MergeUtils makes this task easier and more efficient.

## Overview
MergeUtils provides a sophisticated and accessible mechanism to execute left joins across data sourced from various services, implementing an API Composition pattern. This design is focused on customization, allowing users to modify the joining process according to their specific requirements. MergeUtils achieves this flexibility through interfaces that users can implement according to their application needs.

Microservices Architecture, where each service has its isolated database, there's often a need for a client that requires data that spans multiple services. [The API Composition pattern](https://microservices.io/patterns/data/api-composition.html) is a way to address this problem. MergeUtils takes the principles of this pattern and provides a streamlined, easy-to-use Java utility that facilitates the process.

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
QueryData<Map<String, Object>> queryData = new QueryData<>(users, List.of("id"), Map::get, Map::put, HashMap::new);
JoinedData<Map<String, Object>> joinedData = new JoinedData<>(orders, List.of("userId"), Map::get);

// Perform the left join
List<Map<String, Object>> mergedMapList = MergeUtils.leftJoin(
        queryData,
        joinedData,
        () -> Set.of("productName", "orderId")
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
QueryData<UserOrder> queryData = new QueryData<>(userOrder, List.of("id"), new UserOrderGetter(), new UserOrderSetter(), data -> new UserOrder(data.getId(), data.getName()));
JoinedData<Order> joinedData = new JoinedData<>(orders, List.of("userId"), new OrderGetter());

// Perform the left join
List<UserOrder> userOrders = MergeUtils.leftJoin(
        queryData,
        joinedData,
        () -> Set.of("productName", "orderId")
);

// Print the joined data
for (UserOrder user : userOrders) {
    System.out.println(user.toString());
}
```
