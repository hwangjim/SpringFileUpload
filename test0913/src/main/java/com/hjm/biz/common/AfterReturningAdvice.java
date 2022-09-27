package com.hjm.biz.common;

import org.aspectj.lang.JoinPoint;

import com.hjm.biz.member.MemberVO;

public class AfterReturningAdvice {
	public void printLogAfterReturning(JoinPoint jp,Object returnObj) {
		String methodName=jp.getSignature().getName();
		Object[] args=jp.getArgs();
		
		System.out.println("�������� �ٽɸ޼����: "+methodName);
		System.out.println("����ϴ� ����");
		System.out.println("=====");
		for(Object v:args) {
			System.out.println(v);
		}
		System.out.println("=====");
		
		if(returnObj instanceof MemberVO) {
			MemberVO mvo=(MemberVO)returnObj;
			if(mvo.getRole().equals("ADMIN")) {
				System.out.println("�������Դϴ�.");
			}
			else {
				System.out.println("�Ϲݰ����Դϴ�.");
			}
		}
		System.out.println("�ٽɸ޼����� ��ȯ��: "+returnObj);
	}
}
