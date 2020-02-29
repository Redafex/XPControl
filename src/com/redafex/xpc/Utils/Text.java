package com.redafex.xpc.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Text {

    public static void sendMSG(CommandSender sender, String s){
        if (sender == null){
            Bukkit.getConsoleSender().sendMessage(Color(s));
        } else {
            sender.sendMessage(Color(s));
        }
    }

    public static String Color(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }
}
