package de.hubsau.ploraxreport.report;


/*Class erstellt von Hubsau


14:02 2019 10.04.2019
Wochentag : Mittwoch


*/




import java.util.ArrayList;
import java.util.List;

public class Report {

    private String reportedPlayer,reporter;

    Reason reason;
    public boolean inprocess;
    private String processer;
    private short id;

    private List<SecondReason> secondReasons;



    public Report(String reportedPlayer, String reporter, short id) {
        this.reportedPlayer = reportedPlayer;
        this.reporter = reporter;
        secondReasons = new ArrayList<>();
        this.id = id;

    }

    public void setProcesser(String processer) {
        this.processer = processer;
    }

    public String getProcesser() {
        return processer;
    }

    public short getID() {
        return id;
    }

    public void setInprocess(boolean inprocess) {
        this.inprocess = inprocess;
    }

    public boolean isInprocess() {
        return inprocess;
    }


    public List<SecondReason> getSecondReasons() {
        return secondReasons;
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
