package org.netmelody.tatin.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.simpleframework.http.core.Container;
import org.simpleframework.transport.connect.SocketConnection;


public final class TatinServer {

    private final InetSocketAddress socketAddress;

    public TatinServer(int port) throws IOException {
        final Container container = new TatinContainer();
        final SocketConnection connection = new SocketConnection(container);
        socketAddress = (InetSocketAddress)connection.connect(new InetSocketAddress(port));
    }
    
    public final int port() {
        return socketAddress.getPort();
    }
}
