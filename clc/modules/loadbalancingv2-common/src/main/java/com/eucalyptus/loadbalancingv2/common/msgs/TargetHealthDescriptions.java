/*
 * Copyright 2021 AppScale Systems, Inc
 *
 * SPDX-License-Identifier: BSD-2-Clause
 */
package com.eucalyptus.loadbalancingv2.common.msgs;

import java.util.ArrayList;
import com.eucalyptus.binding.HttpEmbedded;
import com.eucalyptus.binding.HttpParameterMapping;
import edu.ucsb.eucalyptus.msgs.EucalyptusData;


public class TargetHealthDescriptions extends EucalyptusData {

  @HttpEmbedded(multiple = true)
  @HttpParameterMapping(parameter = "member")
  private ArrayList<TargetHealthDescription> member = new ArrayList<>();

  public ArrayList<TargetHealthDescription> getMember() {
    return member;
  }

  public void setMember(final ArrayList<TargetHealthDescription> member) {
    this.member = member;
  }
}
