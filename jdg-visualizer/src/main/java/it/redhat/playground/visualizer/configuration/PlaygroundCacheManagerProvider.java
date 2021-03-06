/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package it.redhat.playground.visualizer.configuration;

import it.redhat.playground.configuration.PlaygroundConfiguration;
import org.infinispan.manager.DefaultCacheManager;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.util.logging.Logger;

@Startup
@Singleton
public class PlaygroundCacheManagerProvider {

    @Inject
    private Logger log;

    private DefaultCacheManager manager;

    @PostConstruct
    public void startup() {
        log.info("DefaultCacheManager does not exist - constructing a new one");
        manager = new PlaygroundConfiguration().getCacheManager();
        log.info("Starting Cache");
        manager.getCache().start();
    }

    public DefaultCacheManager getCacheManager() {
        return manager;
    }

    @PreDestroy
    public void cleanUp() {
        manager.stop();
        manager = null;
    }

}
