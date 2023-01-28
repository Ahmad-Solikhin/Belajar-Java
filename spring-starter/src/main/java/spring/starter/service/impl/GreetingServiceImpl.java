package spring.starter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.starter.config.ApplicationProperties;
import spring.starter.config.CloudProperties;
import spring.starter.service.GreetingService;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private ApplicationProperties properties;

    private CloudProperties cloudProperties;

    public GreetingServiceImpl(CloudProperties cloudProperties) {
        this.cloudProperties = cloudProperties;
    }

    @Override
    public String sayGreeting() {
        System.out.println(cloudProperties.getApiKey());
        ZoneId zoneId = ZoneId.of(properties.getTimezone());
        return properties.getWelcomeText() + ", " + zoneId.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", " + properties.getCurrency();
    }
}
