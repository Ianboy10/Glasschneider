package net.ianboy10.glasschneider.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class NoDMG implements Listener {
    @EventHandler
    public void onDmg(EntityDamageByEntityEvent e) {
        if(!(e.getDamager() instanceof Player)) return; // Überprüft, ob der Angreifer ein Spieler ist
        Player p = (Player) e.getDamager(); // Der Angreifer ist ein Spieler
        if(!p.getInventory().getItemInMainHand().getI18NDisplayName().equals("§5§lGlasschneider")) return; // Überprüft, ob das Item in der Hand ein Glasschneider ist
        e.setCancelled(true); // Verhindert den Schaden
    }
}
