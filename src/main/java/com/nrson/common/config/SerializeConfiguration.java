package com.nrson.common.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.serializer.Serializer;

@Configuration
@ComponentScan(basePackageClasses = Serializer.class)
public class SerializeConfiguration {

}
