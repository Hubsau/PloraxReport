package de.hubsau.ploraxreport.listener;
/*Class erstellt von Hubsau


20:26 2019 15.01.2019
Wochentag : Dienstag


*/


import de.hubsau.ploraxreport.io.CoreMessage;
import org.bukkit.event.Listener;

public abstract class AbstractListener implements Listener {


    public CoreMessage coreMessage = new CoreMessage();

    public String prefix()

    {
        return  coreMessage.PREFIX;

    }

    public CoreMessage CoreMessage() {
        return coreMessage;
    }
}
