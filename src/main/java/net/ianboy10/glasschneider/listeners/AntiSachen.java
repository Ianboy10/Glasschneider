package net.ianboy10.glasschneider.listeners;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AntiSachen implements Listener {
    public static List<UUID> uuid = new ArrayList<>(); // Liste für die Spieler

    @EventHandler
    public void onOpenInv(InventoryOpenEvent e) {
        if (e.getInventory().getType().equals(InventoryType.PLAYER))
            return; // Abfrage ob es das Inventar des Spielers ist
        if (uuid.contains(e.getPlayer().getUniqueId())) return; // Abfrage ob der Spieler in der Liste eingetragen ist
        uuid.add(e.getPlayer().getUniqueId()); // Spieler in die Liste eintragen
    }

    @EventHandler
    public void onCloseInv(InventoryCloseEvent e) {
        if (e.getInventory().getType().equals(InventoryType.PLAYER))
            return; // Abfrage ob es das Inventar des Spielers ist
        if (!uuid.contains(e.getPlayer().getUniqueId())) return; // Abfrage ob der Spieler in der Liste eingetragen ist
        uuid.remove(e.getPlayer().getUniqueId()); // Spieler aus der Liste entfernen
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked(); // Spieler welcher das Item anklickt
        if (!uuid.contains(e.getWhoClicked().getUniqueId()))
            return; // Abfrage ob der Spieler in der Liste eingetragen ist
        if (!e.getCurrentItem().getI18NDisplayName().equals("§5§lGlasschneider"))
            return; // abfrage ob es der Glasschneider ist
        e.setCancelled(true); // verschieben verhindern
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§cDu darfst keine Items verschieben!")); // Nachricht senden
    }
}
