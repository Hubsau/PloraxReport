package de.hubsau.ploraxreport.inventory.impl;
/*Class erstellt von Hubsau


18:21 2019 11.04.2019
Wochentag : Donnerstag


*/



import de.hubsau.ploraxreport.inventory.AbstractReportInventory;
import de.hubsau.ploraxreport.util.ItemBuilder;
import de.hubsau.ploraxreport.util.report.Report;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;


public class ContinueInventory extends AbstractReportInventory {

        private Report report;
    private ItemStack back, confirm;


        public static String invName = "§4§7➜ §4§lSicher?";

        public ContinueInventory(String player, Report report) {

            super(Bukkit.createInventory(null, 9, invName));

            this.report = report;

            fillPlaceHolder();


            back = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short)14)).name("§4Zurück").build();
            confirm = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short)13)).name("§2Report abschicken").build();

            setItem(7, back);
            setItem(8, confirm);
            editReportItemLore(getReportInfo(), report);


        }


    public ItemStack getBack() {
        return back;
    }

    public ItemStack getConfirm() {
        return confirm;
    }

    @Override
    public String reasonPrefix() {
        return "§7➜ §6§l";
    }

    @Override
    public int infoSlot() {
        return 4;
    }
}
