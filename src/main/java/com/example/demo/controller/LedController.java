package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Led;
import com.example.demo.service.LedLightService;
import com.example.demo.util.mqtt.MqttUtil;

@RestController
public class LedController {
	@Autowired
	private LedLightService ledService;
	
	@RequestMapping("/on")
	public void on() {
		ledService.on();
		MqttUtil.getInstance().mqttConnectNPublishNSubscribe(Led.builder().estado(1l).fecha(LocalDateTime.now()).toString());
	}
	
	@RequestMapping("/off")
	public void off() {
		ledService.off();
		MqttUtil.getInstance().mqttConnectNPublishNSubscribe(Led.builder().estado(0l).fecha(LocalDateTime.now()).toString());
	}
}
