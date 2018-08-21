package com.antipov.redditreader.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * * Annotation for providing App Context
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationContext {
}
