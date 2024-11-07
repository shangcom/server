package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

/**
 * 현재 run configuration에서 톰캣을 servlet container으로 지정했음.
 * 톰캣 서버를 실행하면, 톰캣이 서블릿 컨테이너로 작동함.
 * 서블릿 컨테이너는 META-INF/services/jakarta.servlet.ServletContainerInitializer 파일을 스캔하여,
 * 해당 파일에 등록된 ServletContainerInitializer 구현 클래스의 인스턴스를 자동으로 생성, 초기화 작업에 사용한다.
 * 구현체 각각의 onStartup()를 호출하여 애플리케이션의 초기 설정을 수행하고, 서블릿과 필터를 등록한다.
 * 여기까지 완료되면 ServletContainerInitializer 구현 인스턴스의 역할은 종료.
 * 톰캣이 실행되고 나면, 클라이언트의 HTTP 요청이 톰캣으로 전달되고,
 * 톰캣은 이를 해당 서블릿이나 스프링 MVC의 DispatcherServlet에 전달하여 요청을 처리한다.
 */
/*
@HandlesTypes(Xxx.class)
일반적으로 인터페이스나 추상 클래스를 지정하여, 이를 구현하거나 상속한 모든 클래스를 찾아서
ServletContainerInitializer의 onStartup 메서드에 전달.
 */
@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) {
        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("MyContainerInitV2 c = " + c);
        System.out.println("MyContainerInitV2 ctx = " + ctx);

        /*
         * AppInit을 구현한 클래스를 동적으로 인스턴스화하고,
         * 각 클래스가 자신만의 초기화 작업을 수행하도록 onStartup() 호출.
         */
        for (Class<?> appInitClass : c) {
            try {
                AppInit appInit = (AppInit) appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

}
