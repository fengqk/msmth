package com.perfect.msmth.helper;

import com.perfect.msmth.helper.UtilHelper;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpVersion;
import org.apache.http.HttpResponse;
import org.apache.http.HttpEntity;
import org.apache.http.Header;
import org.apache.http.util.EntityUtils;

import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.ClientConnectionManager;

import org.apache.http.params.HttpParams;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpProtocolParams;

import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.drawable.Drawable;

public class SmthSpider {
    
    public static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.4) Gecko/20091016 Firefox/3.5.4";

    private DefaultHttpClient httpClient;
    
    private static class Holder {
        static SmthSpider instance = new SmthSpider();
    }

    public static SmthSpider getInstance() {
        return Holder.instance;
    }

    private SmthSpider() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        
        HttpParams params = new BasicHttpParams();
        ConnManagerParams.setMaxTotalConnections(params, 10);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
        
        httpClient = new DefaultHttpClient(cm, params);
        httpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, true);
    }
    
    public String getUrlContent(String url, String encoding) {
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("Accept-Encoding", "gzip, deflate");
        
        String content = null;
        try{
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            Header[] headers = httpResponse.getHeaders("Content-Encoding");
            
            boolean compressed = false;
            if(headers != null && headers.length != 0) {
                for(Header header : headers) {
                    String s = header.getValue();
                    if(s.contains("gzip")) {
                        compressed = true;
                        break;
                    }
                }
            }
            
            if(compressed) {
                InputStream is = httpEntity.getContent();
                BufferedReader br = new java.io.BufferedReader(new InputStreamReader(new GZIPInputStream(is), encoding));
                String line;
                StringBuilder sb = new StringBuilder();
                while((line = br.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }
                br.close();
                content = sb.toString();
            } else {
                content = EntityUtils.toString(httpEntity, encoding);
            } 
        } catch (Exception e) {
            content = null;
        }
        
        return UtilHelper.toDBC(content);
    }
    
    public byte[] getUrlImage(String url) {
        ByteArrayOutputStream out = null;  
        int BUFFER_SIZE = 1024*16;  
        InputStream inputStream = null;  
        try{
            inputStream = new URL(url).openStream();  
            BufferedInputStream in = new BufferedInputStream(inputStream, BUFFER_SIZE);  
            out = new ByteArrayOutputStream(BUFFER_SIZE);  
            int length = 0;
            byte[] tem = new byte[BUFFER_SIZE];  
            length = in.read(tem);  
            while(length != -1){
                out.write(tem, 0, length);  
                length = in.read(tem);  
            }
            in.close();
        }catch(Exception e){  
            e.printStackTrace();
        }finally{
            if(inputStream != null){  
                try{  
                    inputStream.close();  
                }catch(Exception e){}  
            }
        }
        
        return out != null ? out.toByteArray() : null;
    }
    
    public void Destroy() {
        httpClient.getConnectionManager().shutdown();
    }
}
