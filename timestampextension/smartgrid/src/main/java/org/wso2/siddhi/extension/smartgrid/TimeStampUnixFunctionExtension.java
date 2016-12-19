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

package org.wso2.siddhi.extension.smartgrid;

import org.wso2.siddhi.core.config.ExecutionPlanContext;
import org.wso2.siddhi.core.exception.ExecutionPlanRuntimeException;
import org.wso2.siddhi.core.executor.ExpressionExecutor;
import org.wso2.siddhi.core.executor.function.FunctionExecutor;
import org.wso2.siddhi.query.api.definition.Attribute;
import org.wso2.siddhi.query.api.exception.ExecutionPlanValidationException;
import org.apache.log4j.Logger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeStampUnixFunctionExtension extends FunctionExecutor {
    private static final Logger log = Logger.getLogger(TimeStampUnixFunctionExtension.class);
    private Attribute.Type returnType = Attribute.Type.LONG;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ExecutionPlanContext executionPlanContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new ExecutionPlanValidationException("Invalid no of arguments passed to smartgrid:timeStampUnix() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }

        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.STRING) {
            throw new ExecutionPlanValidationException("Invalid parameter type found for the first argument of function, " +
                    "required " + Attribute.Type.STRING + ", but found " + attributeExpressionExecutors[0].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        return null;
    }

    @Override
    protected Object execute(Object data) {
      /*  String source = (String) data;
        String splitStrArray[] = source.split(" ");

        int dateFifteenMins = Integer.parseInt(splitStrArray[0].split("-")[2]) * 96; //every 24 hour has 4 * 24 number of 15 minutes slots

        splitStrArray = splitStrArray[1].split(",");
        splitStrArray = splitStrArray[0].split(":");
        int mod = Integer.parseInt(splitStrArray[1])%15;
        int minutesValue = Integer.parseInt(splitStrArray[1])/15;


        if(mod > 7.5f){
            minutesValue++;
        }

        return new Long(dateFifteenMins+(Integer.parseInt(splitStrArray[0]) * 4 + minutesValue));
	*/
	String source = (String) data;

	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date dateTime = null;
        df.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));
	try {
	dateTime = df.parse(source);
	//dateTime=Date(Long(dateTime)/1000);
	} catch (ParseException e) {
	e.printStackTrace();
	}
	//System.out.println("dateTime:"+dateTime.getTime());

	return new Long(dateTime.getTime())/1000;

    }

    @Override
    public void start() {
        //Nothing to start.
    }

    @Override
    public void stop() {
        //Nothing to stop.
    }

    @Override
    public Attribute.Type getReturnType() {
        return returnType;
    }

    @Override
    public Object[] currentState() {
        return null;    //No need to maintain a state.
    }

    @Override
    public void restoreState(Object[] state) {
        //Since there's no need to maintain a state, nothing needs to be done here.
    }
}
