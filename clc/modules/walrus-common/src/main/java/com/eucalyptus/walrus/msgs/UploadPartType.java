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
package com.eucalyptus.walrus.msgs;

public class UploadPartType extends WalrusDataRequestType {

  private String contentLength;
  private String contentMD5;
  private String contentType;
  private String expect;
  private String uploadId;
  private String partNumber;

  public String getContentLength( ) {
    return contentLength;
  }

  public void setContentLength( String contentLength ) {
    this.contentLength = contentLength;
  }

  public String getContentMD5( ) {
    return contentMD5;
  }

  public void setContentMD5( String contentMD5 ) {
    this.contentMD5 = contentMD5;
  }

  public String getContentType( ) {
    return contentType;
  }

  public void setContentType( String contentType ) {
    this.contentType = contentType;
  }

  public String getExpect( ) {
    return expect;
  }

  public void setExpect( String expect ) {
    this.expect = expect;
  }

  public String getUploadId( ) {
    return uploadId;
  }

  public void setUploadId( String uploadId ) {
    this.uploadId = uploadId;
  }

  public String getPartNumber( ) {
    return partNumber;
  }

  public void setPartNumber( String partNumber ) {
    this.partNumber = partNumber;
  }
}
