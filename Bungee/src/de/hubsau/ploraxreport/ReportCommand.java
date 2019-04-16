package de.hubsau.ploraxreport;/*Class erstellt von Hubsau


21:05 2019 13.04.2019
Wochentag : Samstag


*/


import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.CommandSender;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import de.hubsau.ploraxreport.report.Report;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReportCommand extends Command {


    private short reportID;
    private Main main;
    private Report report;



    public ReportCommand(String name, Main main) {
        super(name);
        this.main = main;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {

        if(args.length == 1){

            if(sender instanceof ProxiedPlayer){

                ProxiedPlayer player = (ProxiedPlayer) sender;

                if(player.hasPermission("plorax.de.hubsau.ploraxreport.report.see")){


                    try {

                        reportID = (short) Integer.parseInt(args[0]);


                    }catch (NumberFormatException e){
                        player.sendMessage(main.getPrefix()+ "§cDieser Report konnte nicht gefunden worden.");
                        return;
                    }
                    main.getReports().forEach(report->{



                        if(report.getID() == reportID){



                            if(!report.isInprocess()) {


                                report.setInprocess(true);
                                report.setProcesser(player.getName());

                                this.report = report;


                                player.sendMessage(main.getPrefix()+ "§7Teleportiere zu §6"+report.getReportedPlayer());
                                ServerInfo server = BungeeCord.getInstance().getPlayer(report.getReportedPlayer()).getServer().getInfo();
                                if(player.getServer().getInfo() != server){

                                    player.connect(server);

                                }


                                BungeeCord.getInstance().getScheduler().schedule(main, new Runnable() {
                                    @Override
                                    public void run() {
                                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                        DataOutputStream out = new DataOutputStream(stream);


                                        try{
                                            out.writeUTF(player.getName()+ ":"+ report.getReportedPlayer());

                                        }catch (IOException e){


                                            e.printStackTrace();
                                        }
                                        server.sendData("Report", stream.toByteArray());
                                    }
                                }, 1, TimeUnit.SECONDS);

                                return;


                            }else{
                                player.sendMessage(main.getPrefix()+ "§cDieser Report wird/wurde bereits von §6"+ report.getProcesser()+ "§c bearbeitet");

                            }


                        }


                    });

                    if(this.report != null) main.getReports().remove(report);


                    BungeeCord.getInstance().getScheduler().schedule(main, new Runnable() {
                        @Override
                        public void run() {

                            main.getReports().remove(report);

                        }
                    },5, TimeUnit.MINUTES);

                }
            }
        }



    }

}
