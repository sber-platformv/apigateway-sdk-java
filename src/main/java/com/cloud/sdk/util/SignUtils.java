package com.cloud.sdk.util;

import com.cloud.apigateway.sdk.utils.Client;
import com.cloud.apigateway.sdk.utils.Request;
import com.cloud.sdk.auth.vo.SignResult;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.client.methods.HttpRequestBase;

public class SignUtils {
   public static SignResult sign(Request request) throws Exception {
      SignResult result = new SignResult();
      HttpRequestBase signedRequest = Client.sign(request);
      Header[] headers = signedRequest.getAllHeaders();
      Map headerMap = new HashMap();
      Header[] var5 = headers;
      int var6 = headers.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Header header = var5[var7];
         headerMap.put(header.getName(), header.getValue());
      }

      result.setUrl(signedRequest.getURI().toURL());
      result.setHeaders(headerMap);
      return result;
   }
}
