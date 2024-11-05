package hello.container;

import jakarta.servlet.ServletContext;

/**
 * 이 인터페이스를 구현한 다양한 클래스를 만들 수 있음
 * 이 클래스들은 각자 다른 방식으로 서블릿을 초기화할 수 있음.
 */
public interface AppInit {
    void onStartup(ServletContext servletContext);
}
