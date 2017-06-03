package com.example.demo.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Led {
	private Long estado;
	private LocalDateTime fecha;
	
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode payload = mapper.createObjectNode();
		payload.put("dateTimeStamp", fecha.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
		payload.put("blinkStatus", estado);
		return payload.toString();
	}
}
