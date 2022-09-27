package test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ip")
public class Iphone implements Phone{
	
	@Autowired
	
	private Watch watch;	// 객체 멤버변수인 watch로 인해 의존관계 성립
							// 의존성 주입(DI)이 필요한 상태 
	private int number;
	public Iphone() {
		System.out.println("아이폰 객체 생성");
	}
	
	public Watch getWatch() {
		return watch;
	}

	public void setWatch(Watch watch) {
		this.watch = watch;
		System.out.println("setter 호출됨1");
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		System.out.println("setter 호출됨2");
	}

	// 생성자 인젝션
//	public Iphone(Watch watch) {
//		System.out.println("아이폰 객체 생성 완료 2 ");
//		this.watch = watch;
//	}
//	public Iphone(Watch watch, int number) {
//		System.out.println("아이폰 객체 생성 완료 3 ");
//		this.watch = watch;
//		this.number = number;
//	}
	public void powerOn() {
		System.out.println("아이폰 전원 on : " + number);
	}
	public void powerOff() {
		System.out.println("아이폰 전원 off");
	}
	public void volumeUp() {
		watch.volumeUp();
	}
	public void volumeDown() {
		watch.volumeDown();
	}
	
}
