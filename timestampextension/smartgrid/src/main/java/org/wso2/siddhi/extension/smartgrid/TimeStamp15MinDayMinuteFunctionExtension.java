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

public class TimeStamp15MinDayMinuteFunctionExtension extends FunctionExecutor {
    private Attribute.Type returnType = Attribute.Type.LONG;

    @Override
    protected void init(ExpressionExecutor[] attributeExpressionExecutors, ExecutionPlanContext executionPlanContext) {
        if (attributeExpressionExecutors.length != 2) {
            throw new ExecutionPlanValidationException("Invalid no of arguments passed to smartgrid:timeStamp15minDayMin() function, " +
                    "required 2, but found " + attributeExpressionExecutors.length);
        }

        if (attributeExpressionExecutors[0].getReturnType() != Attribute.Type.INT) {
            throw new ExecutionPlanValidationException("Invalid parameter type found for the first argument of smartgrid:timeStamp15minDayMin() function, " +
                    "required " + Attribute.Type.INT + ", but found " + attributeExpressionExecutors[0].getReturnType().toString());
        }

        if (attributeExpressionExecutors[1].getReturnType() != Attribute.Type.INT) {
            throw new ExecutionPlanValidationException("Invalid parameter type found for the second argument of smartgrid:timeStamp15minDayMin() function, " +
                    "required " + Attribute.Type.INT + ", but found " + attributeExpressionExecutors[1].getReturnType().toString());
        }
    }

    @Override
    protected Object execute(Object[] data) {
        int day = (Integer)data[0];
        int internationalDateTime = (Integer)data[1];
        int hours = (internationalDateTime / 100) * 4;
        int minutesCount = (internationalDateTime % 100) / 15; // Because minutes are in 15minutes intervals

        return new Long(day * 96 + hours + minutesCount);
    }

    @Override
    protected Object execute(Object data) {
        return null;
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
