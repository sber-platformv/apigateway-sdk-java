package com.cloud.apigateway.sdk.utils;

import com.cloud.sdk.http.HttpMethodName;
import java.io.InputStream;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;

public abstract class AccessService {
   protected String ak = null;
   protected String sk = null;

   public AccessService(String ak, String sk) {
      this.ak = ak;
      this.sk = sk;
   }

   public abstract HttpRequestBase access(String var1, Map var2, InputStream var3, Long var4, HttpMethodName var5) throws Exception;

   public abstract HttpRequestBase access(String var1, Map var2, String var3, HttpMethodName var4) throws Exception;

   public HttpRequestBase access(String url, Map header, HttpMethodName httpMethod) throws Exception {
      return this.access(url, header, (InputStream)null, 0L, httpMethod);
   }

   public HttpRequestBase access(String url, InputStream content, Long contentLength, HttpMethodName httpMethod) throws Exception {
      return this.access(url, (Map)null, content, contentLength, httpMethod);
   }

   public HttpRequestBase access(String url, HttpMethodName httpMethod) throws Exception {
      return this.access(url, (Map)null, (InputStream)null, 0L, httpMethod);
   }

   public String getAk() {
      return this.ak;
   }

   public void setAk(String ak) {
      this.ak = ak;
   }

   public String getSk() {
      return this.sk;
   }

   public void setSk(String sk) {
      this.sk = sk;
   }
}
