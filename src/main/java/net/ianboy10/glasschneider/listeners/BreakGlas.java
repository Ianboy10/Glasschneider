package net.ianboy10.glasschneider.listeners;

import net.ianboy10.glasschneider.main.Main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class BreakGlas implements Listener {
    private final JavaPlugin plugin;

    public BreakGlas(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBreak(PlayerInteractEvent e) {
        if (e.getClickedBlock() == null || e.getAction() == null || (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK)))
            return; // Überprüft, ob es ein rechtsklick auf einen block war
        if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().getType() == Material.AIR)
            return; // Überprüft, das Material des Items
        if (e.getPlayer().getInventory().getItemInMainHand() != null && !e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("§5§lGlasschneider"))
            return; // Überprüft den Namen
        if (!isGlassBlock(e.getClickedBlock().getType())) return; // Überprüft, ob es sich um einen Glasblock handelt
        if (Main.uuid.contains(e.getPlayer().getUniqueId()))
            return; // Überprüft, ob der Spieler in der Liste ist

        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§eDas Glas wird zerbrochen, bitte warte einen Moment..."));
        Main.uuid.add(e.getPlayer().getUniqueId());

        Block clickedBlock = e.getClickedBlock(); // Nimmt das Glas
        Material material = clickedBlock.getType();
        byte data = clickedBlock.getData();

        new BukkitRunnable() {
            @Override
            public void run() {
                Block clickedBlock = e.getClickedBlock(); // Nimmt das Glas
                if (clickedBlock != null) {
                    clickedBlock.setType(material);
                    clickedBlock.setData(data);
                    respawnBlock(clickedBlock.getLocation(), clickedBlock.getType(), clickedBlock.getData());
                    clickedBlock.setType(Material.AIR); // Löscht das Glas
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aGlas wurde zerbrochen!"));
                    Main.uuid.remove(e.getPlayer().getUniqueId());
                }
            }
        }.runTaskLater(plugin, 3 * 20);
        return;
    }

    private void respawnBlock(Location location, Material block, byte data) {
        new BukkitRunnable() {
            @Override
            public void run() {
                location.getBlock().setType(block); // Setze den Block zurück
                location.getBlock().setData(data); // Setzt die Farbe
            }
        }.runTaskLater(plugin, 30 * 20);
    }

    private boolean isGlassBlock(Material material) { // überprüft, ob es wirklich Glas ist
        return material == Material.GLASS || material == Material.STAINED_GLASS || material == Material.THIN_GLASS || material == Material.STAINED_GLASS_PANE;
    }
}
