package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.boot.web.reactive.context.GenericReactiveWebApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLOutput;

public class MemberApp {
    public static void main(String[] args) {

        //   MemberService memberService = new MemberServiceImpl();

        //   AppConfig appConfig = new AppConfig();
        //   MemberService memberService = appConfig.memberService();
        //   Appconfig 환경설정 정보를 가지고 스프링이 @Bean 에노테이션 을가진 애들을 스프링 컨테이너에 넣어준다
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);  //가지고올 스프링컨테이너의 메서드 이름,반환타입


        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
