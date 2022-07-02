package ru.red.config;

import org.jeasy.random.EasyRandom;

import javax.enterprise.inject.Produces;

public class EasyRandomConfiguration {
    @Produces
    public EasyRandom easyRandom() {
        return new EasyRandom();
    }
}
