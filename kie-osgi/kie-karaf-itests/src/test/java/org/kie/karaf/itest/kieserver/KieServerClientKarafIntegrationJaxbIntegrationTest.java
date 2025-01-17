/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.karaf.itest.kieserver;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.karaf.itest.AbstractKarafIntegrationTest;
import org.kie.karaf.itest.util.PaxExamWithWireMock;
import org.kie.server.api.marshalling.MarshallingFormat;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.LogLevelOption;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.configureConsole;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.logLevel;


@RunWith(PaxExamWithWireMock.class)
@ExamReactorStrategy(PerClass.class)
@Ignore("DROOLS-6581")
public class KieServerClientKarafIntegrationJaxbIntegrationTest extends BaseKieServerClientKarafIntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(KieServerClientKarafIntegrationJaxbIntegrationTest.class);
    public static final String HOST = "localhost";
    public static final int PORT = 59400;
    public static final String TYPE = "jaxb";

    public KieServerClientKarafIntegrationJaxbIntegrationTest() {
        serverUrl = System.getProperty("org.kie.server.itest.server.url", "http://" + HOST + ":" + PORT);
    }

    @Test
    public void testListContainersJAXB() throws Exception {
        testListContainers(MarshallingFormat.JAXB);

    }

    @Test
    public void testCompleteInteractionWithKieServerJAXB() throws Exception {

        testCompleteInteractionWithKieServer(MarshallingFormat.JAXB);

    }

    @Configuration
    public static Option[] configure() {

        return new Option[]{
                // Install Karaf Container
                AbstractKarafIntegrationTest.getKarafDistributionOption(),

                // Don't bother with local console output as it just ends up cluttering the logs
                configureConsole().ignoreLocalConsole(),
                // Force the log level to INFO so we have more details during the test.  It defaults to WARN.
                logLevel(LogLevelOption.LogLevel.WARN),

                // Option to be used to do remote debugging
//                  debugConfiguration("5005", true),

                // Load kie-server-client
                AbstractKarafIntegrationTest.loadKieFeatures("kie-server-client")
        };
    }




}