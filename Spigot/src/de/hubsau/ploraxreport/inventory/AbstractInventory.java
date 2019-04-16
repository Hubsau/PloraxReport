package de.hubsau.ploraxreport.inventory;
/*Class erstellt von Hubsau


23:19 2019 29.03.2019
Wochentag : Freitag


*/



import de.hubsau.ploraxreport.Main;
import de.hubsau.ploraxreport.util.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public abstract class AbstractInventory  {

    private Inventory inventory;

    private int i;
    private int i2;
    private int i3;
    private int i4;
    private int i5;

    private Main main = Main.getInstance();
    private ItemStack placeholder;


    public AbstractInventory(Inventory inventory) {
        this.inventory = inventory;

        this.placeholder = new ItemBuilder(new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7)).name("").build();

    }


    public String getName(){
        return inventory.getName();
    }

    public void open(Player player){
            player.playSound(player.getLocation(), Sound.ZOMBIE_HURT, 1, 1);
            player.openInventory(getInventory());

    }

    public void setItemInAnimation(int slot, ItemStack item, Player player){


        setItemInAnimation(slot, item, player, Sound.ORB_PICKUP, true);


    }


    public void setItemInAnimation(int slot, ItemStack item, Player player, Sound sound1, boolean onlyPlaceHolder){


        if(onlyPlaceHolder){
            if(item.getType().equals(placeholder.getType()))
                player.playSound(player.getLocation(), sound1, 1, 1);

        }else{
            player.playSound(player.getLocation(), sound1, 1, 1);
        }
        inventory.setItem(slot, item);


    }
    public void setItem(int slot, ItemStack item){


        inventory.setItem(slot, item);
    }

    public void fillPlaceHolder(){


        for(int i = 0; i< inventory.getSize(); i++){

            inventory.setItem(i, placeholder);

        }


    }



    public void openAnimated(Player player){

        ItemStack[] contents = inventory.getContents().clone();


        int size = inventory.getSize();

        inventory.clear();
        player.openInventory(inventory);



        i = 0;
        new BukkitRunnable() {
            @Override
            public void run() {




                if(i <= size-1) {


                    setItemInAnimation(i, contents[i], player);


                    i += 8;
                    setItemInAnimation(i, contents[i], player);

                    i += 1;

                }else cancel();
            }
        }.runTaskTimer(main, 10, 10);


        i2 = 1;
        new BukkitRunnable() {
            @Override
            public void run() {




                if(i2 <= size-1) {


                    inventory.setItem(i2, contents[i2]);


                    i2 += 6;
                    setItemInAnimation(i2, contents[i2],player);

                    i2 += 3;


                }else cancel();
            }
        }.runTaskTimer(main, 20, 10);


        i3 = 2;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(i3 <= size-1) {
                    inventory.setItem(i3, contents[i3]);
                    i3 += 4;
                    setItemInAnimation(i3, contents[i3],player);
                    i3 += 5;
                }else cancel();
            }
        }.runTaskTimer(main, 30, 10);
        i4 = 3;

        new BukkitRunnable() {
            @Override
            public void run() {
                if(i4 <= size-1) {
                    inventory.setItem(i4, contents[i4]);
                    i4 += 2;
                    inventory.setItem(i4, contents[i4]);
                    i4 += 7;
                }else cancel();
            }
        }.runTaskTimer(main, 40, 10);
        i5 = 4;


        new BukkitRunnable() {
            @Override
            public void run() {

                if(i5 <= size-10) {


                    inventory.setItem(i5, contents[i5]);

                    i5 += 0;


                    setItemInAnimation(i5, contents[i5],player);

                    i5 += 9;


                }else {

                    setItemInAnimation(i5, contents[i5],player, Sound.LEVEL_UP, false);

                    cancel();
                }


            }
        }.runTaskTimer(main, 40, 10);
     /*

        for(int i = 0; i< 4; i+=8){

            inventory.setItem(i, contents[i]);

            i+=1;
                inventory.setItem(i, contents[i]);

        }

      */



    }

    public Inventory getInventory() {
        return inventory;
    }

    public ItemStack getPlaceholder() {
        return placeholder;
    }


}
