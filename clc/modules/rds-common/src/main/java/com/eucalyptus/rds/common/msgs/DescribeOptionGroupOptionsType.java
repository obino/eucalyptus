/*
 * Copyright 2021 AppScale Systems, Inc
 *
 * SPDX-License-Identifier: BSD-2-Clause
 */
package com.eucalyptus.rds.common.msgs;

import javax.annotation.Nonnull;


public class DescribeOptionGroupOptionsType extends RdsMessage {

  @Nonnull
  private String engineName;

  private FilterList filters;

  private String majorEngineVersion;

  private String marker;

  private Integer maxRecords;

  public String getEngineName() {
    return engineName;
  }

  public void setEngineName(final String engineName) {
    this.engineName = engineName;
  }

  public FilterList getFilters() {
    return filters;
  }

  public void setFilters(final FilterList filters) {
    this.filters = filters;
  }

  public String getMajorEngineVersion() {
    return majorEngineVersion;
  }

  public void setMajorEngineVersion(final String majorEngineVersion) {
    this.majorEngineVersion = majorEngineVersion;
  }

  public String getMarker() {
    return marker;
  }

  public void setMarker(final String marker) {
    this.marker = marker;
  }

  public Integer getMaxRecords() {
    return maxRecords;
  }

  public void setMaxRecords(final Integer maxRecords) {
    this.maxRecords = maxRecords;
  }

}
