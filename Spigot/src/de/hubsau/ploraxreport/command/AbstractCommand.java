package de.hubsau.ploraxreport.command;
/*Class erstellt von Hubsau


19:48 2019 15.01.2019
Wochentag : Dienstag


*/


import de.hubsau.ploraxreport.io.CoreMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class AbstractCommand extends Command
{
    private boolean onlyPlayer;

    public CoreMessage coreMessage = new CoreMessage();


    public void setOnlyPlayer(boolean onlyPlayer)
    {
        this.onlyPlayer = onlyPlayer;
    }

    public boolean isOnlyPlayer()
    {
        return this.onlyPlayer;
    }

    public AbstractCommand(String name, List<String> aliases, boolean onlyPlayer)
    {
        super(name);
        setAliases(aliases);
        setOnlyPlayer(onlyPlayer);
    }
    public String prefix()

    {
        return  coreMessage.PREFIX;

    }


    public boolean execute(CommandSender sender, String commandLabel, String[] args)
    {
        if (this.onlyPlayer)
        {
            if (!(sender instanceof Player))
            {
                Bukkit.getConsoleSender().sendMessage("Du bist kein Spieler");
                return true;
            }
            sendCommand(sender, args);
        }
        else
        {
            sendCommand(sender, args);
        }
        return true;
    }

    public abstract void sendCommand(CommandSender sender, String[] args);
}