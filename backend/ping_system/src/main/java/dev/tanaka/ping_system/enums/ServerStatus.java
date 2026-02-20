package dev.tanaka.ping_system.enums;

public enum ServerStatus {
    SERVER_DOWN("Server Down"),
    SERVER_UP("Server up");

    private String serverStatus;

    ServerStatus(String serverStatus) {
        this.serverStatus = serverStatus;
    }
}
