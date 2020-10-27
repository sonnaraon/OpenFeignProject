package com.nrson.common.component;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.nrson.common.dto.StandardResponseDTO;
import com.nrson.common.enums.ErrorCode;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageComponent {

	
	public StandardResponseDTO<?> makeErrorMessage(String code, String message, String[] params, Throwable e) {
		final MappingMessage mappingMessage = getMappingMessage(code, message);
		final String messageCode = mappingMessage.getMessageCode();
		final String messageContent = mappingMessage.getMessageContent();
		
		if(StringUtils.isEmpty(messageContent)) {
			final StandardResponseDTO<?> standardResponseDTO = StandardResponseDTO.fail(code, message, e);
			log.error("not found message for code[={}], <-- {}", code, message);
			return standardResponseDTO;
		}
		
		if(StringUtils.isAllEmpty(params)) {
			return StandardResponseDTO.fail(messageCode, messageContent, e);
		}
		
		final String messageParam = MessageFormat.format(messageContent, (Object[]) params);
		return StandardResponseDTO.fail(messageCode, messageParam, e);		
	}
	

	
	private MappingMessage getMappingMessage(final String messageCode, final String messageContent) {
		
		String msgCont = messageContent;
		ErrorCode eCode = ErrorCode.getEnum(messageCode);

		if(StringUtils.isBlank(messageContent)) {
			msgCont = eCode.getErrMsg();
		}
		
		return MappingMessage.builder()
				.messageCode(eCode.getCode())
				.messageContent(msgCont)
				.build();
	}
	
	
	@Getter
	@Builder
	private static class MappingMessage {
		private String messageCode;
		private String messageContent;
	}
}
