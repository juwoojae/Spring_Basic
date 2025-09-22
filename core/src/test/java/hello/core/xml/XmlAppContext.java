package hello.core.xml;
import hello.core.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;import static org.assertj.core.api.Assertions.*;
public class XmlAppContext {
    @Test
    void xmlAppContext() {
        ApplicationContext ac = new
                GenericXmlApplicationContext("appConfig.xml");

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);
        //assertThat(service)의 리턴 타입은
        //AbstractObjectAssert<?, ServiceType> 같은 AssertJ 검증 객체이다.
        //이 검증 객체는 내부에 service 인스턴스를 들고 있으며, .isInstanceOf(MemberService.class) 같은 메서드를 호출하면
        //내부에 가진 service가 MemberService 타입인지 검사하고
        //조건을 만족하면 아무 문제 없이 넘어가고,만족하지 않으면 예외(AssertionError)를 던진다.

    }
}