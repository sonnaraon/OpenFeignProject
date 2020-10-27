package com.nrson.common.config;

import java.util.Collections;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.MatchAlwaysTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

@Aspect
@Configuration
public class TxAdviceInterceptor {
	
	private static final String TX_METHOD_NAME = "*";
	// private static final int TX_METHOD_TIMEOUT = 5;
	private static final String AOP_POINTCUT_EXPRESSION = "execution(* com..*Service.*(..))";

	@Autowired
	private PlatformTransactionManager transactionManager;
	
	@Bean
	public TransactionInterceptor txAdvice() {
		final MatchAlwaysTransactionAttributeSource source = new MatchAlwaysTransactionAttributeSource();
		final RuleBasedTransactionAttribute transactionAttribute = new RuleBasedTransactionAttribute();
		
		transactionAttribute.setName(TX_METHOD_NAME);
		transactionAttribute.setRollbackRules(
				Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
		
		// 이 기능을 지원하지 못하는 일부 트랜잭션 매니져는 예외를 발생시킬 수 있어서 일단 주석 처리 필요 시 설정
		// transactionAttribute.setTimeout(TX_METHOD_TIMEOUT);
		source.setTransactionAttribute(transactionAttribute);
		
		final TransactionInterceptor txAdvice = new TransactionInterceptor(transactionManager, source);
		
		return txAdvice;		
	}
	
	@Bean
	public Advisor txAdviceAdvisor() {
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		
		pointcut.setExpression(AOP_POINTCUT_EXPRESSION);
		
		return new DefaultPointcutAdvisor(pointcut, txAdvice());
	}
}
