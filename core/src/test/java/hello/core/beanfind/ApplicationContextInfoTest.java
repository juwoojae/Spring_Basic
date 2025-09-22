package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();//빈 정의된 이름 등록하기
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName); //타입을 모르기때문에 Object로 받아온다
            System.out.println("name = " + beanDefinitionName + "Object "+ bean);
        }

    }
    @Test
    @DisplayName("에플리케이션 빈 출력하기")
    void findApplicationBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();//빈 정의된 이름 등록하기
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//빈에 대한 정보들

            //스프링 내부의 빈이  내가 직접 컨테이너에 등록한 빈: Role ROLE_APPLICATION
            //스프링이 내부에서 사용하는 빈: Role ROLE_INFRSTRUCTURE
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName); //타입을 모르기때문에 Object로 받아온다
                System.out.println("name = " + beanDefinitionName + "Object "+ bean);
            }
        }

    }

}
