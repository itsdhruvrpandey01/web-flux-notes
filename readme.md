# 📘 Reactive Programming With Spring WebFlux — Detailed Explanation

This repository demonstrates various core concepts of **Reactive Programming** using **Project Reactor**, the reactive engine behind **Spring WebFlux**.
All examples are written using `Mono`, `Flux`, and Reactor operators such as `map`, `flatMap`, `zip`, and `delayElements`.

---

## 🧰 Technologies Used

* **Spring Boot**
* **Spring WebFlux**
* **Project Reactor**
* **JUnit 5**

---

# 📚 What You Will Learn

This project explains:

### ✔ Difference between `Mono` and `Flux`

### ✔ Error handling in reactive streams

### ✔ Combining publishers using `zip()` and `zipWith()`

### ✔ Transforming reactive data with `map` and `flatMap`

### ✔ Converting `Mono` → `Flux` using `flatMapMany`

### ✔ Applying time-based operations like `delayElements()`

### ✔ Understanding how subscription works in Reactor

---

# 📝 Code Under Test

The tests are inside:

```
ReactiveProgrammngApplicationTests.java
```

This file contains multiple sections demonstrating WebFlux + Reactor behavior.

---

# 🧠 WebFlux & Reactor Concepts Used in the Code

Below is an explanation of every section of your code.

---

# 1️⃣ **Mono Basics + Error Handling**

```java
Mono<String> errorMono = Mono.error(new RuntimeException("Error from mono by dhruv"));

Mono<String> m1 = Mono
    .just("Learn Code With Durgesh")
    .log()
    .then(errorMono);

m1.subscribe(
    data -> System.out.println("Data: " + data),
    err -> System.out.println("Handled error from m1: " + err.getMessage())
);

errorMono.subscribe(
    data -> System.out.println("Data: " + data),
    err -> System.out.println("Handled error: " + err.getMessage())
);
```

### 🔍 Explanation

* `Mono` represents **0 or 1 value**.
* `Mono.just(value)` produces a single value.
* `.log()` prints reactor signals like *onSubscribe, onNext, onComplete*.
* `.then(errorMono)` ignores the previous output and switches to another publisher.
* `Mono.error()` produces an error signal.

### 💡 Reactive Streams Rule

Only **one terminal signal** can be sent:

* either **onComplete**
* or **onError**
  Never both.

---

# 2️⃣ **Combining Publishers With zip()**

```java
Mono<String> m1 = Mono.just("Mono m1");
Mono<String> m2 = Mono.just("Mono m2");
Mono<String> m3 = Mono.just("Mono m3");

Mono<Tuple3<String, String, String>> zipped = Mono.zip(m1, m2, m3);
```

### 🔍 Explanation

`zip()` combines multiple publishers and waits for all of them.
Each Mono emits 1 value → zip creates a **Tuple3**.

You can extract values:

```java
zipped.subscribe(data -> {
    System.out.println(data.getT1());
});
```

---

# 3️⃣ **zipWith() — Combining 2 Publishers**

```java
Mono<Tuple2<String, String>> zippedCombined = m1.zipWith(m2);
zippedCombined.subscribe(data -> {
    System.out.println(data.getT1());
    System.out.println(data.getT2());
});
```

### 🔍 Explanation

* `zipWith()` is a more readable form of 2-way zipping.
* Result is a `Tuple2`.

---

# 4️⃣ **Transforming Data — map(), flatMap(), flatMapMany()**

### ✔ *map() → Synchronous transformation*

```java
Mono<String> resultMono = m1.map(data -> data.toUpperCase());
```

* `map` is **synchronous**
* Converts value → new value
  No new reactive stream is created.

---

### ✔ *flatMap() → Asynchronous composition*

```java
Mono<String[]> resultFlatMap = m1.flatMap(value -> Mono.just(value.split(" ")));
```

* Used when returning another **Mono** inside a processing chain.
* `map` returns value
* `flatMap` returns **Mono inside Mono**, so Reactor flattens it.

---

### ✔ *flatMapMany() → Mono → Flux converter*

```java
Flux<String> stringFlux = m1.flatMapMany(value -> Flux.just(value.split(" ")));
```

* Converts a **Mono into a Flux**
* Useful when 1 value generates *multiple* elements.

---

# 5️⃣ **Delay Example — delayElements()**

```java
Flux<String> stringFlux = m1
        .concatWith(m2)
        .log()
        .delayElements(Duration.ofMillis(3000));
```

### 🔍 Explanation

* `concatWith()` → runs publishers sequentially
* `delayElements()` makes each element wait **3 seconds**
* Reactor uses **non-blocking scheduler**, so delay does not freeze threads

### ✔ Why `Thread.sleep()`?

Because the test method ends before delay emits values.
Sleep keeps JVM alive.

---

# 🧵 Threading & Schedulers (implicit)

By default, Reactor executes everything on the **calling thread** unless a scheduler is applied.

Example output:

```
main
Mono m1
Mono m2
```

Even delay runs on reactive scheduler, not Java threads.

---

# 📌 Summary Table

| Concept           | Meaning                           |
| ----------------- | --------------------------------- |
| **Mono**          | Emits 0 or 1 value                |
| **Flux**          | Emits 0…N values                  |
| **map**           | Transform data synchronously      |
| **flatMap**       | Transform data → new Mono         |
| **flatMapMany**   | Convert Mono → Flux               |
| **zip**           | Combine publishers; produce tuple |
| **zipWith**       | Pairwise zip                      |
| **delayElements** | Delay emission (non-blocking)     |
| **subscribe()**   | Triggers the pipeline             |

---

# 🚀 How WebFlux Uses These Concepts

Spring WebFlux is built on Project Reactor, so:

* Every HTTP request returns a **Mono** or **Flux**
* Non-blocking I/O uses **reactor-netty**
* Operations like `map`, `flatMap`, `zip` build reactive pipelines
* Data is processed only after `.subscribe()`
  (in controllers, subscription is handled by WebFlux automatically)

---

# 🏁 Final Note

This project gives a great foundation for:

✔ WebFlux REST APIs
✔ Reactive database access (R2DBC / MongoDB)
✔ Streaming APIs (Server-Sent Events)
✔ Non-blocking microservices

---