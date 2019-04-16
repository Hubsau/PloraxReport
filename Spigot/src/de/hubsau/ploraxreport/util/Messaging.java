package de.hubsau.ploraxreport.util;
/*Class erstellt von Hubsau


14:02 2019 10.04.2019
Wochentag : Mittwoch


*/


import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.io.CoreMessage;
import de.hubsau.ploraxreport.util.report.Report;
import io.netty.util.NettyRuntime;
import net.plorax.api.PloraxAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class Messaging implements PluginMessageListener {



    private Main main;
    String scdReason;

    public Messaging(Main main) {
        this.main = main;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {




        if(channel.equals("Report")){


            DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
            try {

                String action = in.readUTF();

                String[] teleport = action.split(":");

                Player player1 = Bukkit.getPlayer(teleport[0]);
                Player player2 = Bukkit.getPlayer(teleport[1]);

                if(!PloraxAPI.getSpecPlayers().containsKey(player1)){

                    player1.performCommand("spec");
                }
                player1.sendMessage(new CoreMessage().PREFIX+ "§7Du bearbeitest nun den Report von §6"+player2.getName());
                player1.teleport(player2);










            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }else{


        }

    }
    public void sendReportToTeam(Report report){




        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);


        //proxy report

        b = new ByteArrayOutputStream();
        out = new DataOutputStream(b);
        try {
            out.writeUTF("Report");
            out.writeUTF(new CoreMessage().PREFIX);
            out.writeShort(report.getID());
            out.writeUTF(report.getReporter());
            out.writeUTF(report.getReportedPlayer());
            out.writeUTF(report.getReason().name());
            scdReason = "";
            report.getSecondReasons().forEach(reason->{

                scdReason+=reason+",";


            });

            if(scdReason.equals("")){

                out.writeUTF(scdReason);

            }else out.writeUTF(scdReason.substring(0, scdReason.length()-1));



        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getPlayer(report.getReporter()).sendMessage("§7Dein Report über den Spieler §4"+ report.getReportedPlayer()+ " §7wurde gesendet");

        Bukkit.getPlayer(report.getReporter()).sendPluginMessage(Main.getPlugin(Main.class), "RPSend", b.toByteArray());



    }



}
