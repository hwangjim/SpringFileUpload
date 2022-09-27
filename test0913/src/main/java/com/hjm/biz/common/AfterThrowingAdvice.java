package com.hjm.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
@Aspect
public class AfterThrowingAdvice {
	@AfterThrowing(pointcut="PointcutCommon.bPointcut()", throwing="exceptObj")
	public void printLogAfterThrowing(JoinPoint jp,Exception exceptObj) {
		String methodName=jp.getSignature().getName();
		Object[] args=jp.getArgs();
		
		System.out.println("�������� �ٽɸ޼����: "+methodName);
		System.out.println("����ϴ� ����");
		System.out.println("=====");
		for(Object v:args) {
			System.out.println(v);
		}
		System.out.println("=====");
				
		System.out.println("�߻��� ����: "+exceptObj.getMessage());
		if(exceptObj instanceof IllegalArgumentException) {
			System.out.println("�ùٸ������� ���ڰ��� ����߽��ϴ�...");
		}
		else if(exceptObj instanceof NumberFormatException) {
			System.out.println("���� ������ �ƴ� ���� ����߽��ϴ�...");
			// 12,000 -> , �������� replace() ó���ؼ� ����غ�~~
		}
		else if(exceptObj instanceof Exception) {
			System.out.println("���ܰ� �߻��߽��ϴ�...");
		}
		else {
			System.out.println("Ȯ�ε������� ������ �߻��߽��ϴ�!!!");
		}
	}
}
