package com.cloud.apigateway.sdk.utils;

import com.cloud.sdk.http.HttpMethodName;
import java.util.Map;

public abstract class AccessServiceOkhttp {
   protected String ak = null;
   protected String sk = null;

   public AccessServiceOkhttp(String ak, String sk) {
      this.ak = ak;
      this.sk = sk;
   }

   public abstract okhttp3.Request access(String var1, Map var2, String var3, HttpMethodName var4) throws Exception;

   public okhttp3.Request access(String url, Map header, HttpMethodName httpMethod) throws Exception {
      return this.access(url, header, (String)null, httpMethod);
   }

   public okhttp3.Request access(String url, String entity, HttpMethodName httpMethod) throws Exception {
      return this.access(url, (Map)null, entity, httpMethod);
   }

   public okhttp3.Request access(String url, HttpMethodName httpMethod) throws Exception {
      return this.access(url, (Map)null, (String)null, httpMethod);
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
