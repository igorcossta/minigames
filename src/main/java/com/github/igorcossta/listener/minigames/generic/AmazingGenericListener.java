package com.github.igorcossta.listener.minigames.generic;

import com.github.igorcossta.util.message.MessageUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class AmazingGenericListener implements Listener {
    @EventHandler
    void on(final BlockBreakEvent e) {
        MessageUtils.sendMessage(e.getPlayer(), "You broke a block in generic minigame");
    }
}
