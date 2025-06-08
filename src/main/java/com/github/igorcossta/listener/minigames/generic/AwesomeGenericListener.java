package com.github.igorcossta.listener.minigames.generic;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.github.igorcossta.util.message.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class AwesomeGenericListener implements Listener {
    @EventHandler
    void on(final PlayerJumpEvent e) {
        MessageUtils.sendMessage(e.getPlayer(), "That message comes from the generic minigame");
    }
}
