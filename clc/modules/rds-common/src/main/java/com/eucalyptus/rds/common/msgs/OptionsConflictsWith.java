/*
 * Copyright 2021 AppScale Systems, Inc
 *
 * SPDX-License-Identifier: BSD-2-Clause
 */
package com.eucalyptus.rds.common.msgs;

import java.util.ArrayList;
import com.eucalyptus.binding.HttpParameterMapping;
import edu.ucsb.eucalyptus.msgs.EucalyptusData;


public class OptionsConflictsWith extends EucalyptusData {

  @HttpParameterMapping(parameter = "OptionConflictName")
  private ArrayList<String> member = new ArrayList<>();

  public ArrayList<String> getMember() {
    return member;
  }

  public void setMember(final ArrayList<String> member) {
    this.member = member;
  }
}
