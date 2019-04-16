package de.hubsau.ploraxreport.inventory;
/*Class erstellt von Hubsau


18:35 2019 11.04.2019
Wochentag : Donnerstag


*/


import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.util.ItemBuilder;
import de.hubsau.ploraxreport.util.report.Report;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReportInventory extends AbstractInventory {

    public abstract String reasonPrefix();
    public abstract int infoSlot();

    private ItemStack reportInfo;
    private String second;

    public AbstractReportInventory(Inventory inventory) {
        super(inventory);

        reportInfo = new ItemBuilder(Material.REDSTONE_TORCH_ON).name("§c§7➜ §6Report Info").build();


    }


    public void editReportItemLore(ItemStack item, Report report){




        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();


        lore.add("§eSpieler §8➜ §7"+report.getReportedPlayer());

        if(report.getReason() != null)
        lore.add("§eGrund §8➜ §7"+ report.getReason().name());

        if(report.getSecondReasons().size() > 0){

            second = "§eGenauer Grund §8➜ §7";
            report.getSecondReasons().forEach(secondReason -> {

                second += secondReason+ ", ";


            });



            if(second.length() > 5) {

                lore.add(second.substring(0, second.length()-2));

            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        setItem(infoSlot(), item);



    }

    public ItemStack getReportInfo() {
        return reportInfo;
    }



    @Override
    public void open(Player player) {


        setItem(infoSlot(), reportInfo);

        Main.getInstance().getReportManager().wait.add(player);
        super.open(player);

        new BukkitRunnable() {
            @Override
            public void run() {
                Main.getInstance().getReportManager().wait.remove(player);

            }
        }.runTaskLater(Main.getInstance(), 10);
    }


}
