package de.hubsau.ploraxreport.inventory.impl;
/*Class erstellt von Hubsau


23:32 2019 29.03.2019
Wochentag : Freitag


*/




import de.hubsau.ploraxreport.inventory.AbstractReportInventory;
import de.hubsau.ploraxreport.util.ItemBuilder;
import de.hubsau.ploraxreport.util.report.Reason;
import de.hubsau.ploraxreport.util.report.Report;
import de.hubsau.ploraxreport.util.report.SecondReason;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class Report2Inventory extends AbstractReportInventory {

    int i;
    int i2;
    private Report report;
    private ItemStack back, further;
    public static String invName = "§4§7➜ §4§lReport ";
    private String second;



    public Report2Inventory(Report report) {

        super(Bukkit.createInventory(null, 9 * 3, invName + report.getReportedPlayer()));

        this.report = report;
        Reason reason = report.getReason();

        report.getSecondReasons().clear();

        back = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short)14)).name("§4Zurück").build();
        further = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short)5)).name("§aWeiter").build();



        fillPlaceHolder();
        i = 8;

        int size = reason.getSecondReasons().size();



            if(size > 0){

                if (size%2 == 0) {


                    i += (9-size)/2;
                } else {
                    i += ((9 - size) / 2) + 1;

                }
            for (SecondReason secondReason : reason.getSecondReasons()) {


                setItem(i, new ItemBuilder(Material.BOOK).name(reasonPrefix()+ secondReason.name()).build());
                i++;

            }
            setItem(0, getReportInfo());
            setItem(25, back);
            setItem(26, further);

        }else{

        }




    }




    public ItemStack getBack() {
        return back;
    }



    public ItemStack getFurther() {
        return further;
    }

    @Override
    public void open(Player player) {

        if(report.getReason().getSecondReasons().size() > 0) {
            super.open(player);

        }else{

            ContinueInventory continueInventory = new ContinueInventory(player.getName(), report);

            report.setContinueInventory(continueInventory);
            continueInventory.open(player);




        }

        editReportItemLore(getReportInfo(), report);



    }

    @Override
    public String reasonPrefix() {
        return "§7➜ §6§l";
    }

    @Override
    public int infoSlot() {
        return 0;
    }
}
