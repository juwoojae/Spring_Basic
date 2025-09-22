package hello.core.singleton;

public class SingletonService {
    //statc은 로드시 메모리에 딱한번 올라가고, 전역에서 class명.변수명 으로 접근이 가능하다
    // static final 하면 전역에 적용되는 상수 멘치로 작동함
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){

    }  //이렇게하면 외부에서 객체 생성을 못해준다
 public void logic(){
     System.out.println("싱글톤 객체 로직 호출");
 }

}
