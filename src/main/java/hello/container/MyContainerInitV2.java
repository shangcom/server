package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

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
