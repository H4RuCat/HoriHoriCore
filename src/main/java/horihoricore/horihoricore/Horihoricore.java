package horihoricore.horihoricore;

import com.google.common.util.concurrent.AbstractScheduledService;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Horihoricore extends JavaPlugin implements Listener {

    public static String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "BreakCore" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE ;
    private Listeners listeners;
    public HashMap<UUID, Integer> map = new HashMap<>();

    @Override
    public void onEnable() {
        getLogger().info("HoriHoriCoreぱーぼーの起動を開始します...");
        this.listeners = new Listeners(map);
        // イベント登録 //
        Bukkit.getPluginManager().registerEvents(this.listeners, this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {getLogger().info("HoriHoriCoreぱーぼーを終了します...");}

}
