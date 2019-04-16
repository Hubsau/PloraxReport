package de.hubsau.ploraxreport.listener.impl.inventory;
/*Class erstellt von Hubsau


16:47 2019 10.04.2019
Wochentag : Mittwoch


*/


import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.inventory.AbstractReportInventory;
import de.hubsau.ploraxreport.inventory.impl.ContinueInventory;
import de.hubsau.ploraxreport.inventory.impl.Report2Inventory;
import de.hubsau.ploraxreport.inventory.impl.ReportInventory;
import de.hubsau.ploraxreport.listener.AbstractListener;
import de.hubsau.ploraxreport.util.ItemBuilder;
import de.hubsau.ploraxreport.util.report.Reason;
import de.hubsau.ploraxreport.util.report.Report;
import de.hubsau.ploraxreport.util.report.ReportManager;
import de.hubsau.ploraxreport.util.report.SecondReason;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ClickListener extends AbstractListener {



    private Main main;
    private ReportManager manager;

    public ClickListener(Main main) {
        this.main = main;
        this.manager = main.getReportManager();

    }


    @EventHandler
    public void onClick(InventoryClickEvent event){
        try{

            if(event.getWhoClicked() instanceof Player){

                Player player = (Player) event.getWhoClicked();


                ItemStack current = event.getCurrentItem();



                String name = event.getInventory().getName();

                if(!((name.startsWith(ReportInventory.invName)
                       || name.startsWith(Report2Inventory.invName)
                       || name.equals(ContinueInventory.invName)
                ))){

                    return;
                }

                if(current.getType().equals(Material.STAINED_GLASS_PANE) || current.getType().equals(Material.REDSTONE_TORCH_ON)){
                    event.setCancelled(true);
                    return;
                }
                event.setCancelled(true);

                Report report = manager.reportProcess.get(player.getName());




                        if (name.startsWith(ReportInventory.invName)) {





                            ReportInventory reportInventory = report.getReportInventory();



                            if (current.equals(reportInventory.getFurther())) {


                                if (report.getReason() == null) {
                                    player.sendMessage(prefix() + "§cWähle einen Grund aus, um Fortzufahren!");


                                } else {
                                    Report2Inventory report2Inventory = new Report2Inventory(report);
                                    report.setReport2Inventory(report2Inventory);
                                    report2Inventory.open(player);


                                }

                            } else {
                                if (current.equals(reportInventory.getStop())) {


                                    report = null;
                                    manager.reportProcess.remove(player.getName());
                                    player.closeInventory();

                                    player.sendMessage(coreMessage.REPORT_CANCELED);

                                } else {

                                    Reason reason = report.getReason();
                                    if (reason == null) {

                                        enchantItem(current, reportInventory, report);

                                    } else {

                                        new ItemBuilder(reason.getItemStack()).type(Material.BOOK).build();

                                        enchantItem(current, reportInventory, report);


                                    }
                                }
                            }


                        }else {


                            if (name.startsWith(Report2Inventory.invName)) {

                                //Report Page 2

                                Report2Inventory report2Inventory = report.getReport2Inventory();



                                if (current.equals(report2Inventory.getFurther())) {


                                    if (report.getSecondReasons().size() == 0) {
                                        player.sendMessage(prefix() + "§cWähle einen weiteren Grund aus, um Fortzufahren!");


                                    } else {

                                        ContinueInventory continueInventory = new ContinueInventory(player.getName(), report);
                                        report.setContinueInventory(continueInventory);
                                        continueInventory.open(player);

                                    }


                                } else {
                                    if (current.equals(report2Inventory.getBack())) {

                                        report.getReportInventory().open(player);

                                    } else {

                                        if (isEnchanted(current)) {

                                            removeEnchatment(current, report2Inventory, report);
                                        } else {
                                            enchantItem(current, report2Inventory, report);

                                        }

                                    }
                                }


                            }
                            else{

                                if(name.equals(ContinueInventory.invName)){


                                    ContinueInventory continueInventory = report.getContinueInventory();

                                    if(current.equals(continueInventory.getBack())){


                                        if(report.getSecondReasons().size() == 0){

                                            report.getReportInventory().open(player);
                                        }else report.getReport2Inventory().open(player);
                                    }else{

                                        if(current.equals(continueInventory.getConfirm())){

                                            manager.sendReport(report);
                                            player.closeInventory();
                                        }
                                    }
                                }
                            }

                        }

            }

        }catch (Exception e){


            e.printStackTrace();
        }


    }




    private void enchantItem(ItemStack current, AbstractReportInventory reportInventory, Report report){

        new ItemBuilder(current).type(Material.ENCHANTED_BOOK).build();
        String name = current.getItemMeta().getDisplayName().replace(reportInventory.reasonPrefix(), "");



        if(reportInventory instanceof Report2Inventory){
            Report2Inventory report2Inventory = (Report2Inventory) reportInventory;
            List<SecondReason> secondReasons = report.getSecondReasons();

            SecondReason secondReason = SecondReason.valueOf(name);
            secondReasons.add(secondReason);

            report2Inventory.editReportItemLore(report2Inventory.getReportInfo(), report);

            return;

        }else {
            Reason newReason = Reason.valueOf(name);
            newReason.setItemStack(current);
            report.setReason(Reason.valueOf(name));

        }

        reportInventory.editReportItemLore(reportInventory.getReportInfo(), report);

    }



    private void removeEnchatment(ItemStack current, Report2Inventory inventory, Report report){
        new ItemBuilder(current).type(Material.BOOK).build();
        String name = current.getItemMeta().getDisplayName().replace(inventory.reasonPrefix(), "");

        Report2Inventory report2Inventory =  inventory;
        List<SecondReason> secondReasons = report.getSecondReasons();
        secondReasons.remove(SecondReason.valueOf(name));

        inventory.editReportItemLore(report2Inventory.getReportInfo(), report);



    }
    private boolean isEnchanted(ItemStack item){

        return !item.getType().equals(Material.BOOK);


    }

}
