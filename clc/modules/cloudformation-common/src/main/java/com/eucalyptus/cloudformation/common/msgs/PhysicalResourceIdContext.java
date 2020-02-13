/*
 * Copyright 2020 AppScale Systems, Inc
 *
 * Use of this source code is governed by a BSD-2-Clause
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/BSD-2-Clause
 */
package com.eucalyptus.cloudformation.common.msgs;

import java.util.ArrayList;
import com.eucalyptus.binding.HttpEmbedded;
import com.eucalyptus.binding.HttpParameterMapping;
import edu.ucsb.eucalyptus.msgs.EucalyptusData;


public class PhysicalResourceIdContext extends EucalyptusData {

  @HttpEmbedded(multiple = true)
  @HttpParameterMapping(parameter = "member")
  private ArrayList<PhysicalResourceIdContextKeyValuePair> member = new ArrayList<>();

  public ArrayList<PhysicalResourceIdContextKeyValuePair> getMember() {
    return member;
  }

  public void setMember(final ArrayList<PhysicalResourceIdContextKeyValuePair> member) {
    this.member = member;
  }
}
