package spring.starter.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.starter.config.ApplicationProperties;
import spring.starter.service.GreetingService;

import java.time.ZoneId;
import java.time.format.TextStyle;
import java.util.Locale;

@Service
public class GreetingServiceImpl implements GreetingService {

    @Autowired
    private ApplicationProperties properties;

    @Override
    public String sayGreeting() {
        ZoneId zoneId = ZoneId.of(properties.getTimezone());
        return properties.getWelcomeText() + ", " + zoneId.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + ", " + properties.getCurrency();
    }
}
