/*************************************************************************
 * Copyright 2009-2013 Ent. Services Development Corporation LP
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
package com.eucalyptus.compute.policy;

import static com.eucalyptus.auth.policy.PolicySpec.qualifiedName;
import static com.eucalyptus.compute.common.policy.ComputePolicySpec.*;
import java.util.Set;
import com.eucalyptus.auth.AuthException;
import com.eucalyptus.auth.policy.condition.ArnConditionOp;
import com.eucalyptus.auth.policy.condition.ConditionOp;
import com.eucalyptus.auth.policy.key.PolicyKey;
import com.google.common.collect.ImmutableSet;
import net.sf.json.JSONException;

/**
 *
 */
@PolicyKey( VpcKey.KEY_NAME )
public class VpcKey implements ComputeKey {
  static final String KEY_NAME = "ec2:vpc";
  private static final Set<String> actions = ImmutableSet.<String>builder()
      .add( qualifiedName( VENDOR_EC2, EC2_CREATETAGS ) )
      .add( qualifiedName( VENDOR_EC2, EC2_ATTACHCLASSICLINKVPC ) )
      .add( qualifiedName( VENDOR_EC2, EC2_AUTHORIZESECURITYGROUPEGRESS ) )
      .add( qualifiedName( VENDOR_EC2, EC2_AUTHORIZESECURITYGROUPINGRESS ) )
      .add( qualifiedName( VENDOR_EC2, EC2_DELETENETWORKACL ) )
      .add( qualifiedName( VENDOR_EC2, EC2_DELETENETWORKACLENTRY ) )
      .add( qualifiedName( VENDOR_EC2, EC2_DELETEROUTE ) )
      .add( qualifiedName( VENDOR_EC2, EC2_DELETEROUTETABLE ) )
      .add( qualifiedName( VENDOR_EC2, EC2_DELETESECURITYGROUP ) )
      .add( qualifiedName( VENDOR_EC2, EC2_REVOKESECURITYGROUPEGRESS ) )
      .add( qualifiedName( VENDOR_EC2, EC2_REVOKESECURITYGROUPINGRESS ) )
      .add( qualifiedName( VENDOR_EC2, EC2_RUNINSTANCES ) )
      .build( );

  @Override
  public String value( ) throws AuthException {
    return ComputePolicyContext.getVpcArn( );
  }

  @Override
  public void validateConditionType( final Class<? extends ConditionOp> conditionClass ) throws JSONException {
    if ( !ArnConditionOp.class.isAssignableFrom( conditionClass ) ) {
      throw new JSONException( KEY_NAME + " is not allowed in condition " + conditionClass.getName( ) + ". ARN conditions are required." );
    }
  }

  @Override
  public void validateValueType( final String value ) throws JSONException {
    Validation.assertArnValue( value );
  }

  @Override
  public boolean canApply( final String action ) {
    return actions.contains( action );
  }
}
