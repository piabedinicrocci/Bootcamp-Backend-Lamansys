# JDK 

[[_TOC_]]

## Java SE 5 (2004)

### Generics

```java

List<String> list = new ArrayList<>();
list.add("Hello");
String str = list.get(0);
```

### Enumerated types (Enums)

```java
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
```

### Varargs

```java
public void printNumbers(int... numbers) {
    for (int number : numbers) {
        System.out.println(number);
    }
}
```

## Java SE 7 (2011)

### Try-with-resources Statement

```java
try (BufferedReader br = new BufferedReader(new FileReader("file.txt"))) {
    System.out.println(br.readLine());
}
```

## Java SE 8 (2014)

### Lambda Expressions

```java
List<String> list = Arrays.asList("a", "b", "c");
list.forEach(item -> System.out.println(item));
```

### Functional Interfaces

```java
@FunctionalInterface
interface MyFunctionalInterface {
    void myMethod();
}
```

### Stream API

```java
List<String> list = Arrays.asList("a", "b", "c");
list.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
```

### Optional Class
```java
Optional<String> optional = Optional.of("Hello");
optional.ifPresent(System.out::println);
```

### New Date and Time API (java.time)
```java
LocalDate date = LocalDate.now();
LocalTime time = LocalTime.now();
LocalDateTime dateTime = LocalDateTime.now();
```

## Java SE 10 (2018)

### Local-Variable Type Inference (var)

```java
var list = new ArrayList<String>();
list.add("Hello");
```

## Java SE 16 (2021)

### Records
```java
public record Point(int x, int y) {}
```