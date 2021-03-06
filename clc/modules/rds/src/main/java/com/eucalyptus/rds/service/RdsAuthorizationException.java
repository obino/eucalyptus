/*
 * Copyright 2021 AppScale Systems, Inc
 *
 * SPDX-License-Identifier: BSD-2-Clause
 */
package com.eucalyptus.rds.service;

import com.eucalyptus.ws.Role;
import com.eucalyptus.ws.protocol.QueryBindingInfo;

/**
 *
 */
@QueryBindingInfo( statusCode = 403 )
public class RdsAuthorizationException extends RdsException {
  private static final long serialVersionUID = 1L;

  public RdsAuthorizationException( final String code, final String message ) {
    super( code, Role.Sender, message );
  }
}
