package de.hubsau.ploraxreport.listener.impl.inventory;
/*Class erstellt von Hubsau


19:14 2019 11.04.2019
Wochentag : Donnerstag


*/


import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.inventory.AbstractInventory;
import de.hubsau.ploraxreport.listener.AbstractListener;
import de.hubsau.ploraxreport.util.report.Report;
import de.hubsau.ploraxreport.util.report.ReportManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class InventoryCloseListener extends AbstractListener {


    private Main main;
    private ReportManager reportManager;

    public InventoryCloseListener(Main main) {
        this.main = main;
        reportManager  = main.getReportManager();
    }

    @EventHandler
    public void onInvClose(InventoryCloseEvent event){





        if(event.getPlayer() instanceof Player) {

            if(!reportManager.wait.contains(event.getPlayer())){


                String name = event.getPlayer().getName();

            if (reportManager.reportProcess.containsKey(name)) {

                Report report = reportManager.reportProcess.get(name);

                reportManager.reportProcess.remove(name, report);

                event.getPlayer().sendMessage(coreMessage.REPORT_CANCELED);


            }

        }
        }
    }
}
