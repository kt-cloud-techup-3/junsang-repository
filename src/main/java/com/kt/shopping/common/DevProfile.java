package com.kt.shopping.common;

import org.springframework.context.annotation.Profile;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Profile("dev")
@Retention(RetentionPolicy.RUNTIME)
public @interface DevProfile {
}
