/*************************************************************************
 * Copyright 2014 Ent. Services Development Corporation LP
 *
 * Redistribution and use of this software in source and binary forms,
 * with or without modification, are permitted provided that the
 * following conditions are met:
 *
 *   Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 *   Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer
 *   in the documentation and/or other materials provided with the
 *   distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 * COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 * BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 * ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * This file may incorporate work covered under the following copyright
 * and permission notice:
 *
 *   Copyright 2010-2015 Amazon.com, Inc. or its affiliates.
 *   All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License").
 *   You may not use this file except in compliance with the License.
 *   A copy of the License is located at
 *
 *     http://aws.amazon.com/apache2.0
 *
 *   or in the "license" file accompanying this file. This file is
 *   distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF
 *   ANY KIND, either express or implied. See the License for the specific
 *   language governing permissions and limitations under the License.
 ************************************************************************/
package com.eucalyptus.simpleworkflow.common.model;

/**
 * Schedule Activity Task Failed Cause
 */
public enum ScheduleActivityTaskFailedCause {
    
    ACTIVITY_TYPE_DEPRECATED("ACTIVITY_TYPE_DEPRECATED"),
    ACTIVITY_TYPE_DOES_NOT_EXIST("ACTIVITY_TYPE_DOES_NOT_EXIST"),
    ACTIVITY_ID_ALREADY_IN_USE("ACTIVITY_ID_ALREADY_IN_USE"),
    OPEN_ACTIVITIES_LIMIT_EXCEEDED("OPEN_ACTIVITIES_LIMIT_EXCEEDED"),
    ACTIVITY_CREATION_RATE_EXCEEDED("ACTIVITY_CREATION_RATE_EXCEEDED"),
    DEFAULT_SCHEDULE_TO_CLOSE_TIMEOUT_UNDEFINED("DEFAULT_SCHEDULE_TO_CLOSE_TIMEOUT_UNDEFINED"),
    DEFAULT_TASK_LIST_UNDEFINED("DEFAULT_TASK_LIST_UNDEFINED"),
    DEFAULT_SCHEDULE_TO_START_TIMEOUT_UNDEFINED("DEFAULT_SCHEDULE_TO_START_TIMEOUT_UNDEFINED"),
    DEFAULT_START_TO_CLOSE_TIMEOUT_UNDEFINED("DEFAULT_START_TO_CLOSE_TIMEOUT_UNDEFINED"),
    DEFAULT_HEARTBEAT_TIMEOUT_UNDEFINED("DEFAULT_HEARTBEAT_TIMEOUT_UNDEFINED"),
    OPERATION_NOT_PERMITTED("OPERATION_NOT_PERMITTED");

    private String value;

    private ScheduleActivityTaskFailedCause(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    /**
     * Use this in place of valueOf.
     *
     * @param value
     *            real value
     * @return ScheduleActivityTaskFailedCause corresponding to the value
     */
    public static ScheduleActivityTaskFailedCause fromValue(String value) {
        if (value == null || "".equals(value)) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        
        } else if ("ACTIVITY_TYPE_DEPRECATED".equals(value)) {
            return ScheduleActivityTaskFailedCause.ACTIVITY_TYPE_DEPRECATED;
        } else if ("ACTIVITY_TYPE_DOES_NOT_EXIST".equals(value)) {
            return ScheduleActivityTaskFailedCause.ACTIVITY_TYPE_DOES_NOT_EXIST;
        } else if ("ACTIVITY_ID_ALREADY_IN_USE".equals(value)) {
            return ScheduleActivityTaskFailedCause.ACTIVITY_ID_ALREADY_IN_USE;
        } else if ("OPEN_ACTIVITIES_LIMIT_EXCEEDED".equals(value)) {
            return ScheduleActivityTaskFailedCause.OPEN_ACTIVITIES_LIMIT_EXCEEDED;
        } else if ("ACTIVITY_CREATION_RATE_EXCEEDED".equals(value)) {
            return ScheduleActivityTaskFailedCause.ACTIVITY_CREATION_RATE_EXCEEDED;
        } else if ("DEFAULT_SCHEDULE_TO_CLOSE_TIMEOUT_UNDEFINED".equals(value)) {
            return ScheduleActivityTaskFailedCause.DEFAULT_SCHEDULE_TO_CLOSE_TIMEOUT_UNDEFINED;
        } else if ("DEFAULT_TASK_LIST_UNDEFINED".equals(value)) {
            return ScheduleActivityTaskFailedCause.DEFAULT_TASK_LIST_UNDEFINED;
        } else if ("DEFAULT_SCHEDULE_TO_START_TIMEOUT_UNDEFINED".equals(value)) {
            return ScheduleActivityTaskFailedCause.DEFAULT_SCHEDULE_TO_START_TIMEOUT_UNDEFINED;
        } else if ("DEFAULT_START_TO_CLOSE_TIMEOUT_UNDEFINED".equals(value)) {
            return ScheduleActivityTaskFailedCause.DEFAULT_START_TO_CLOSE_TIMEOUT_UNDEFINED;
        } else if ("DEFAULT_HEARTBEAT_TIMEOUT_UNDEFINED".equals(value)) {
            return ScheduleActivityTaskFailedCause.DEFAULT_HEARTBEAT_TIMEOUT_UNDEFINED;
        } else if ("OPERATION_NOT_PERMITTED".equals(value)) {
            return ScheduleActivityTaskFailedCause.OPERATION_NOT_PERMITTED;
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}
    