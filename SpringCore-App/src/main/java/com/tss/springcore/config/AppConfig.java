package com.tss.springcore.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.ComponentScan;

@Configurable
@ComponentScan(basePackages = "com.tss.springcore")
public class AppConfig {
}