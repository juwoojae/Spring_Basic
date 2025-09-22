package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * 객체지향 5대 원칙
 * 단일 책임 원칙(SRP, Single Responsibility Principle): 클래스는 한 가지 기능만 가져야 하며, 변경 이유가 한 가지여야 한다
 * 개방-폐쇄 원칙(OCP, Open-Closed Principle): 소프트웨어 구조는 확장에는 열려 있고, 변경에는 닫혀 있어야 한다
 * 리스코프 치환 원칙(LSP, Liskov Substitution Principle): 자식 클래스는 부모 클래스를 완전히 대체할 수 있어야 한다
 * 인터페이스 분리 원칙(ISP, Interface Segregation Principle): 클래스는 자신이 사용하지 않는 인터페이스를 구현하지 않아야 한다.
 * 의존 역전 원칙(DIP, Dependency Inversion Principle): 구체적 구현에 의존하지 않고, 추상화에 의존해야 합니다.
 */
@Component//컴포넌트 스캔을 통해서 스프링빈으로 등록한다 - > 생성자 호출 -> @Autowired 가 있으면 스프링 컨테이너에서 같은 타입의 memberRepository를 꺼내서 의존관계 주입을 해준다.
@RequiredArgsConstructor //모든 필드의 final 이 붙은 속성에 매개변수로 받는 생성자를 자동으로 만들어주는 어노테이션
public class OrderServiceImpl implements OrderService {
    /**
     * <생성자 주입을 해주지 않았을때>
     * // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
     *  private final DiscountPolicy discountPolicy =  new RateDiscountPolicy(); 로 구현체를 돌려야하는데
     *  이렇게 되면 추상(인터페이스) 뿐만이 아니라 구현체에도 의존하고있다 ->OCP,DIP위반
     */
    //private final 는 생성자에서 무조건 값이 할당이 되어야한다
    private final MemberRepository memberRepository;//회원 저장소
    private final DiscountPolicy discountPolicy;   //할인 정책 결정

    //생성자 주입
    //생성자가 하나만있으면 @Autowired 생략이 가능하다
    //@Autowired
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}