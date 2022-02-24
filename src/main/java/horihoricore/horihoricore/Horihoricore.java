package horihoricore.horihoricore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Horihoricore extends JavaPlugin implements Listener {

    public static String Prefix = ChatColor.GRAY + "[" + ChatColor.WHITE + "HoriCore" + ChatColor.GRAY + "]" + ChatColor.WHITE ;
    private Listeners listeners;

    public HashMap<Player, Integer> map = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("HoriHoriCoreぱーぼーの起動を開始します...");
        this.listeners = new Listeners(map);
        Bukkit.getPluginManager().registerEvents(this.listeners, this);
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        map.put(e.getPlayer(), 0);
    }

    @Override
    public void onDisable() {
        getLogger().info("HoriHoriCoreぱーぼーを終了します...");
    }
}