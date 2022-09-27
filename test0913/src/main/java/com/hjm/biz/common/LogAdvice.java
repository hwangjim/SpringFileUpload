package com.hjm.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class LogAdvice {
	@Before("PointcutCommon.aPointcut()")
	public void printLog(JoinPoint jp) {
		String methodName=jp.getSignature().getName();
		// ���� �������� ����Ʈ��(�ٽɷ���,CRUD)�� �޼����
		Object[] args=jp.getArgs();
		// ���� �������� ����Ʈ��(�ٽɷ���,CRUD)�� ����ϴ� ���ڵ��� ����
		
		System.out.println("�������� �ٽɸ޼����: "+methodName);
		System.out.println("����ϴ� ����");
		System.out.println("=====");
		for(Object v:args) {
			System.out.println(v);
		}
		System.out.println("=====");
	}
}
