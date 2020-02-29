package com.redafex.xpc.Events;

import com.redafex.xpc.Utils.Config;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;

import java.util.UUID;

public class OnJoinGive implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        for (String s : Config.get().getStringList(uuid + ".Pending")){
            String[] args = s.split(" ");
            int sec = Integer.parseInt(args[1]);
            String event = args[0];
            if (event.equalsIgnoreCase("add")){
                p.setLevel(p.getLevel() + sec);
            }if (event.equalsIgnoreCase("set")){
                p.setLevel(sec);
            }if (event.equalsIgnoreCase("wipe")){
                p.setLevel(0);
            }if (event.equalsIgnoreCase("remove")){
                if (p.getLevel() >= sec){
                    p.setLevel(p.getLevel() - sec);
                } else {
                    p.setLevel(0);
                }
            }if (event.equalsIgnoreCase("double")){
                p.setLevel(p.getLevel() * 2);
            } if (event.equalsIgnoreCase("divide")){
                if (p.getLevel() >= sec){
                    p.setLevel(p.getLevel() / sec);
                } else {
                    p.setLevel(0);
                }
            }
        }

        Config.get().set(uuid.toString(), null);
        Config.save();
    }

    @EventHandler
    public void onFirstJoin(PlayerJoinEvent e){
        
    }


}
