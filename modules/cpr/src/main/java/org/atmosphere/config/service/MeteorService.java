/*
 * Copyright 2008-2025 Async-IO.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.atmosphere.config.service;

import org.atmosphere.cache.DefaultBroadcasterCache;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.AtmosphereResourceEventListener;
import org.atmosphere.cpr.BroadcastFilter;
import org.atmosphere.cpr.Broadcaster;
import org.atmosphere.cpr.BroadcasterCache;
import org.atmosphere.cpr.DefaultBroadcaster;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a {@link org.atmosphere.cpr.Meteor} implementation so Atmosphere can install it at runtime.
 *
 * @author Jeanfrancois Arcand
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MeteorService {

    /**
     * The url mapping for the associated {@link org.atmosphere.cpr.Meteor}
     *
     * @return url mapping for the associated {@link org.atmosphere.cpr.Meteor}
     */
    String path() default "/";

    /**
     * A list of {@link org.atmosphere.cpr.BroadcastFilter}
     */
    Class<? extends BroadcastFilter>[] broadcastFilters() default {};

    /**
     * The {@link org.atmosphere.cpr.Broadcaster} class name
     *
     * @return The {@link org.atmosphere.cpr.Broadcaster} class name
     */
    Class<? extends Broadcaster> broadcaster() default DefaultBroadcaster.class;

    /**
     * Set to true if this {@link org.atmosphere.cpr.AtmosphereHandler} supports sessions
     *
     * @return true if sessions are supported.
     */
    boolean supportSession() default false;

    /**
     * Atmosphere's configuration that will be passed to the associated {@link org.atmosphere.cpr.AtmosphereHandler}. Configuration
     * name and value is delimited by "=", and different configuration lines are separated by comma.
     */
    String[] atmosphereConfig() default {};

    /**
     * A list of {@link org.atmosphere.cpr.AtmosphereInterceptor} to install
     */
    Class<? extends AtmosphereInterceptor>[] interceptors() default {};

    /**
     * The {@link org.atmosphere.cpr.BroadcasterCache} class name. By default, a no-ops {@link DefaultBroadcasterCache}
     * is installed. It is strongly recommended to install the {@link org.atmosphere.cache.UUIDBroadcasterCache} to prevent
     * messages being lost.
     *
     * @return The {@link org.atmosphere.cpr.Broadcaster} class name
     */
    Class<? extends BroadcasterCache> broadcasterCache() default DefaultBroadcasterCache.class;

    /**
     * Add {@link org.atmosphere.cpr.AtmosphereResourceEventListener} to track internal events.
     */
    Class<? extends AtmosphereResourceEventListener>[] listeners() default {};

}
