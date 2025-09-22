package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Provider;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.expression.spel.CodeFlow;


import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {

    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);  //또 다시 조회를 하면 그때 스프링 빈으로 등록하고, 빈의 인스턴스를 조회 해준다
        prototypeBean2.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
    }
    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(
                ClientBean.class,
                PrototypeBean.class
        );
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }
    @Scope("singleton")
    static  class ClientBean{ //PrototypeBean 에 의존한다

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;//스프링 빈에 자동으로 등록된다.
        //Spring framework 의존하지 않는다.
        public int logic(){
            PrototypeBean prototypeBean = prototypeBeanProvider.get();//스프링 컨테이너에서 프로토타입 빈을 반환한다 DL
            prototypeBean.addCount();
            return prototypeBean.getCount();
        }
    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count =0;

        public void addCount(){
            count++;
        }
        public int getCount() {
            return count;
        }
        @PostConstruct  //의존성 주입 완료후에
        public void init(){
            System.out.println("PrototypeBean.init" + this);
        }
        @PreDestroy
        public void destory(){
            System.out.println("PrototypeBean.destory");
        }
    }
}
