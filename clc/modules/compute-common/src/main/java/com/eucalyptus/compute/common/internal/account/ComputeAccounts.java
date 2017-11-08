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
package com.eucalyptus.compute.common.internal.account;

import java.util.NoSuchElementException;
import java.util.ServiceLoader;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import com.eucalyptus.entities.Entities;
import com.eucalyptus.entities.TransactionException;
import com.eucalyptus.entities.TransactionResource;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

/**
 *
 */
public class ComputeAccounts {

  private static final Logger logger = Logger.getLogger( ComputeAccounts.class );

  private static final Cache<String,Object> accountCache = CacheBuilder
      .<String,Object>newBuilder( )
      .maximumSize( 1_000 )
      .expireAfterWrite( 1, TimeUnit.HOURS )
      .build( );

  private static final Iterable<ComputeAccountInitializer> initializers =
      ServiceLoader.load( ComputeAccountInitializer.class );

  public static void ensureInitialized( final String accountNumber ) {
    if ( accountCache.getIfPresent( accountNumber ) == null ) {
      boolean initialized = false;
      synchronized ( sync( accountNumber ) ) {
        if ( accountCache.getIfPresent( accountNumber ) == null ) {
          try ( final TransactionResource tx = Entities.transactionFor( ComputeAccount.class ) ) {
            try {
              Entities.uniqueResult( ComputeAccount.exampleWithAccountNumber( accountNumber ) );
            } catch ( NoSuchElementException e ) {
              Entities.persist( ComputeAccount.create( accountNumber ) );
              tx.commit( );
              initialized = true;
            }
            accountCache.put( accountNumber, accountNumber );
          } catch ( TransactionException e ) {
            logger.error( "Error checking for account initialization " + accountNumber, e );
          }
        }
      }
      if ( initialized ) {
        initialize( accountNumber );
      }
    }
  }

  private static Object sync( final String accountNumber ) {
    return ( "account-create-sync-" + accountNumber ).intern( );
  }

  private static void initialize( final String accountNumber ) {
    for ( final ComputeAccountInitializer initializer : initializers ) {
      try {
        initializer.initialize( accountNumber );
      } catch ( final Exception e ) {
        logger.error( "Error in account initializer", e );
      }
    }
  }

  public interface ComputeAccountInitializer {
    void initialize( String accountNumber );
  }
}
