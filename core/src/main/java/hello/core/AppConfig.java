package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ApplicationContext 를 스프링 컨테이너라고 한다
 * 기존에는 AppConfig 를 사용해서 객체를 직접 생성하고, DI 를 했지만 , 이제부터는 스프링 컨테이너를 통해서 한다
 * 스프링 컨테이너는 @Configuration 이 붙은 클래스를 설정 정보로 사용한다
 * @Bean 이라 적힌 메서드를 모두 호출한 후 , 반환된 객체를 스프링 컨테이너에 등록한다 -> 이 객체를 스프링 빈이라고 한다
 * 스프링 빈은 @Bean 이 붙은 메서드명을 스프링 빈의 이름으로 사용한다
 * 이전에는 개발자가 AppConfig 객체를 사용해서 직접 조회했지만, 이제부터는 스프링 컨테이너를 통해서 필요한 객체를 꺼내올수 있다
 * applicationContext.getBean() 메서드를 사용한다
 */
 @Configuration
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository() 싱글톤이 깨지나?
    //생성자 주입
    @Bean
    public MemberService memberService() {   //memberService 메서드를 호출하므로서 , 객체가 생성된다
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());  //역할과 구현클래스가 한눈에 보인다
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy(); 아래로 교체하기만 하면 OCP 확장에는 열려있고, 변경에 대해서는 닫혀있게 된다 IOC(Incersion of Control)
        return new RateDiscountPolicy();
    }

}
/**
 * 첫번째 리펙토링 V1 (순수 자바코드)
 * 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 구현 객체를 생성하고, 연결하는 책임을 가지는 별도의 설정클래스를 만든다
 *
 * 1. 다형성을 이용
 * MemberService 의 구현체가 MemberServiceImpl 임을 명시
 * 2. 생성자 주입을 이용
 * MemberServiceImpl 에 생성자 주입으로 의존관계를 넣어준다
 *
 * public MemberService memberService() {
 *  return new MemberServiceImpl(new MemoryMemberRepository());
 *  }
 *  public OrderService orderService() {
 *  return new OrderServiceImpl(
 *      new MemoryMemberRepository(),
 *      new FixDiscountPolicy());
 *  }
 */
