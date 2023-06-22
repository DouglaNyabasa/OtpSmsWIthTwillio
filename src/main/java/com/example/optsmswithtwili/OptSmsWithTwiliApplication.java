package com.example.optsmswithtwili;

import com.example.optsmswithtwili.config.TwilioConfig;
import com.twilio.Twilio;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OptSmsWithTwiliApplication {


	private TwilioConfig twilioConfig;

	public OptSmsWithTwiliApplication(TwilioConfig twilioConfig) {
		this.twilioConfig = twilioConfig;
	}

	@PostConstruct
	public void initTwilio(){
		Twilio.init(twilioConfig.getAccountSid(),twilioConfig.getAuthToken());
	}
	public static void main(String[] args) {
		SpringApplication.run(OptSmsWithTwiliApplication.class, args);
	}

}
