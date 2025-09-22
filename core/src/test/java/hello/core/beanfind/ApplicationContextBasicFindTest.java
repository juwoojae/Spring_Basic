package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  //static
    }
    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType(){
        MemberService memberService = ac.getBean( MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  //static
    }
    @Test
    @DisplayName("구현체 로 조회")  //인터페이스 객체말고, 구현체로 Bean을 조회할수도 있다
    void findBeanByName2(){  //역할과 구현이 분리되어있어야 하고, 역할에 의존해야 한다.
        MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);  //static
    }
    @Test
    @DisplayName("빈 이름으로 조회X")
    void findBeanByNameX(){
        //ac.getBean("xxxxx", MemberService.class);
        //MemberService xxxxx = ac.getBean("xxxxx", MemberService.class);//.NoSuchBeanDefinitionException
        assertThrows(NoSuchBeanDefinitionException.class,
                () ->ac.getBean("xxxxx", MemberService.class)); //assertThrows 는 예외를 테스트하는 메서드
    }
}
