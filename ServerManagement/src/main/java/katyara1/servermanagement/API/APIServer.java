package katyara1.servermanagement.API;

import com.sun.net.httpserver.HttpServer;
import katyara1.servermanagement.API.controllers.InformationServerController;
import java.io.IOException;
import java.net.InetSocketAddress;

public final class APIServer {
    private static HttpServer _server;

    public static void startServer(int port) throws IOException {
        _server = HttpServer.create(new InetSocketAddress(port), 0);

        _server.createContext("/informationserver", new InformationServerController());

        _server.setExecutor(null);
        _server.start();
    }

    public static void stopServer() {
        if (_server != null) {
            _server.stop(0);
            _server = null;
        }
    }
}
