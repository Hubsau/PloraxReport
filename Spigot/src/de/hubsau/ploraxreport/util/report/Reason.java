package de.hubsau.ploraxreport.util.report;
/*Class erstellt von Hubsau


14:03 2019 10.04.2019
Wochentag : Mittwoch


*/


import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public enum Reason {




    HACKING(SecondReason.KillAura, SecondReason.No_Knockback, SecondReason.Range,SecondReason.Fly, SecondReason.Teleport, SecondReason.X_Ray, SecondReason.Sonstiges),
    CHAT(SecondReason.Spaming, SecondReason.Werbung,SecondReason.Drohung, SecondReason.Beleidigung, SecondReason.Provokation),
    SPIELER(SecondReason.Name, SecondReason.Skin, SecondReason.Cape, SecondReason.Clan, SecondReason.Sonstiges),
    BUGUSING,
    TEAMING;



    Reason(SecondReason... reason2){

        secondReasons = new ArrayList<>();

        for (SecondReason s : reason2) {
            secondReasons.add(s);
        }

    }
   /*

    public Reason setSecondReason(SecondReason reason2){

        if(secondReasons.contains(reason2.toString())){


            secondReason = reason2.toString();
            return this;

        }else{
            return null;

        }

    }

    */
    private ItemStack itemStack;

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }


    //private String secondReason;
    private List<SecondReason> secondReasons;



    public List<SecondReason> getSecondReasons() {
        return secondReasons;
    }
}
