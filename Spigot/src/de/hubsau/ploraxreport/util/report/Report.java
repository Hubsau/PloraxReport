package de.hubsau.ploraxreport.util.report;
/*Class erstellt von Hubsau


14:02 2019 10.04.2019
Wochentag : Mittwoch


*/


import com.google.common.annotations.Beta;
import de.hubsau.ploraxreport.inventory.impl.ContinueInventory;
import de.hubsau.ploraxreport.inventory.impl.Report2Inventory;
import de.hubsau.ploraxreport.inventory.impl.ReportInventory;
import net.plorax.api.PloraxAPI;
import org.bukkit.event.EventHandler;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Report {

    private String reportedPlayer,reporter;

    Reason reason;
    private ReportInventory reportInventory;
    private Report2Inventory report2Inventory;
    private ContinueInventory continueInventory;
    private List<SecondReason> secondReasons;
    private short id;
    private UUID uuid;
    private UUID reportedUUID;
    private String dateOfCreate;



    public Report(String reportedPlayer, String reporter) {
        this.reportedPlayer = reportedPlayer;
        this.reporter = reporter;
        secondReasons = new ArrayList<>();
        id = (short) (Math.random()*10000);

        this.uuid = PloraxAPI.getFetcher().getUniqueId(reporter);
        this.reportedUUID = PloraxAPI.getFetcher().getUniqueId(reportedPlayer);

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", new Locale("d", "DE"));

        this.dateOfCreate = format.format(new Date());




    }

    public UUID getReportedUUID() {
        return reportedUUID;
    }

    public String getDateOfCreate() {
        return dateOfCreate;
    }

    public UUID getUUIDReporter() {
        return uuid;
    }



    public short getID() {
        return id;
    }

    public List<SecondReason> getSecondReasons() {
        return secondReasons;
    }

    public void setReport2Inventory(Report2Inventory report2Inventory) {
        this.report2Inventory = report2Inventory;
    }

    public void setReportInventory(ReportInventory reportInventory) {
        this.reportInventory = reportInventory;
    }

    public Report2Inventory getReport2Inventory() {
        return report2Inventory;
    }

    public ReportInventory getReportInventory() {
        return reportInventory;
    }

    public ContinueInventory getContinueInventory() {
        return continueInventory;
    }

    public void setContinueInventory(ContinueInventory continueInventory) {
        this.continueInventory = continueInventory;
    }



    public String getReportedPlayer() {
        return reportedPlayer;
    }

    public void setReportedPlayer(String reportedPlayer) {
        this.reportedPlayer = reportedPlayer;
    }

    public String getReporter() {
        return reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
    }

    public Reason getReason() {
        return reason;
    }

    public void setReason(Reason reason) {
        this.reason = reason;
    }



}
