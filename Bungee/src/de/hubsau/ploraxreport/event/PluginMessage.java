package de.hubsau.ploraxreport.event;
/*Class erstellt von Hubsau


21:45 2019 16.04.2019
Wochentag : Dienstag


*/


import de.dytanic.cloudnet.api.CloudAPI;
import de.dytanic.cloudnet.lib.player.CloudPlayer;
import de.dytanic.cloudnet.lib.player.permission.PermissionEntity;
import de.dytanic.cloudnet.lib.player.permission.PermissionPool;
import de.hubsau.ploraxreport.Main;
import net.md_5.bungee.BungeeCord;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Event;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import de.hubsau.ploraxreport.report.Reason;
import de.hubsau.ploraxreport.report.Report;
import de.hubsau.ploraxreport.report.SecondReason;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.List;

public class PluginMessage implements Listener {

    private String prefix;

    private Main main;

    public PluginMessage(String prefix, Main main) {
        this.prefix = prefix;
        this.main = main;
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent event) {


        if (event.getTag().equals("RPSend")) {

            DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
            try {


                String channel = in.readUTF();


                if (channel.equals("Report")) {


                    try {

                        prefix = in.readUTF();
                        short id = in.readShort();
                        String reporter = in.readUTF();
                        String reported = in.readUTF();
                        String reportReason = in.readUTF();
                        String reportSecondReason = in.readUTF();

                        Report report = new Report(reported, reporter, id);




                        report.setReason(Reason.valueOf(reportReason));
                        List<SecondReason> secondReasons = report.getSecondReasons();
                        for (String s : reportSecondReason.split(",")) {


                            if (!s.equals(""))
                                secondReasons.add(SecondReason.valueOf(s));
                        }
                        main.getReports().add(report);


                        for (CloudPlayer cloudPlayer : CloudAPI.getInstance().getOnlinePlayers()) {

                            System.out.println(cloudPlayer.getName());

                            PermissionEntity permissionEntity = cloudPlayer.getPermissionEntity();
                            PermissionPool permissionPool = CloudAPI.getInstance().getPermissionPool();
                            if (permissionEntity.hasPermission(permissionPool, "plorax.reports.see", permissionEntity.getHighestPermissionGroup(permissionPool).getName())) {


                                ProxiedPlayer player = BungeeCord.getInstance().getPlayer(cloudPlayer.getName());
                                player.sendMessage("");

                                TextComponent click = new TextComponent(reported);

                                TextComponent text = new TextComponent(prefix + "§7Der Spieler ");
                                TextComponent text2 = new TextComponent(" §7wurde von §6" + reporter + " §7gemeldet");
                                click.setColor(ChatColor.RED);
                                click.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Klicke hier um dich zu §4" + reported + "§7 zu teleportieren §8(§7" + BungeeCord.getInstance().getPlayer(reported).getServer().getInfo().getName() + "§8)").create()));
                                click.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/editreport " + report.getID()));

                                text.addExtra(click);
                                text.addExtra(text2);

                                player.sendMessage(text);
                                player.sendMessage(prefix + "§6Grund: §7" + reportReason);

                                if (reportSecondReason.equals("")) {

                                    return;
                                }
                                player.sendMessage(prefix + "§6Weitere Gründe: §7" + reportSecondReason);
                                player.sendMessage("");


                            }
                        }
                        return;


                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    return;


                } else return;
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }

    }


    }
