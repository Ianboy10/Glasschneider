package net.ianboy10.glasschneider.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class GiveItem implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(!(sender instanceof Player)) return false; // Abfrage ob der Sender ein Spieler ist
        Player p = (Player) sender; // Der Sender ist ein Spieler
        if(!(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType() == Material.AIR)) { // Abfrage ob die Hand leer ist
            p.sendMessage("§cDeine Hand muss leer sein!");
            return false;
        }

        List<String> lore = new ArrayList<>(); // Erstellen einer Liste
        lore.add("§cRechtsklick auf Glas zum zerstören.");
        lore.add("§f§ovon " + p.getName());

        ItemStack item = new ItemStack(Material.SHEARS, 1); // Erstellen eines Items
        ItemMeta meta = (ItemMeta) item.getItemMeta(); // Erstellen eines Metadaten-Objekts
        meta.setDisplayName("§5§lGlasschneider"); // Setzen des Namens
        meta.setLore(lore); // Setzen der Lore
        item.setItemMeta(meta); // Setzen der Metadaten

        p.getInventory().setItemInMainHand(item); // Setzen des Items in die Hand
        p.sendMessage("§aDu hast einen §a§lGlasschneider §aerhalten."); // Senden einer Nachricht
        return false;
    }
}
