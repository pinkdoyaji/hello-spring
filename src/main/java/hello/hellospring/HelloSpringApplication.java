package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* SpringBootApplication 기준으로 이 패키지만 Spring이 뒤져서 찾아낸다.
* */
@SpringBootApplication
public class HelloSpringApplication {

	public static void main(String[] args) {SpringApplication.run(HelloSpringApplication.class, args);}

}
