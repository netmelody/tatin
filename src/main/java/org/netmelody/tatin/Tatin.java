package org.netmelody.tatin;

import java.io.IOException;

import org.netmelody.tatin.server.TatinServer;

import static java.lang.Integer.parseInt;

public final class Tatin {
    public static void main(String[] args) throws IOException {
        if (args.length != 1 || !isValidPort(args[0])) {
            System.out.println("Usage: tatin port");
            System.exit(1);
        }
        
        final TatinServer tatinServer = new TatinServer(parseInt(args[0]));
        System.out.printf("tatin started on port %s", tatinServer.port());
    }

    private static boolean isValidPort(String portString) {
        try {
            final int port = parseInt(portString);
            return port >= 0 && port <= 65535;
        }
        catch (Exception e) {
            return false;
        }
    }
}