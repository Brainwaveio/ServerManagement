package katyara1.servermanagement.commands;

import com.google.common.collect.Lists;
import katyara1.servermanagement.API.DTOs.ServerInfoDto;
import katyara1.servermanagement.services.ServerService;
import katyara1.servermanagement.utils.AbstractCommand;
import katyara1.servermanagement.utils.Commands;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.List;

public class ServerCommand extends AbstractCommand {
    private final ServerService _serverService;

    public ServerCommand() {
        super(Commands.Server.toString());
        _serverService = new ServerService();
    }

    @Override
    public void execute(CommandSender sender, String label, String[] args) {
        switch (args[0].toLowerCase()) {
            case "info":
                printInfoServer(sender);
                break;
            case "onlineplayer":
                printOnlinePlayers(sender);
                break;

            default:
                sender.sendMessage("Error Commands");
        }
    }

    @Override
    public List<String> complete(CommandSender sender, String[] args) {
        if (args.length == 1 && sender.isOp()) {
            return Lists.newArrayList("info", "playeronline");
        }

        return Lists.newArrayList();
    }

    private void printInfoServer(CommandSender sender) {
        if (sender.isOp()) {
            ServerInfoDto result = _serverService.getInfoServer();
            sender.sendMessage(ChatColor.BLUE + "-- Server info --");

            sender.sendMessage("[" + ChatColor.GOLD + "Server ip" + ChatColor.WHITE + "]: " + ChatColor.GREEN + result.ip);
            sender.sendMessage("[" + ChatColor.GOLD + "Server port" + ChatColor.WHITE + "]: " + ChatColor.GREEN + result.port);
            sender.sendMessage("[" + ChatColor.GOLD + "Max players" + ChatColor.WHITE + "]: " + ChatColor.GREEN + result.maxPlayers);

            sender.sendMessage(ChatColor.BLUE + "---- Ip bans ----");

            if (result.ipBans.isEmpty()) {
                sender.sendMessage("Hasn't bans");
            } else {
                for (String ipBan : result.ipBans) {
                    sender.sendMessage(ChatColor.RED + ipBan);
                }
            }

            sender.sendMessage(ChatColor.BLUE + "-----------------");
        } else {
            sender.sendMessage(ChatColor.RED + "you aren't authorized to execute this command");
        }
    }

    private void printOnlinePlayers(CommandSender sender) {
        if (sender.isOp()) {
            Collection<? extends Player> result = _serverService.getOnlinePlayers();

            sender.sendMessage(ChatColor.BLUE + "---- All online players ----");

            if (result.isEmpty()) {
                sender.sendMessage("There is currently no player online");
            } else {
                for (Player player : result) {
                    sender.sendMessage(ChatColor.GREEN + player.getName());
                }
            }

            sender.sendMessage(ChatColor.BLUE + "----------------------------");
        } else {
            sender.sendMessage(ChatColor.RED + "you aren't authorized to execute this command");
        }
    }
}
