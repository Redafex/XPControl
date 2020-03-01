package com.redafex.xpc;

import com.redafex.xpc.Command.Main;
import com.redafex.xpc.Events.OnJoinGive;
import com.redafex.xpc.Utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Launcher extends JavaPlugin {

    @Override
    public void onEnable() {
        setConfig();
        getCommand("XPC").setExecutor(new Main());
        getCommand("XPC").setExecutor(new Main());
        Bukkit.getPluginManager().registerEvents(new OnJoinGive(), this);
    }

    public void setConfig(){
        Config.setup();
        Config.get().options().copyDefaults(true);
        Config.save();
    }
}
