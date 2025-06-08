package com.github.igorcossta.util.message;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;

import java.util.Collection;
import java.util.function.Supplier;

public final class MessageUtils {
    private static Supplier<String> defaultPrefix = () -> "<light_purple>[Minigames]</light_purple>";

    private MessageUtils() {
    }

    public static void sendMessage(final CommandSender receiver, final String message) {
        sendMessage(receiver, message, defaultPrefix.get());
    }

    public static void sendMessage(final Collection<CommandSender> receivers, final String message) {
        sendMessage(receivers, message, defaultPrefix.get());
    }

    private static void sendMessage(final CommandSender receiver, final String message, final String prefix) {
        if (!message.isEmpty()) {
            receiver.sendMessage(MiniMessage.miniMessage().deserialize(prefix + " " + message));
        }
    }

    private static void sendMessage(final Collection<CommandSender> receivers, final String message, final String prefix) {
        if (!message.isEmpty()) {
            for (CommandSender receiver : receivers) {
                receiver.sendMessage(MiniMessage.miniMessage().deserialize(prefix + " " + message));
            }
        }
    }

    /**
     * Get the default prefix
     *
     * @return the prefix
     */
    public static String getPrefix() {
        return defaultPrefix.get();
    }

    /**
     * Set the default prefix
     *
     * @param prefix the prefix
     */
    public static void setPrefix(final Supplier<String> prefix) {
        MessageUtils.defaultPrefix = prefix;
    }

    /**
     * Set the default prefix
     *
     * @param prefix the prefix
     */
    public static void setPrefix(final String prefix) {
        setPrefix(() -> prefix);
    }
}
