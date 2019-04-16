package de.hubsau.ploraxreport.command.impl;
/*Class erstellt von Hubsau


16:15 2019 10.04.2019
Wochentag : Mittwoch


*/


import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.command.AbstractCommand;
import de.hubsau.ploraxreport.inventory.impl.ReportInventory;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class ReportCommand extends AbstractCommand {


    public ReportCommand() {
        super("report", Arrays.asList(), true);
    }

    @Override
    public void sendCommand(CommandSender sender, String[] args) {


        Player player = (Player)sender;
        if(args.length == 1){


            String target = args[0];

            for (CloudPlayer cloudPlayer : CloudAPI.getInstance().getOnlinePlayers()) {
                if(cloudPlayer.getName().equals(target)){


                    ReportInventory inventory = new ReportInventory(target);
                    inventory.open(player);

                    Main.getInstance().getReportManager().startReportProcess(player.getName(), target, inventory);

                    return;
                }
            }
            player.sendMessage(prefix()+ "§6"+target+ "§c ist nicht auf den Netzwerk");





        }else{
           sender.sendMessage(prefix()+ "§cNutze /report <Spieler>");
        }



    }
}
