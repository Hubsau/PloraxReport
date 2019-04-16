package de.hubsau.ploraxreport;/*Class erstellt von Hubsau


20:32 2019 13.04.2019
Wochentag : Samstag


*/


import de.hubsau.ploraxreport.event.PluginMessage;
import de.hubsau.ploraxreport.report.Report;
import net.md_5.bungee.BungeeCord;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Main extends Plugin {


    private List<Report> reports;
    private static Main instance;
    private String prefix;


    @Override
    public void onEnable() {

        instance = this;

        BungeeCord.getInstance().getPluginManager().registerListener(this, new PluginMessage(prefix, this));
        BungeeCord.getInstance().registerChannel("Report");
        BungeeCord.getInstance().registerChannel("RPSend");

        BungeeCord.getInstance().getPluginManager().registerCommand(this, new ReportCommand("editreport", this));


        reports = new ArrayList<>();


    }

    public List<Report> getReports() {
        return reports;
    }

    public static Main getInstance() {
        return instance;
    }

    public String getPrefix() {
        return prefix;
    }




}
