package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {
    @Test
    void findAllBean(){  //모든빈을 조회해야할때 Map,List 를 이용해서
        ApplicationContext ac = new AnnotationConfigApplicationContext(
                AutoAppConfig.class,
                DiscountService.class
        );
        DiscountService discountService = ac.getBean(DiscountService.class);//discountService 객체
        Member member = new Member(1L, "userA", Grade.VIP);//멤버객체생성
        int discountPrice = discountService.discount(member, 10000, "rateDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);
    }
    //DiscountService 는 스프링 빈으로 등록되지 않는다
    //대신 설정정보로 보고, 내부의 @Bean 이있는지 확인해서 등록하기위해 refliection 으로 인스턴스를 생성하고
    //그과정에 생성자가 호출이 된다.
    static class DiscountService{//DiscountService 는 DiscountPolicy 에 의존한다
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policies;
        @Autowired //의존관계 주입
        //Autowired가 알아서DiscountPolicy들을찾아서 자동으로 넣어준다.
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }

        public int discount(Member member, int price, String discountCode) {

            DiscountPolicy discountPolicy = policyMap.get(discountCode); //rateDiscountPolicy /FixDiscountPolicy

            System.out.println("discountCode = " + discountCode);
            System.out.println("discountPolicy = " + discountPolicy);

            return discountPolicy.discount(member,price);  //할인된 가격 리턴
        }
    }
}
