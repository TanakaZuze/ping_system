package dev.tanaka.ping_system.service;

import dev.tanaka.ping_system.model.Server;

import java.io.IOException;
import java.util.List;


public interface ServerService {
    //    crud
    Server createServer(Server server);

    List<Server> getAllServers();

    Server updateServer(Long id, Server server);

    void deleteServer(Long id);

    Server getByServerById(Long id);

    void pingServer(String ipAddress) throws IOException;


}
