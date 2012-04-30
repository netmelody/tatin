package org.netmelody.tatin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.nio.charset.Charset;

public final class TrivialHttpClient {
    
    public static final class TrivialResponse {
        public final int code;
        public final String content;
        public TrivialResponse(int code, String content) {
            this.code = code;
            this.content = content;
        }
    }
    
    public static TrivialResponse getFrom(final String urlString) throws IOException {
        final URL url = new URL(urlString);
        final HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setInstanceFollowRedirects(false);
        waitForSocketOpen(url);
        final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream()));
        final int responseCode = conn.getResponseCode();
        
        final StringBuilder responseText = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseText.append(inputLine);
        }
        in.close();
        return new TrivialResponse(responseCode, responseText.toString());
    }

    public static TrivialResponse putTo(String urlString, String content) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setInstanceFollowRedirects(false);
        waitForSocketOpen(url);
        
        conn.setDoOutput(true);
        conn.setRequestMethod("PUT");
        conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
        final byte[] contentBody = content.getBytes(Charset.forName("UTF-8"));
        conn.setRequestProperty("Content-Length", "" + contentBody);
        conn.setUseCaches(false);
        final OutputStream out = conn.getOutputStream();
        out.write(contentBody);
        out.close();
        
        final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getResponseCode() >= 400 ? conn.getErrorStream() : conn.getInputStream()));
        final int responseCode = conn.getResponseCode();
        
        final StringBuilder responseText = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            responseText.append(inputLine);
        }
        in.close();
        return new TrivialResponse(responseCode, responseText.toString());
    }

    private static void waitForSocketOpen(final URL url) {
        long startTime = System.currentTimeMillis();
        boolean currentState = false;
        while(!currentState) {
            try {
                Socket socket = new Socket(url.getHost(), url.getPort());
                currentState = true;
                socket.close();
            } catch (IOException e) {
                currentState = false;
            }
            if (System.currentTimeMillis() - startTime > 10000L) {
                throw new IllegalStateException("socket did not open");
            }
        }
    }
}