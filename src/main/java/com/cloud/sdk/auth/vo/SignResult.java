package com.cloud.sdk.auth.vo;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SignResult {
   private Map headers = new HashMap();
   private URL url;
   private Map parameters = new HashMap();
   private InputStream inputStream;

   public Map getHeaders() {
      return this.headers;
   }

   public void setHeaders(Map headers) {
      this.headers = headers;
   }

   public URL getUrl() {
      return this.url;
   }

   public void setUrl(URL url) {
      this.url = url;
   }

   public Map getParameters() {
      return this.parameters;
   }

   public void setParameters(Map parameters) {
      this.parameters = parameters;
   }

   public InputStream getInputStream() {
      return this.inputStream;
   }

   public void setInputStream(InputStream inputStream) {
      this.inputStream = inputStream;
   }
}
