package com.redafex.xpc.Command;

import com.redafex.XPC.Utils.Config;
import com.redafex.XPC.Utils.Text;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class Main implements CommandExecutor {




    private ArrayList<String> arguments = new ArrayList<>(Arrays.asList("set" , "add", "remove", "wipe", "double"));

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length == 0){
            Text.sendMSG(commandSender, "&cPlease provide more arguments!");
            return true;
        }

        if (arguments.contains(args[0])){

            if (args[0].equalsIgnoreCase("wipe")){
                GiveExp(commandSender, args[1], 0, "&a" + args[1] + "'s XP has been successfully wiped!", "&aSince " + args[1] + " is offline, Their xp will be wiped as soon as they join!", "wipe");
                return true;
            }

            if (args[0].equalsIgnoreCase("double")) {
                GiveExp(commandSender, args[1], 0, "&a" + args[1] + "'s XP has been successfully doubled!", "&aSince " + args[1] + " is offline, Their xp will be doubled as soon as they join!", "double");
                return true;
            }


            if (args.length != 3){
                Text.sendMSG(commandSender, "&cPlease provide an integer");
                return true;
            }

            if (!isStringInteger(args[2])){
                Text.sendMSG(commandSender, "&a" + args[2] + " &cis not a number");
                return true;
            }

            int amount = Integer.parseInt(args[2]);

            if (args[0].equalsIgnoreCase("set")){
                GiveExp(commandSender, args[1], amount, "&a" + args[1] + "'s XP has been successfully set to " + amount + "!", "&aSince " + args[1] + " is offline, Their xp will be set to " + amount + " as soon as they join!", "set");
                return true;
            } if (args[0].equalsIgnoreCase("remove")){
                GiveExp(commandSender, args[1], amount, "&a" + args[1] + "'s XP has been successfully reduced by " + amount + "!", "&aSince " + args[1] + " is offline, Their xp will be reduced by " + amount + " as soon as they join!", "remove");
                return true;
            } if (args[0].equalsIgnoreCase("add")){
                GiveExp(commandSender, args[1], amount, "&a" + args[1] + "'s XP has been successfully increased by " + amount + "!", "&aSince " + args[1] + " is offline, Their xp will be reduced by " + amount + " as soon as they join!", "add");
                return true;
            } if (args[0].equalsIgnoreCase("divide")){
                GiveExp(commandSender, args[1], amount, "&a" + args[1] + "'s XP has been successfully divided by " + amount + "!", "&aSince " + args[1] + " is offline, Their xp will be divided by " + amount + " as soon as they join!", "add");
                return true;
            }

            return true;
        }
        Text.sendMSG(commandSender,"&a" + args[0] + "&c Is not a valid argument!");

        return true;
    }


    @SuppressWarnings("deprecation")
    public void GiveExp(CommandSender cs, String player, int amount, String on , String off, String action){

        Player p = Bukkit.getPlayer(player);

        if(p == null){
            OfflinePlayer of = Bukkit.getOfflinePlayer(player);
            if (of == null){
                Text.sendMSG(cs, "&a" + player + " &cHas never logged into this server before!");
                return;
            } else {
                UUID uuid = of.getUniqueId();
                if (Config.get().contains(uuid + ".Pending")) {
                    ArrayList<String> strings = (ArrayList<String>) Config.get().getStringList(uuid + ".Pending");
                    Config.get().set(uuid + ".Pending", Arrays.asList( action + " " + amount, strings));
                } else {
                    Config.get().set(uuid + ".Pending", Arrays.asList(action + " " + amount));
                }
                Config.save();
                Text.sendMSG(cs, off);
            }
            return;
        }
        Text.sendMSG(cs, on);
        if (action.equalsIgnoreCase("add")){
            p.setLevel(p.getLevel() + amount);
        }if (action.equalsIgnoreCase("set")){
            p.setLevel(amount);
        }if (action.equalsIgnoreCase("wipe")){
            p.setLevel(0);
        }if (action.equalsIgnoreCase("remove")){
            if (p.getLevel() >= amount){
                p.setLevel(p.getLevel() - amount);
                return;
            }
            p.setLevel(amount - p.getLevel());
        }

    }


    public static boolean isStringInteger(String number){
        try{
            Integer.parseInt(number);
        }catch(Exception e ){
            return false;
        }
        return true;
    }


}
