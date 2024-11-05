package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitV2Spring implements AppInit {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV2Spring.onStartup");

        // AnnotationConfigWebApplicationContext : 스프링 빈을 관리하는 컨테이너.
        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        // 스프링 설정 클래스를 컨테이너에 등록.
        appContext.register(HelloConfig.class);

        /*
        DispatcherServlet : 스프링 MVC에서 모든 HTTP 요청을 처리하는 핵심 서블릿.
        스프링 컨테이너(appContext)를 인자로 받아 스프링 컨테이너에 등록된 빈들을 사용할 수 있음.
        연결된 DispatcherServlet은 애플리케이션의 요청을 스프링 컨테이너에서 정의된 컨트롤러와 매핑해 처리.
        스프링 MVC에서 중앙 요청 처리 역할을 수행하는 특화된 서블릿.
         */
        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        // 디스패처 서블릿 dispatcher를 서블릿 컨테이너에 "dispatcherV2"라는 이름으로 등록
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV2", dispatcher);

        // /spring/* 요청이 디스패처 서블릿(dispatcherV2)을 통하도록 설정.
        servlet.addMapping("/spring/*");

    }

}
