package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope(value ="request",proxyMode = ScopedProxyMode.TARGET_CLASS) //request 스코프 HTTP요청 이 들어오면 빈이 생성되고, 요청이 끝나는 시점에 소멸된다.
//MyLogger 의 가짜 프록시 클래스를 만들어 두고, HTTP request 와 상관 없이 가짜 프록시 클래스를 다른 빈에 미리 주입해둘수 있다.
public class MyLogger {   //log 를 출력하기 위한 MyLogger 클래스이다.
    private String  uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + "[ " + message);
    }
    @PostConstruct  //초기화 메서드
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);

    }
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
