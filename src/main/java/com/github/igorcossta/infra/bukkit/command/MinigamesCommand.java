package com.github.igorcossta.infra.bukkit.command;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.game.Game;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MinigamesCommand implements CommandExecutor {
    private final Minigames instance;

    public MinigamesCommand(Minigames instance) {
        this.instance = instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender,
                             @NotNull Command command,
                             @NotNull String label,
                             @NotNull String[] args) {
        if (!(sender instanceof Player p)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        if (args.length < 1) {
            p.sendMessage("Usage: /<command> <...>");
            return true;
        }

        Location location = p.getLocation();
        Game game = instance.getGame();

        game.setSpawnLocation(location.x(), location.y() + 5, location.z());
        game.setExitLocation(location.x(), location.y() + 5, location.z() + 5);
        game.setWorld(location.getWorld().getName());

        try {
            if (args[0].equalsIgnoreCase("join")) {
                game.addPlayer(p.getUniqueId());
                p.sendMessage("You join the game");
            }

            if (args[0].equalsIgnoreCase("open")) {
                game.open();
            }

            if (args[0].equalsIgnoreCase("start")) {
                game.start();
                return true;
            }

            if (args[0].equalsIgnoreCase("end")) {
                game.end();
                return true;
            }
        } catch (RuntimeException ex) {
            p.sendMessage("[Minigames] " + ex.getMessage());
        }

        return true;
    }
}
