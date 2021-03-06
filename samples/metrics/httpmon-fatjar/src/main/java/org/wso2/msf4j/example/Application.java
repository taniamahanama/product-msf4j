/*
 * Copyright (c) 2016, WSO2 Inc. (http://wso2.com) All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wso2.msf4j.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wso2.msf4j.MicroservicesRunner;
import org.wso2.msf4j.example.service.DemoService;
import org.wso2.msf4j.example.service.StudentService;
import org.wso2.msf4j.httpmonitoring.HTTPMonitoringInterceptor;
import org.wso2.msf4j.metrics.MetricReporter;
import org.wso2.msf4j.metrics.MetricsInterceptor;

/**
 * Main Application Class.
 */
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("Starting the Microservice with Metrics");
        new MicroservicesRunner().addInterceptor(new HTTPMonitoringInterceptor().init())
                .addInterceptor(
                        new MetricsInterceptor().init(MetricReporter.CONSOLE, MetricReporter.JMX, MetricReporter.DAS))
                .deploy(new DemoService()).deploy(new StudentService()).start();
    }
}
