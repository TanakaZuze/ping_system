package dev.tanaka.ping_system.service.implementation;

import dev.tanaka.ping_system.enums.ServerStatus;
import dev.tanaka.ping_system.exception.ServerNotFoundException;
import dev.tanaka.ping_system.model.Server;
import dev.tanaka.ping_system.repository.ServerRepository;
import dev.tanaka.ping_system.service.ServerService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ServerServiceI implements ServerService {
    //    constructor Ioc
    private final ServerRepository serverRepository;

    public ServerServiceI(ServerRepository serverRepository) {
        this.serverRepository = serverRepository;
    }


    //    crud server methods
    @Override
    public Server createServer(Server server) {
        server.setImageUrl(setImageUrlMethod());
        return serverRepository.save(server);
    }

    @Override
    public List<Server> getAllServers() {
        return serverRepository.findAll();
    }

    @Override
    public Server updateServer(Long id, Server server) {
        Server updatedServer = serverRepository.findById(id)
                .orElseThrow(() -> new ServerNotFoundException("Server not found"));

        updatedServer.setName(server.getName());
        updatedServer.setIpAddress(server.getIpAddress());
        updatedServer.setName(server.getName());
        updatedServer.setMemory(server.getMemory());
        updatedServer.setType(server.getType());
        updatedServer.setImageUrl(server.getImageUrl());
        updatedServer.setServerStatus(server.getServerStatus());

        return serverRepository.save(updatedServer);
    }

    @Override
    public void deleteServer(Long id) {
        if (serverRepository.existsById(id)) {
            serverRepository.deleteById(id);
        } else
            throw new ServerNotFoundException("Server not found");
    }

    @Override
    public Server getByServerById(Long id) {
        return null;
    }

    @Override
    public void pingServer(String ipAddress) throws IOException {
// check if server exists
        Server server = serverRepository.findByIpAddress(ipAddress)
                .orElseThrow(() -> new ServerNotFoundException("Server not found"));

//        pinging ip address
        InetAddress address = InetAddress.getByName(ipAddress);
        server.setServerStatus(address.isReachable(10_00) ? ServerStatus.SERVER_UP : ServerStatus.SERVER_DOWN);
        serverRepository.save(server);

    }

    //    set image url
    public String setImageUrlMethod() {
        String[] imageNames = {"server1.png", "server2.png", "server3.png", "server4.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/server/image/" + imageNames[new Random().nextInt(4)]).toUriString();
    }


}
