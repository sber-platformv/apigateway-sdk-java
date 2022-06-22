package com.cloud.apigateway.sdk.utils;

import com.cloud.sdk.http.HttpMethodName;
import com.cloud.sdk.util.HttpUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Request {
   private String key = null;
   private String secret = null;
   private String method = null;
   private String url = null;
   private String body = null;
   private String fragment = null;
   private Map headers = new Hashtable();
   private Map queryString = new Hashtable();

   /** @deprecated */
   @Deprecated
   public String getRegion() {
      return "";
   }

   /** @deprecated */
   @Deprecated
   public String getServiceName() {
      return "";
   }

   public String getKey() {
      return this.key;
   }

   public String getSecrect() {
      return this.secret;
   }

   public HttpMethodName getMethod() {
      return HttpMethodName.valueOf(this.method.toUpperCase());
   }

   public String getBody() {
      return this.body;
   }

   public Map getHeaders() {
      return this.headers;
   }

   /** @deprecated */
   @Deprecated
   public void setRegion(String region) {
   }

   /** @deprecated */
   @Deprecated
   public void setServiceName(String serviceName) {
   }

   public void setAppKey(String appKey) throws Exception {
      if (null != appKey && !appKey.trim().isEmpty()) {
         this.key = appKey;
      } else {
         throw new Exception("appKey can not be empty");
      }
   }

   public void setAppSecrect(String appSecret) throws Exception {
      if (null != appSecret && !appSecret.trim().isEmpty()) {
         this.secret = appSecret;
      } else {
         throw new Exception("appSecrect can not be empty");
      }
   }

   public void setKey(String appKey) throws Exception {
      if (null != appKey && !appKey.trim().isEmpty()) {
         this.key = appKey;
      } else {
         throw new Exception("appKey can not be empty");
      }
   }

   public void setSecret(String appSecret) throws Exception {
      if (null != appSecret && !appSecret.trim().isEmpty()) {
         this.secret = appSecret;
      } else {
         throw new Exception("appSecrect can not be empty");
      }
   }

   public void setMethod(String method) throws Exception {
      if (null == method) {
         throw new Exception("method can not be empty");
      } else if (!method.equalsIgnoreCase("post") && !method.equalsIgnoreCase("put") && !method.equalsIgnoreCase("patch") && !method.equalsIgnoreCase("delete") && !method.equalsIgnoreCase("get") && !method.equalsIgnoreCase("options") && !method.equalsIgnoreCase("head")) {
         throw new Exception("unsupported method");
      } else {
         this.method = method;
      }
   }

   public String getUrl() {
      String uri = this.url;
      if (this.queryString.size() > 0) {
         uri = uri + "?";
         int loop = 0;
         Iterator var3 = this.queryString.entrySet().iterator();

         while(var3.hasNext()) {
            Map.Entry entry = (Map.Entry)var3.next();

            for(Iterator var5 = ((List)entry.getValue()).iterator(); var5.hasNext(); ++loop) {
               String value = (String)var5.next();
               if (loop > 0) {
                  uri = uri + "&";
               }

               uri = uri + HttpUtils.urlEncode((String)entry.getKey(), false);
               uri = uri + "=";
               uri = uri + HttpUtils.urlEncode(value, false);
            }
         }
      }

      if (this.fragment != null) {
         uri = uri + "#";
         uri = uri + this.fragment;
      }

      return uri;
   }

   public void setUrl(String url) throws Exception {
      if (null != url && !url.trim().isEmpty()) {
         int i = url.indexOf(35);
         if (i >= 0) {
            url = url.substring(0, i);
         }

         i = url.indexOf(63);
         if (i >= 0) {
            String query = url.substring(i + 1, url.length());
            String[] var4 = query.split("&");
            int var5 = var4.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               String item = var4[var6];
               String[] spl = item.split("=", 2);
               String key = spl[0];
               String value = "";
               if (spl.length > 1) {
                  value = spl[1];
               }

               if (!key.trim().isEmpty()) {
                  key = URLDecoder.decode(key, "UTF-8");
                  value = URLDecoder.decode(value, "UTF-8");
                  this.addQueryStringParam(key, value);
               }
            }

            url = url.substring(0, i);
         }

         this.url = url;
      } else {
         throw new Exception("url can not be empty");
      }
   }

   public String getPath() {
      String url = this.url;
      int i = url.indexOf("://");
      if (i >= 0) {
         url = url.substring(i + 3);
      }

      i = url.indexOf(47);
      return i >= 0 ? url.substring(i) : "/";
   }

   public String getHost() {
      String url = this.url;
      int i = url.indexOf("://");
      if (i >= 0) {
         url = url.substring(i + 3);
      }

      i = url.indexOf(47);
      if (i >= 0) {
         url = url.substring(0, i);
      }

      return url;
   }

   public void setBody(String body) {
      this.body = body;
   }

   public void addQueryStringParam(String name, String value) throws UnsupportedEncodingException {
      List paramList = (List)this.queryString.get(name);
      if (paramList == null) {
         paramList = new ArrayList();
         this.queryString.put(name, paramList);
      }

      ((List)paramList).add(value);
   }

   public Map getQueryStringParams() {
      return this.queryString;
   }

   public String getFragment() {
      return this.fragment;
   }

   public void setFragment(String fragment) throws Exception {
      if (null != fragment && !fragment.trim().isEmpty()) {
         this.fragment = URLEncoder.encode(fragment, "UTF-8");
      } else {
         throw new Exception("fragment can not be empty");
      }
   }

   public void addHeader(String name, String value) {
      if (null != name && !name.trim().isEmpty()) {
         this.headers.put(name, value);
      }
   }
}
