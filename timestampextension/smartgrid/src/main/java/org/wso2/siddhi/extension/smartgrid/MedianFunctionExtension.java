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
import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;

public class MedianFunctionExtension extends FunctionExecutor {
    private static final Logger log = Logger.getLogger(MedianFunctionExtension.class);
    private Attribute.Type returnType = Attribute.Type.DOUBLE;
    ArrayList<Double> pq1 = new ArrayList<Double>();

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ExecutionPlanContext executionPlanContext) {
        if (attributeExpressionExecutors.length != 1) {
            throw new ExecutionPlanValidationException("Invalid no of arguments passed to smartgrid:median() function, " +
                    "required 1, but found " + attributeExpressionExecutors.length);
        }

        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.DOUBLE) {
            throw new ExecutionPlanValidationException("Invalid parameter type found for the first argument of function, " +
                    "required " + Attribute.Type.DOUBLE + ", but found " + attributeExpressionExecutors[0].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        log.info("BBB++>>");
        return null;
    }

    @Override
    protected Object execute(Object data) {
       /* for(Object element: objects){
            pq1.add((Double)element);
        }

        return getMedian();*/

        //log.info("AAAAAAAAAAAAAAAAA++>>");

 /*       pq1.add((Double)data);

        if(pq1.size() >= 5){
            Double median = getMedian();
            pq1.clear();
            return median;
        }

        return -1.0d;
*/
            pq1.add((Double)data);

        if(pq1.size() >= 5){
            Double median = getMedian();
            pq1.remove(0);
            //pq1.clear();
            return median;
        }
            return 0.0d;
    }

    public double getMedian() {
        //log.info("+++++++++++++++++++++>>");
        
        Collections.sort(pq1);
       // log.info("pq1.size():"+pq1.size());
        int pointA = pq1.size()/2;
        //log.info("pointA:"+pointA);
        if(pq1.size()%2==0){
            int pointB = pointA-1;
          //  log.info("median:"+(pq1.get(pointA)+pq1.get(pointB))/2);
            return (pq1.get(pointA)+pq1.get(pointB))/2;
        }
        //log.info("median:"+pq1.get(pointA));
        //log.info("-------------->>");
        //lock.unlock();
        return pq1.get(pointA);
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
