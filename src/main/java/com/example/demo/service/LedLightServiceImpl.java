package com.example.demo.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Service
public class LedLightServiceImpl extends RaspiPin implements LedLightService  {
	
	@Value("${iotled.pin.number}")
	private int pinNumber;
	
	@Value("${iotled.pin.name}")
	private String pinName;
	
	private static Logger LOG = Logger.getLogger(LedLightServiceImpl.class);
	//private static final Pin PIN = RaspiPin.GPIO_29;
	private static GpioPinDigitalOutput pin = null;

	@Override
	public void on() {
		createPin();
		if( pin.getState().isHigh()){
			LOG.info("Already ON!");
		} else  {
			pin.high();
		}
		//pin.toggle();
	}

	@Override
	public void off() {
		createPin();
		if( pin.getState().isLow()){
			LOG.info("Already LOW!");
		} else  {
			pin.low();
		}
	}
	
	private void createPin() {
		if(pin == null) {
			Pin PIN = createDigitalAndPwmPin(pinNumber, pinName);
			GpioController gpio = GpioFactory.getInstance();
			pin = gpio.provisionDigitalOutputPin(PIN,"My LED",PinState.LOW);
		}
	}
	
	

}
