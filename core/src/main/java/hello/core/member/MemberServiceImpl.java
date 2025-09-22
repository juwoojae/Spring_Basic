package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {
    /**
     * 생성자 주입을 해주지 않은경우 -> ocp,dip 위반
     * 추상에 의존해야지, 구현체에 의존하면 안된다.
     * */
    // private final MemberRepository memberRepository = new MemoryMemberRepository()

    private final MemberRepository memberRepository;   //멤버로서 선언을 한뒤, 생성자를 통해서 구현체를 유동적으로 주입할수있다

    @Autowired  //자동 의존관계 주입
    public MemberServiceImpl(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
