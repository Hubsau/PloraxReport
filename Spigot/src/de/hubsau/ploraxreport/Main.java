package de.hubsau.ploraxreport;
/*Class erstellt von Hubsau


13:56 2019 10.04.2019
Wochentag : Mittwoch


*/


import de.hubsau.ploraxreport.command.impl.CheckReportsCommand;
import de.hubsau.ploraxreport.command.impl.ReportCommand;
import de.hubsau.ploraxreport.listener.impl.inventory.ClickListener;
import de.hubsau.ploraxreport.listener.impl.inventory.InventoryCloseListener;
import de.hubsau.ploraxreport.setup.Setup;
import de.hubsau.ploraxreport.util.Messaging;
import de.hubsau.ploraxreport.util.database.MySQL;
import de.hubsau.ploraxreport.util.report.ReportManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private ReportManager reportManager;
    private Messaging messaging;
    private MySQL mySQL;


    @Override
    public void onEnable() {
        instance = this;


        initialize();

    }


    private void initialize(){


        reportManager = new ReportManager();

        Setup setup = new Setup(this, "ploraxapply");

        setup.addCommand(new ReportCommand());
        setup.addCommand(new CheckReportsCommand(this));
        setup.addListener(new ClickListener(this));
        setup.addListener(new InventoryCloseListener(this));

        setup.register();

        messaging = new Messaging(this);

        getServer().getMessenger().registerOutgoingPluginChannel(this, "RPSend");
        getServer().getMessenger().registerIncomingPluginChannel(this, "RPSend",messaging);


        getServer().getMessenger().registerIncomingPluginChannel(this, "Report", messaging);



        mySQL = new MySQL(this);




    }


    public MySQL getMySQL() {
        return mySQL;
    }


    public Messaging getMessaging() {
        return messaging;
    }

    public ReportManager getReportManager() {
        return reportManager;
    }

    public static Main getInstance() {
        return instance;
    }
}
