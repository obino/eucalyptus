/*************************************************************************
 * Copyright 2009-2014 Ent. Services Development Corporation LP
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

import static com.eucalyptus.compute.policy.ComputePolicyContext.ComputePolicyContextResource;
import static com.eucalyptus.compute.policy.ComputePolicyContext.ComputePolicyContextResourceSupport;
import java.util.Date;
import javax.annotation.Nullable;
import com.eucalyptus.compute.common.internal.blockstorage.Snapshot;
import com.eucalyptus.util.TypeMapper;
import com.google.common.base.Function;

/**
 *
 */
@TypeMapper
public class SnapshotComputePolicyContextTransform implements Function<Snapshot,ComputePolicyContextResource> {

  @Override
  public ComputePolicyContextResource apply( final Snapshot snapshot ) {
    return new ComputePolicyContextResourceSupport( ) {
      @Nullable
      @Override
      public String getOwner() {
        return snapshot.getOwnerAccountNumber( );
      }

      @Nullable
      @Override
      public String getParentVolumeArn() {
        return snapshot.getParentVolume( ) == null ?
            null :
            "arn:aws:ec2::" + getOwner( ) + ":volume/" + snapshot.getParentVolume( );
      }

      @Nullable
      @Override
      public Date getSnapshotTime() {
        return snapshot.getCreationTimestamp( );
      }

      @Nullable
      @Override
      public Integer getVolumeSize() {
        return snapshot.getVolumeSize( );
      }
    };
  }
}
