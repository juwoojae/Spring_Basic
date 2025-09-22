package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //구현체로도 스프링빈 조회가 가능하다
        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberrepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository= " + memberRepository2);
        System.out.println("memberRepository = " + memberrepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberrepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberrepository);

    }
    @Test
    void configurationDeep(){
        ApplicationContext ac= new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);//AppConfig 자체도 스프링 빈에 올라간다

        System.out.println("bean = " + bean.getClass());//Object 클래스의 getClass()  ->    메서드Class<?>반환    주로 리플렉션(Reflection), 런타임 타입 확인에 사용

    }
}