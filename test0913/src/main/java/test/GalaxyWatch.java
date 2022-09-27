package test;

import org.springframework.stereotype.Component;

// 이름이 없어도됨 
// 이유는 갤럭시 워치는 이름이 필요가 없다 
// Autowired는 이름이 아닌 타입으로 체크를 하기 때문


public class GalaxyWatch implements Watch{

	@Override
	public void volumeUp() {
		// TODO Auto-generated method stub
		System.out.println("갤럭시 워치 +");
	}

	@Override
	public void volumeDown() {
		// TODO Auto-generated method stub
		System.out.println("갤럭시 워치 - ");
	}
	
}
