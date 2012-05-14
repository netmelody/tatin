package org.netmelody.tatin.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.simpleframework.http.Path;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

public final class TatinContainer implements Container {

    private final Map<String, byte[]> state = new HashMap<String, byte[]>();

    @Override
    public void handle(Request req, Response resp) {
        if ("GET".equals(req.getMethod())) {
            handleGet(req, resp);
        }
        if ("PUT".equals(req.getMethod())) {
            handlePut(req, resp);
        }
        
        try {
            resp.close();
        }
        catch (IOException e) { }
    }

    private void handlePut(Request req, Response resp) {
        try {
            final InputStream inputStream = req.getInputStream();
            state.put(req.getPath().getPath(), bytesFrom(inputStream));
            inputStream.close();
        }
        catch (Exception e) { }
    }

    private byte[] bytesFrom(InputStream inputStream) throws IOException {
        final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        final byte[] data = new byte[1024];
        int nRead;
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    private void handleGet(Request req, Response resp) {
        try {
            final OutputStream outputStream = resp.getOutputStream();
            outputStream.write(valueAt(req.getPath()));
            outputStream.close();
        }
        catch (Exception e) { }
    }

    private byte[] valueAt(final Path path) {
        final byte[] bs = state.get(path.getPath());
        return (null == bs) ? new byte[0] : bs;
    }

}