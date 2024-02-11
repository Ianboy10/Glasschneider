package net.ianboy10.glasschneider.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class AntiDrop implements Listener {
    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer(); // Spieler welcher das Item dropt
        ItemStack droppedItem = event.getItemDrop().getItemStack(); // Item welches gedroppt wurde
        if (droppedItem.hasItemMeta() && droppedItem.getItemMeta().hasDisplayName() && droppedItem.getItemMeta().getDisplayName().equals("§5§lGlasschneider")) { // abfrage ob es der Glasschneider ist
            event.setCancelled(true); // droppen verhindern
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cDu kannst dieses Item nicht droppen!")); // Nachricht senden
        }
    }
}
