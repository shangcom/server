package hello.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 스프링 설정 클래스.
 * 일반적으로 빈 정의와 환경 설정을 포함한다.
 * 스프링 컨테이너가 관리할 빈과 의존성을 정의.
 */
@Configuration
public class HelloConfig {

    /*
    스프링 컨테이너가 아래 빈 메서드를 통해 HelloController의 생성자를 사용하여 HelloController 객체를 빈으로 등록한다.
     */
    @Bean
    public HelloController helloController() {
        return new HelloController();
    }
}
