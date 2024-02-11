package net.ianboy10.glasschneider.main;

import net.ianboy10.glasschneider.commands.GiveItem;
import net.ianboy10.glasschneider.listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class Main extends JavaPlugin {
    public static List<UUID> uuid = new ArrayList<>(); // Liste für den "Cooldown" beim Zerbrechen des Glases

    @Override
    public void onEnable() {
        getCommand("getGlasschneider").setExecutor(new GiveItem()); // Registriert den Command für das Item

        Bukkit.getPluginManager().registerEvents(new BreakGlas(this), this); // Registriert den Listener für das Zerbrechen des Glases
        Bukkit.getPluginManager().registerEvents(new NoDMG(), this); // Registriert den Listener für den Schaden
        Bukkit.getPluginManager().registerEvents(new AntiDrop(), this); // Registriert den Listener für das Droppen des Items
        Bukkit.getPluginManager().registerEvents(new AntiSachen(), this); // Registriert den Listener für das Verschieben des Items

        Bukkit.getConsoleSender().sendMessage("§aPlugin enabled!"); // Sendet eine Nachricht in die Konsole
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§cPlugin disabled!"); // Sendet eine Nachricht in die Konsole
    }
}
