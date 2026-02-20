package dev.tanaka.ping_system.controller;

import dev.tanaka.ping_system.model.Server;
import dev.tanaka.ping_system.service.implementation.ServerServiceI;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController

public class ServerController {
    //    Ioc
    private final ServerServiceI serverServiceI;

    public ServerController(ServerServiceI serverServiceI) {
        this.serverServiceI = serverServiceI;
    }

    //create server
    @PostMapping("/create-server")
    @ResponseStatus(HttpStatus.OK)
    public Server createServer(@RequestBody Server server) {
        return serverServiceI.createServer(server);
    }

    //read all servers
    @GetMapping("/read-all-servers")
    @ResponseStatus(HttpStatus.OK)
    public List<Server> readAllServers() {
        return serverServiceI.getAllServers();
    }

    //get server by id
    @GetMapping("/get-server-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Server getServerById(@PathVariable Long id) {
        return serverServiceI.getByServerById(id);
    }

    //    update serve
    @PutMapping("/update-server/{id}")
    public Server updateServer(@PathVariable Long id, @RequestBody Server server) {
        return serverServiceI.updateServer(id, server);
    }

    //    @Delete server
    @DeleteMapping("/delete/{id}")
    public void deleteServer(@PathVariable Long id) {
        serverServiceI.deleteServer(id);
    }

    //    ping server
    @PostMapping("/ping-server")
    public void pingServerController(Server server) throws IOException {
        serverServiceI.pingServer(server.getIpAddress());
    }
}
