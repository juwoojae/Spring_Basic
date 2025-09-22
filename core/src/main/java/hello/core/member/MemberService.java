package hello.core.member;

public interface MemberService {
    void join(Member member);   //Member 를 입력 받으면 Memberrepository 의 구현체의 store(임시dp)에 저장하는 함수

    Member findMember(Long memberId);   //id 를 받으면 Member를 찾아서 리턴하는 함수

}
