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
package com.eucalyptus.simpleworkflow.common.client;

import java.lang.reflect.Modifier;
import org.apache.log4j.Logger;
import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.eucalyptus.bootstrap.ServiceJarDiscovery;
import com.eucalyptus.component.ComponentId;
import com.eucalyptus.component.annotation.ComponentPart;
import com.eucalyptus.system.Ats;

/**
 *
 */
public class WorkflowDiscovery extends ServiceJarDiscovery {

  private static final Logger logger = Logger.getLogger( WorkflowDiscovery.class );

  @Override
  public boolean processClass( final Class candidate ) throws Exception {
    final Ats ats = Ats.inClassHierarchy( candidate );

    if ( ( ats.has( Workflow.class ) || ats.has( Activities.class ) ) &&
        ats.has( ComponentPart.class ) &&
        !Modifier.isAbstract( candidate.getModifiers() ) &&
        !Modifier.isInterface( candidate.getModifiers( ) ) &&
        !candidate.isLocalClass( ) &&
        !candidate.isAnonymousClass( ) ) {
      final Class<? extends ComponentId> componentIdClass = ats.get( ComponentPart.class ).value( );
      if ( ats.has( Workflow.class ) ) {
        WorkflowRegistry.registerWorkflow( componentIdClass, candidate );
        if ( ats.has ( Hourly.class ) ) {
          WorkflowTimer.addHourlyWorkflow( candidate );
        } else if ( ats.has( Daily.class) ) {
          WorkflowTimer.addDailyWorkflow( candidate );
        } else if ( ats.has( Repeating.class) ) {
          WorkflowTimer.addRepeatingWorkflow( candidate );
        } else if ( ats.has (Once.class) ) {
          WorkflowTimer.addOnceWorkflow( candidate );
        }
      } else {
        WorkflowRegistry.registerActivities( componentIdClass, candidate );
      }
      logger.debug( "Discovered workflow implementation class: " + candidate.getName( ) );
      return true;
    } else {
      return false;
    }
  }

  @Override
  public Double getPriority( ) {
    return 0.3d;
  }
}
