/*************************************************************************
 * Copyright 2008 Regents of the University of California
 * Copyright 2009-2012 Ent. Services Development Corporation LP
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
 * POSSIBILITY OF SUCH DAMAGE. USERS OF THIS SOFTWARE ACKNOWLEDGE
 * THE POSSIBLE PRESENCE OF OTHER OPEN SOURCE LICENSED MATERIAL,
 * COPYRIGHTED MATERIAL OR PATENTED MATERIAL IN THIS SOFTWARE,
 * AND IF ANY SUCH MATERIAL IS DISCOVERED THE PARTY DISCOVERING
 * IT MAY INFORM DR. RICH WOLSKI AT THE UNIVERSITY OF CALIFORNIA,
 * SANTA BARBARA WHO WILL THEN ASCERTAIN THE MOST APPROPRIATE REMEDY,
 * WHICH IN THE REGENTS' DISCRETION MAY INCLUDE, WITHOUT LIMITATION,
 * REPLACEMENT OF THE CODE SO IDENTIFIED, LICENSING OF THE CODE SO
 * IDENTIFIED, OR WITHDRAWAL OF THE CODE CAPABILITY TO THE EXTENT
 * NEEDED TO COMPLY WITH ANY SUCH LICENSES OR RIGHTS.
 ************************************************************************/

package com.eucalyptus.crypto;

import java.security.Key;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

public interface CertificateProvider extends BaseSecurityProvider {
  /**
   * TODO: DOCUMENT CryptoProvider.java
   * @return
   */
  public abstract KeyPair generateKeyPair( );
  public abstract String getFingerPrint( Key privKey );
  public abstract byte[] getEncoded( PrivateKey privateKey );
  
  /**
   * Following operations produce a signed certificate. If the <tt>signer</tt> certificate is null, then the certificate generated is self signed.
   * 
   * @param keys
   * @param subjectDn
   * @param signer
   * @return
   */
  public abstract X509Certificate generateCertificate( KeyPair keys, X500Principal subjectDn, X500Principal signer, PrivateKey signingKey );
  public abstract X509Certificate generateCertificate( KeyPair keys, X500Principal subjectDn, X500Principal signer, PrivateKey signingKey, Date notAfter );
  public abstract X509Certificate generateCertificate( PublicKey key, X500Principal subjectDn, X500Principal signer, PrivateKey signingKey, Date notAfter );
  public abstract X509Certificate generateCertificate( KeyPair keys, String userName );
  public abstract X509Certificate generateCertificate( KeyPair keys, X500Principal subjectDn );

  /**
   * Mechanically identical to the above, but signed by the root cert.
   */
  public abstract X509Certificate generateServiceCertificate( KeyPair keys, String userName );

}
