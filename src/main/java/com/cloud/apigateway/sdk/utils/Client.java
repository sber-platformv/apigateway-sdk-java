package com.cloud.apigateway.sdk.utils;

import com.cloud.sdk.http.HttpMethodName;
import java.util.Map;
import org.apache.http.client.methods.HttpRequestBase;

public class Client {
   public static HttpRequestBase sign(Request request) throws Exception {
      String appKey = request.getKey();
      String appSecrect = request.getSecrect();
      String url = request.getUrl();
      String body = request.getBody();
      Map headers = request.getHeaders();
      switch (request.getMethod()) {
         case GET:
            return get(appKey, appSecrect, url, headers);
         case POST:
            return post(appKey, appSecrect, url, headers, body);
         case PUT:
            return put(appKey, appSecrect, url, headers, body);
         case PATCH:
            return patch(appKey, appSecrect, url, headers, body);
         case DELETE:
            return delete(appKey, appSecrect, url, headers);
         case HEAD:
            return head(appKey, appSecrect, url, headers);
         case OPTIONS:
            return options(appKey, appSecrect, url, headers);
         default:
            throw new IllegalArgumentException(String.format("unsupported method:%s", request.getMethod().name()));
      }
   }

   public static HttpRequestBase put(String ak, String sk, String requestUrl, Map headers, String putBody) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      HttpMethodName httpMethod = HttpMethodName.PUT;
      if (putBody == null) {
         putBody = "";
      }

      HttpRequestBase request = accessService.access(requestUrl, headers, putBody, httpMethod);
      return request;
   }

   public static HttpRequestBase patch(String ak, String sk, String requestUrl, Map headers, String body) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      HttpMethodName httpMethod = HttpMethodName.PATCH;
      if (body == null) {
         body = "";
      }

      HttpRequestBase request = accessService.access(requestUrl, headers, body, httpMethod);
      return request;
   }

   public static HttpRequestBase delete(String ak, String sk, String requestUrl, Map headers) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      HttpMethodName httpMethod = HttpMethodName.DELETE;
      HttpRequestBase request = accessService.access(requestUrl, headers, httpMethod);
      return request;
   }

   public static HttpRequestBase get(String ak, String sk, String requestUrl, Map headers) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      HttpMethodName httpMethod = HttpMethodName.GET;
      HttpRequestBase request = accessService.access(requestUrl, headers, httpMethod);
      return request;
   }

   public static HttpRequestBase post(String ak, String sk, String requestUrl, Map headers, String postbody) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      if (postbody == null) {
         postbody = "";
      }

      HttpMethodName httpMethod = HttpMethodName.POST;
      HttpRequestBase request = accessService.access(requestUrl, headers, postbody, httpMethod);
      return request;
   }

   public static HttpRequestBase head(String ak, String sk, String requestUrl, Map headers) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      HttpMethodName httpMethod = HttpMethodName.HEAD;
      HttpRequestBase request = accessService.access(requestUrl, headers, httpMethod);
      return request;
   }

   public static HttpRequestBase options(String ak, String sk, String requestUrl, Map headers) throws Exception {
      AccessService accessService = new AccessServiceImpl(ak, sk);
      HttpMethodName httpMethod = HttpMethodName.OPTIONS;
      HttpRequestBase request = accessService.access(requestUrl, headers, httpMethod);
      return request;
   }

   public static okhttp3.Request okhttpRequest(HttpMethodName httpMethod, String ak, String sk, String requestUrl, Map headers, String body) throws Exception {
      switch (httpMethod) {
         case GET:
         case HEAD:
         case OPTIONS:
            body = "";
         case POST:
         case PUT:
         case PATCH:
         case DELETE:
            if (body == null) {
               body = "";
            }

            AccessServiceOkhttp accessService = new AccessServiceOkhttpImpl(ak, sk);
            okhttp3.Request request = accessService.access(requestUrl, headers, body, httpMethod);
            return request;
         default:
            throw new RuntimeException("Unknown HTTP method name: " + httpMethod);
      }
   }

   public static okhttp3.Request signOkhttp(Request request) throws Exception {
      return okhttpRequest(request.getMethod(), request.getKey(), request.getSecrect(), request.getUrl(), request.getHeaders(), request.getBody());
   }
}
