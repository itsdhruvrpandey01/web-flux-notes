package com.reactive.reactiveProgrammng;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

@SpringBootTest
class ReactiveProgrammngApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void workinWithMono() throws InterruptedException {
		
		/* 'just' example
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
		*/
		
		/*Zip
		Mono<String> m1 = Mono.just("Mono m1");
		Mono<String> m2 = Mono.just("Mono m2");
		Mono<String> m3 = Mono.just("Mono m3");
		
		Mono<Tuple3<String, String, String>>zipped=Mono.zip(m1, m2,m3);
		zipped.subscribe((data)->{
			System.out.println(data.getT1());
		});
		
		Mono<Tuple2<String, String>>zippedCombined=m1.zipWith(m2);
		zippedCombined.subscribe((data)->{
			System.out.println(data.getT1());
			System.out.println(data.getT2());
		});
		*/
		
		/* Map -> transforms the current using the syn function*/
		
		/*
		Mono<String> m1 = Mono.just("Mono m1");
		Mono<String> resultMono = m1.map((data)->data.toUpperCase());
		resultMono.subscribe(System.out::println);
		
		Mono<String[]>resultFlatMap = m1.flatMap(dataM1->Mono.just(dataM1.split(" ")));
		resultFlatMap.subscribe((datas)->{
			for(String data:datas) {
				System.out.println(data);
			}
		});
		System.out.println("---------------------------------------");
		Flux<String>stringFlux = m1.flatMapMany(valueM1->Flux.just(valueM1.split(" ")));
		stringFlux.subscribe((data)->{
			System.out.println(data);
		});
		
		*/
		
		/*Delay function*/
		
		/*Mono<String> m1 = Mono.just("Mono m1");
		Mono<String> m2 = Mono.just("Mono m2");
		
		System.out.println(Thread.currentThread().getName());
		Flux<String> stringFlux = m1
				.concatWith(m2)
				.log()
				.delayElements(Duration.ofMillis(3000));
		stringFlux.subscribe((data)->{
			System.out.println(Thread.currentThread().getName());
			System.out.println(data);
		});
		Thread.sleep(5000);
		System.out.println("Main ends ");
		*/
	}

}
