/*
 * Copyright 2020 AppScale Systems, Inc
 *
 * Use of this source code is governed by a BSD-2-Clause
 * license that can be found in the LICENSE file or at
 * https://opensource.org/licenses/BSD-2-Clause
 */
package com.eucalyptus.cloudformation.common.msgs;

import com.eucalyptus.cloudformation.common.CloudFormationMessageValidation.FieldRange;
import edu.ucsb.eucalyptus.msgs.EucalyptusData;


public class ListTypesResult extends EucalyptusData {

  @FieldRange(min = 1, max = 1024)
  private String nextToken;

  private TypeSummaries typeSummaries;

  public String getNextToken() {
    return nextToken;
  }

  public void setNextToken(final String nextToken) {
    this.nextToken = nextToken;
  }

  public TypeSummaries getTypeSummaries() {
    return typeSummaries;
  }

  public void setTypeSummaries(final TypeSummaries typeSummaries) {
    this.typeSummaries = typeSummaries;
  }

}
