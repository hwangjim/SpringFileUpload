package com.hjm.biz.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

// around�� ����� advice�� �ݵ�� pjp�� input���� �������Ѵ�!
//  ex) ���� ���� Ŭ����
public class AroundAdvice {
	public Object printLogAround(ProceedingJoinPoint pjp) throws Throwable {
		String methodName=pjp.getSignature().getName();
		System.out.println("�������� �ٽɸ޼����: "+methodName);
		
		StopWatch sw=new StopWatch();
		sw.start();
		Object returnObj=pjp.proceed(); // �����ؾ��� ����Ʈ��
		// pjp.proceed()�� ���� ����Ͻ��޼��尡 �����!
		sw.stop();
		System.out.println("����ð�: "+sw.getTotalTimeMillis()+"ms");
		
		return returnObj;
	}
}
