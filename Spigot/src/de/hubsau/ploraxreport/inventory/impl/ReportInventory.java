package de.hubsau.ploraxreport.inventory.impl;
/*Class erstellt von Hubsau


23:32 2019 29.03.2019
Wochentag : Freitag


*/




import de.hubsau.ploraxreport.inventory.AbstractReportInventory;
import de.hubsau.ploraxreport.util.ItemBuilder;
import de.hubsau.ploraxreport.util.report.Reason;
import de.hubsau.ploraxreport.util.report.Report;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class ReportInventory extends AbstractReportInventory {

    int i;


    public static String invName = "§7➜ §4§lReport ";
    private ItemStack stop, further;

    public ReportInventory(String player) {


        super(Bukkit.createInventory(null, 9*3, invName+player));


        fillPlaceHolder();

        stop = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short)14)).name("§4Abbrechen").build();
        further = new ItemBuilder(new ItemStack(Material.WOOL, 1, (short)5)).name("§aWeiter").build();
        i= 11;

        for (Reason reason : Reason.values()) {

            setItem(i, new ItemBuilder(Material.BOOK).name("§7➜ §6§l"+reason.name()).build());
            i++;
        }
        setItem(25, stop);
        setItem(26, further);



    }

    @Override
    public void open(Player player) {
        super.open(player);
    }

    public ItemStack getStop() {
        return stop;
    }

    public ItemStack getFurther() {
        return further;
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
