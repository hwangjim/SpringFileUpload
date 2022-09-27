package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("gp")
public class Galaxy implements Phone{
	// 워치 멤버변수 , 배터리 변수 등 부여 
	// 이후 생성자 , setter 주입
	@Autowired

	private Watch watch;
	private int number;
	
	public Galaxy() {
		
	}
	public Galaxy(Watch watch, int number) {
		this.watch = watch;
		this.number = number;
	}
	
	public Watch getWatch() {
		return watch;
	}
	public void setWatch(Watch watch) {
		this.watch = watch;
		System.out.println("setter 갤럭시 워치 1");
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
		System.out.println("setter 갤럭시 워치 2");
	}
//	public void initMethod() {
//		System.out.println("객체 초기화 작업 처리 메소드");
//	}
//	public void destroyMethod() {
//		System.out.println("객체 메모리를 해제할 때 호출하는 메소드");
//	}
	@Override
	public void powerOn() {
		System.out.println("갤럭시 전원 on");
	}
	@Override
	public void powerOff() {
		System.out.println("갤럭시 전원 off");
	}
	@Override
	public void volumeUp() {
		watch.volumeUp();
	}
	@Override
	public void volumeDown() {
		watch.volumeDown();
	}
}
