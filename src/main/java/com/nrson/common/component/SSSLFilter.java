package com.nrson.common.component;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import com.nrson.common.constant.SupportConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SSSLFilter implements Filter {
	
	private final String[] excludeUri = new String[] {
			"/static"
	};
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		if(request instanceof HttpServletRequest) {
			final HttpServletRequest httpRequest = (HttpServletRequest) request;
			
			Optional.ofNullable(httpRequest.getRequestedSessionId())
			        .ifPresent(sessid -> MDC.put(SupportConstant.MDC_SESSION_NODE, sessid));
			
			String reqURI = httpRequest.getRequestURI();
			final boolean nologRequestInfo = StringUtils.containsAny(reqURI, excludeUri);
			
			request.setAttribute(SupportConstant.REQUEST_NOLOG_REQUEST_INFO_ATTRB, nologRequestInfo);
			
			if(!nologRequestInfo) {
				log.info("==== REQ-URI :: {}", httpRequest.getRequestURI());
			}
			
		}
		
		try {
			filterChain.doFilter(request, response);
		}finally {
			final boolean nologRequestInfo = Optional
					.ofNullable((Boolean) request.getAttribute(SupportConstant.REQUEST_NOLOG_REQUEST_INFO_ATTRB))
					.orElse(Boolean.TRUE)
					.booleanValue();
			if(request instanceof HttpServletRequest && !nologRequestInfo) {
				log.info("==== RES-URI :: {}", ((HttpServletRequest)request).getRequestURI());
			}
		}
	}

}
