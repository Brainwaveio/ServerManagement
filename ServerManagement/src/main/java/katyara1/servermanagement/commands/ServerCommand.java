package katyara1.servermanagement.commands;

import com.google.common.collect.Lists;
import katyara1.servermanagement.utils.AbstractCommand;
import katyara1.servermanagement.utils.Commands;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import java.util.List;
import java.util.Set;

public class ServerCommand extends AbstractCommand {
    private final Server _server;

    public ServerCommand() {
        super(Commands.Server.toString());
        _server = _serverManagementInstance.getServer();
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        switch (args[0]) {
            case "info":
                getInfoServer(sender);
                break;

            default:
                sender.sendMessage("Error Commands");
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            return Lists.newArrayList("info");
        }

        return Lists.newArrayList();
    }

    private void getInfoServer(CommandSender sender) {
        String ip = _server.getIp();
        int port = _server.getPort();
        int maxPlayers = _server.getMaxPlayers();
        Set<String> ipBans = _server.getIPBans();

        if (ip.isEmpty()) {
            ip = "127.0.0.1";
        }

        if (sender.isOp()) {
            sender.sendMessage("-- Server info --");

            sender.sendMessage("Server ip: " + ChatColor.GREEN + ip);
            sender.sendMessage("Server port: " + ChatColor.GREEN + port);
            sender.sendMessage("Max players: " + ChatColor.GREEN + maxPlayers);

            sender.sendMessage("---- Ip bans ----");

            if (ipBans.isEmpty()) {
                sender.sendMessage("Hasn`t bans");
            } else {
                for (String ipBan : ipBans) {
                    sender.sendMessage(ChatColor.RED + ipBan);
                }
            }

            sender.sendMessage("-----------------");
        }
    }
}
