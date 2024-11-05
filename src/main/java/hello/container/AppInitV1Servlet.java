package hello.container;

import hello.servlet.HelloServlet;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;

/**
 * 서블릿 아님.
 * 그냥 서블릿을 등록하는 역할이라서 이름에 Servlet 붙인 것.
 * 꼭 하나의 서블릿만 초기화하는게 아니라, 여러 서블릿을 초기화할 수 있음.
 */
public class AppInitV1Servlet implements AppInit {
    @Override
    public void onStartup(ServletContext servletContext) {
        System.out.println("AppInitV1Servlet.onStartup");
        System.out.println("helloServlet 등록");

        // 순수 서블릿 코드 등록
        ServletRegistration.Dynamic helloServlet =
                servletContext.addServlet("helloServlet", new HelloServlet());
        helloServlet.addMapping("/hello-servlet");
    }
}
