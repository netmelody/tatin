package org.netmelody.tatin.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.simpleframework.http.Path;
import org.simpleframework.http.Request;
import org.simpleframework.http.Response;
import org.simpleframework.http.core.Container;

import com.google.common.base.Optional;
import com.google.common.io.ByteStreams;

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
    }

    private void handlePut(Request req, Response resp) {
        try {
            final InputStream inputStream = req.getInputStream();
            state.put(req.getPath().getPath(), ByteStreams.toByteArray(inputStream));
            inputStream.close();
        }
        catch (Exception e) {
        }
    }

    private void handleGet(Request req, Response resp) {
        try {
            final OutputStream outputStream = resp.getOutputStream();
            ByteStreams.copy(ByteStreams.newInputStreamSupplier(valueAt(req.getPath())), outputStream);
            outputStream.close();
        }
        catch (Exception e) {
        }
    }

    private byte[] valueAt(final Path path) {
        return Optional.fromNullable(state.get(path.getPath())).or(new byte[0]);
    }

}