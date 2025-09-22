package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind(){
        AnnotationConfigApplicationContext ac  = new AnnotationConfigApplicationContext(SingleTonBean.class);
        //빈을 조회하기 직전에 이미 빈 등록이 되어 있음
        SingleTonBean singleTinBean1 = ac.getBean(SingleTonBean.class);
        SingleTonBean singleTinBean2 = ac.getBean(SingleTonBean.class);
        System.out.println("singleTinBean1 = " + singleTinBean1);
        System.out.println("singleTinBean2 = " + singleTinBean2);
        assertThat(singleTinBean1).isSameAs(singleTinBean2);

        ac.close();
    }
    @Scope("singleton")
    static class SingleTonBean{
        @PostConstruct
        public void init(){
            System.out.println("SingleTinBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("SingleTinBean.destroy");
        }
    }
}
