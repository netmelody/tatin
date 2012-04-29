package org.netmelody.tatin;

public final class Tatin {
    public static void main(String[] args) {
        if (args.length != 1 || !isValidPort(args[0])) {
            System.out.println("Usage: tatin port");
            System.exit(1);
        }
        
        final int port = Integer.parseInt(args[0]);
        System.out.printf("tatin started on port %s", port);
    }

    private static boolean isValidPort(String portString) {
        try {
            final int port = Integer.parseInt(portString);
            return port >= 0 && port <= 65535;
        }
        catch (Exception e) {
            return false;
        }
    }
}