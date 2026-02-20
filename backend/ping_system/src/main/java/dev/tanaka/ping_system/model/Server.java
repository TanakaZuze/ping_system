package dev.tanaka.ping_system.model;

import dev.tanaka.ping_system.enums.ServerStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "ip address cannot be empty")
    private String ipAddress;
    private String name;
    private String memory;
    private String type;
    private String imageUrl;
    private ServerStatus serverStatus;

    public Server() {
    }

    public Server(Long id, String ipAddress, String name, String memory, String type, String imageUrl, ServerStatus serverStatus) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.name = name;
        this.memory = memory;
        this.type = type;
        this.imageUrl = imageUrl;
        this.serverStatus = serverStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
