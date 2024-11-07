package hello.container;

import hello.spring.HelloConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * AppInitV3SpringMvc에서는 스프링의 표준 초기화 방식인 WebApplicationInitializer를 사용하기 때문에
 * ServletContainerInitializer와 META-INF/services 설정이 필요하지 않다.
 * 스프링이 제공하는 SpringServletContainerInitializer가 WebApplicationInitializer 구현체를
 * 자동으로 탐색하고 실행하여, 초기화 과정을 대신 관리하기 때문.
 */
public class AppInitV3SpringMvc implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV3SpringMvc.onStartup");

        AnnotationConfigWebApplicationContext appContext = new AnnotationConfigWebApplicationContext();
        appContext.register(HelloConfig.class);

        DispatcherServlet dispatcher = new DispatcherServlet(appContext);

        // 디스패처 서블릿 dispatcher를 서블릿 컨테이너에 "dispatcherV3"라는 이름으로 등록
        ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcherV3", dispatcher);

        // 모든 요청이 디스패처 서블릿(dispatcherV3)을 통하도록 설정.
        servlet.addMapping("/");
    }
}
