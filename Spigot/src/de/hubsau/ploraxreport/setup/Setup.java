package de.hubsau.ploraxreport.setup;
/*Class erstellt von Hubsau


19:26 2019 15.01.2019
Wochentag : Dienstag


*/


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class Setup {


    private ArrayList<Command> commands = new ArrayList();
    private ArrayList<Listener> listeners = new ArrayList();
    private Plugin plugin;
    private String fallbackPrefix;

    public Setup(Plugin plugin, String fallbackPrefix) {
        this.setPlugin(plugin);
        this.fallbackPrefix = fallbackPrefix;
    }

    public void addCommand(Command command) {
        this.commands.add(command);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void register() {
        CraftServer server = (CraftServer) Bukkit.getServer();
        this.commands.forEach((command) -> {
            server.getCommandMap().register(this.fallbackPrefix, command);
        });
        this.listeners.forEach((listener) -> {
            Bukkit.getPluginManager().registerEvents(listener, this.getPlugin());
        });
    }

    public Plugin getPlugin() {
        return this.plugin;
    }

    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

}
