package com.antipov.redditreader.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Annotation for providing Activity Context
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityContext {
}
