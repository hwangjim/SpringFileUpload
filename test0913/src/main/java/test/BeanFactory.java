package test;

public class BeanFactory {
	// 이름을 들으면 객체를 반환한다.
   public Object getBean(String beanName) {
	   // beanName이 galaxy일 경우
      if(beanName.equals("galaxy")) {
    	  // 새 Galaxy 객체를 준다
         return new Galaxy();
      }
      // beanName이 iphone일 경우
      else if(beanName.equals("iphone")) {
    	  // 새 Iphone 객체를 준다
         return new Iphone();
      }
      // 아무것도 아니라면 null 값 반환
      return null;
   }
}