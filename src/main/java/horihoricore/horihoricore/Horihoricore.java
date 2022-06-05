package horihoricore.horihoricore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Horihoricore extends JavaPlugin implements Listener {

    public static String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "BreakCore" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE ;
    public static String breakM1 = ChatColor.YELLOW + "採掘量が" + ChatColor.RED ;

    private Listeners listeners;
    public HashMap<UUID, Integer> map = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("HoriHori///を起動しました(`・ω・´)");
        this.listeners = new Listeners(map);
        // イベント登録 //
        Bukkit.getPluginManager().registerEvents(this.listeners, this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {getLogger().info("HoriHori///が...止まっちゃった...うぅ(´;ω;｀)");}

}
