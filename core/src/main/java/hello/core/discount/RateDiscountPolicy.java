package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// 정률할인 구현체 구현
@Component
//@Primary 의존관계 주입에서 빈 상충이 일어날때 @Primary 가 있으면
// 다재끼고 얘가 1등
@Primary
public class RateDiscountPolicy implements DiscountPolicy{
    private int discountPercent =10;

    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent/100;
        } else {
            return 0;
        }
    }
}
