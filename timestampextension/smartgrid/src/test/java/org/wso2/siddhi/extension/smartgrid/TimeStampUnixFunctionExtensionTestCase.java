/*
 * Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.siddhi.extension.string;

import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.wso2.siddhi.core.ExecutionPlanRuntime;
import org.wso2.siddhi.core.SiddhiManager;
import org.wso2.siddhi.core.event.Event;
import org.wso2.siddhi.core.stream.output.StreamCallback;
import org.wso2.siddhi.core.stream.input.InputHandler;
import org.wso2.siddhi.core.util.EventPrinter;
import org.apache.log4j.Logger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.concurrent.atomic.AtomicInteger;

public class TimeStampUnixFunctionExtensionTestCase {

    private static final Logger log = Logger.getLogger(TimeStampUnixFunctionExtensionTestCase.class);
    private AtomicInteger count = new AtomicInteger(0);
    private volatile boolean eventArrived;

    @Before
    public void init() {
        count.set(0);
        eventArrived = false;
    }

    @Test
    public void testSplitFunctionExtension() throws InterruptedException {
        log.info("SmartGridUnixTestCase TestCase");
        SiddhiManager siddhiManager = new SiddhiManager();

        String inStreamDefinition = "define stream inputStream (tt string); define stream outputStream (updateTimeStamp long);";
        String query ="@info(name = 'query1') " + "from inputStream " + "select smartgrid:timeStampUnix(tt) as updateTimeStamp insert into outputStream;";
        ExecutionPlanRuntime executionPlanRuntime = siddhiManager.createExecutionPlanRuntime(inStreamDefinition + query);

        executionPlanRuntime.addCallback("outputStream", new StreamCallback() {
            @Override
            public void receive(org.wso2.siddhi.core.event.Event[] events) {
                //EventPrinter.print(events);
                for(Event ev : events){
                   System.out.println("-->" + ev.getData()[0]);
                }
            }
        });

        InputHandler inputHandler = executionPlanRuntime.getInputHandler("inputStream");
        executionPlanRuntime.start();
        inputHandler.send(new Object[]{"2000-08-01 00:00:00"});
        inputHandler.send(new Object[]{"2000-08-01 00:15:00"});
        inputHandler.send(new Object[]{"2000-08-09 12:00:00"});
        Thread.sleep(2000);

        executionPlanRuntime.shutdown();
    }
}
