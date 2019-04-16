package de.hubsau.ploraxreport.util.report;
/*Class erstellt von Hubsau


16:48 2019 10.04.2019
Wochentag : Mittwoch


*/




import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.inventory.impl.ReportInventory;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReportManager {

    public HashMap<String, Report> reportProcess;
    public List<Player> wait = new ArrayList<>();

    public ReportManager() {

        reportProcess = new HashMap<>();


    }

    public void startReportProcess(String reporter, String target, ReportInventory inventory){

        if(!reportProcess.containsKey(reporter)){

            Report report = new Report(target, reporter);
            report.setReportInventory(inventory);
            reportProcess.put(reporter, report);
        }
    }

    public void sendReport(Report report){


        Main.getInstance().getMySQL().createReport(report.getReportedUUID(), report);
            Main.getInstance().getMessaging().sendReportToTeam(report);
            reportProcess.remove(report.getReporter(), report);





    }




}
