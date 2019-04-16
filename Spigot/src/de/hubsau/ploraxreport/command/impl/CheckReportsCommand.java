package de.hubsau.ploraxreport.command.impl;
/*Class erstellt von Hubsau


20:17 2019 13.04.2019
Wochentag : Samstag


*/


import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.command.AbstractCommand;
import net.plorax.api.PloraxAPI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class CheckReportsCommand extends AbstractCommand {

    private Main main;


    public CheckReportsCommand(Main main) {
        super("checkreports", Arrays.asList("cr"), true);
        this.main = main;
    }

    @Override
    public void sendCommand(CommandSender sender, String[] args) {


        Player player = (Player) sender;
        if(sender.hasPermission("plorax.seerepots")){

            try{
                if(args.length == 1) {
                    String target = args[0];
                    String reportsAll = main.getMySQL().getReports(PloraxAPI.getFetcher().getUniqueId(target));
                    String[] reports = reportsAll.split("!");

                    player.sendMessage(prefix()+ "§6"+target+ " §7wurde bereits Reportet am");
                    for (String report : reports) {

                        String[] rep = report.split(";");

                        String date = rep[2];
                        player.sendMessage("§c"+date+ " §eGrund: §7"+ rep[0].substring(0, rep[0].length()-1)+ " §7von §6"+ PloraxAPI.getFetcher().getName(UUID.fromString(rep[1])));


                    }




                }else{
                    sender.sendMessage(prefix()+ "§cNutze /checkreports <Spieler>");

                }


            }catch (NullPointerException e){

                player.sendMessage(prefix()+ "§cDieser Spieler konnte nicht gefunden werden");


            }



        }




    }
}
