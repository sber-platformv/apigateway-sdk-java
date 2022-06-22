package com.cloud.apigateway.sdk.utils;

import com.cloud.sdk.auth.signer.Signer;
import com.cloud.sdk.http.HttpMethodName;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class AccessServiceOkhttpImpl extends AccessServiceOkhttp {
   private static final String UTF8 = "UTF-8";

   public AccessServiceOkhttpImpl(String ak, String sk) {
      super(ak, sk);
   }

   public okhttp3.Request access(String url, Map headers, String entity, HttpMethodName httpMethod) throws Exception {
      Request request = new Request();
      request.setAppKey(this.ak);
      request.setAppSecrect(this.sk);
      request.setMethod(httpMethod.name());
      request.setUrl(url);
      Iterator var6 = headers.keySet().iterator();

      while(var6.hasNext()) {
         String k = (String)var6.next();
         request.addHeader(k, (String)headers.get(k));
      }

      request.setBody(entity);
      Signer signer = new Signer();
      signer.sign(request);
      return createRequest(url, request.getHeaders(), entity, httpMethod);
   }

   public okhttp3.Request access(String url, Map headers, InputStream content, Long contentLength, HttpMethodName httpMethod) throws Exception {
      ByteArrayOutputStream result = new ByteArrayOutputStream();
      byte[] buffer = new byte[1024];

      int length;
      while((length = content.read(buffer)) != -1) {
         result.write(buffer, 0, length);
      }

      String body = result.toString("UTF-8");
      return this.access(url, headers, body, httpMethod);
   }

   private static okhttp3.Request createRequest(String url, Map headers, String body, HttpMethodName httpMethod) throws Exception {
      if (body == null) {
         body = "";
      }

      RequestBody entity = RequestBody.create(MediaType.parse(""), body.getBytes("UTF-8"));
      okhttp3.Request httpRequest;
      if (httpMethod == HttpMethodName.POST) {
         httpRequest = (new okhttp3.Request.Builder()).url(url).post(entity).build();
      } else if (httpMethod == HttpMethodName.PUT) {
         httpRequest = (new okhttp3.Request.Builder()).url(url).put(entity).build();
      } else if (httpMethod == HttpMethodName.PATCH) {
         httpRequest = (new okhttp3.Request.Builder()).url(url).patch(entity).build();
      } else if (httpMethod == HttpMethodName.DELETE) {
         httpRequest = (new okhttp3.Request.Builder()).url(url).delete(entity).build();
      } else if (httpMethod == HttpMethodName.GET) {
         httpRequest = (new okhttp3.Request.Builder()).url(url).get().build();
      } else if (httpMethod == HttpMethodName.HEAD) {
         httpRequest = (new okhttp3.Request.Builder()).url(url).head().build();
      } else {
         if (httpMethod != HttpMethodName.OPTIONS) {
            throw new RuntimeException("Unknown HTTP method name: " + httpMethod);
         }

         httpRequest = (new okhttp3.Request.Builder()).url(url).method("OPTIONS", (RequestBody)null).build();
      }

      String key;
      for(Iterator var6 = headers.keySet().iterator(); var6.hasNext(); httpRequest = httpRequest.newBuilder().addHeader(key, (String)headers.get(key)).build()) {
         key = (String)var6.next();
      }

      return httpRequest;
   }
}
