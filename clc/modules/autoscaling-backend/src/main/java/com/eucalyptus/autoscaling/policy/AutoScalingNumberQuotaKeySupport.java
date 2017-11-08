/*************************************************************************
 * Copyright 2009-2015 Ent. Services Development Corporation LP
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
 ************************************************************************/
package com.eucalyptus.autoscaling.policy;

import com.eucalyptus.auth.AuthException;
import com.eucalyptus.auth.policy.PolicySpec;
import com.eucalyptus.auth.policy.key.KeyUtils;
import com.eucalyptus.auth.policy.key.QuotaKey;
import com.eucalyptus.auth.principal.AccountFullName;
import com.eucalyptus.auth.principal.PolicyScope;
import com.eucalyptus.auth.principal.UserFullName;
import com.eucalyptus.autoscaling.common.AutoScalingMetadata;
import com.eucalyptus.autoscaling.common.policy.AutoScalingPolicySpec;
import com.eucalyptus.util.RestrictedTypes;
import net.sf.json.JSONException;

/**
 *
 */
abstract class AutoScalingNumberQuotaKeySupport<T extends AutoScalingMetadata> extends QuotaKey {

  private final String key;
  private final String action;
  private final Class<T> metadataClass;

  AutoScalingNumberQuotaKeySupport( final String key, 
                                    final String action, 
                                    final Class<T> metadataClass ) {
    this.key = key;
    this.action = action;
    this.metadataClass = metadataClass;
  }

  @Override
  public final void validateValueType( String value ) throws JSONException {
    KeyUtils.validateIntegerValue( value, key );
  }

  @Override
  public final boolean canApply( String action ) {
    return PolicySpec.qualifiedName(
        AutoScalingPolicySpec.VENDOR_AUTOSCALING,
        this.action ).equals( action );
  }

  @Override
  public final String value( final PolicyScope scope,
                             final String id, 
                             final String resource, 
                             final Long quantity ) throws AuthException {
    switch ( scope ) {
      case Account:
        return Long.toString( RestrictedTypes.quantityMetricFunction( metadataClass )
            .apply( AccountFullName.getInstance( id ) ) + 1 );
      case Group:
        return NOT_SUPPORTED;
      case User:
        return Long.toString( RestrictedTypes.quantityMetricFunction( metadataClass )
            .apply( UserFullName.getInstance( id ) ) + 1 );
    }
    throw new AuthException( "Invalid scope" );
  }
}
