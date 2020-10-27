package com.nrson.common.util;

import java.util.Arrays;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;


public class ExceptionUtil {
	static final int SHORTEN_LENGTH_OF_STACK_TRACE_LOG = 700;
	static final String[] PACKAGE_PREFIXS_FOR_SKIP_TRACE = new String[] {
			"sun.reflect.",
			"java.lang.reflect.",
			"org.springframework.cglib.proxy.",
			"org.springframework.aop.framework.",
			"org.springframework.aop.aspectj."
	};
	
	public static StringBuilder getStackTraceShortenStrings(Throwable e) {
		return getStackTraceShortenStrings(e, SHORTEN_LENGTH_OF_STACK_TRACE_LOG);
	}
	
	public static StringBuilder getStackTraceShortenStrings(Throwable e, int maxLength) {
		final StringBuilder logBuf = getStackTraceStrings(e);
		
		if(maxLength < 3) {
			maxLength = 3;
		}
		
		final int logLength = logBuf.length();
		final int remains = logLength - maxLength;
		if(remains > 0) {
			logBuf.setLength(maxLength - 3);
			logBuf.append("...");
		}
		
		return logBuf;
	}
	
	
	public static StringBuilder getStackTraceStrings(Throwable e) {
		return getStackTraceStrings(e, SkipStackTrace::notMatch);
	}
	
	public static StringBuilder getStackTraceStrings(Throwable e, final Predicate<String> match) {
		final StringBuilder logBuf = new StringBuilder();
		final StackTraceElement[] stacks = e.getStackTrace();
		
		logBuf.append(e).append(' ');
		
		Arrays.stream(stacks)
		       .map(String::valueOf)
		       .filter(match)
		       .forEachOrdered(st -> logBuf.append(st).append(' '));
		
		return logBuf;
	}
	
	
	static class SkipStackTrace {
		static boolean notMatch(final String clazzname) {
			return !(Arrays.stream(PACKAGE_PREFIXS_FOR_SKIP_TRACE)
					.anyMatch(prefix -> StringUtils.startsWith(clazzname, prefix)));
		}
	}

}
