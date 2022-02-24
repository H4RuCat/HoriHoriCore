package horihoricore.horihoricore;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class Horihoricore extends JavaPlugin implements Listener {

    public static String Prefix = "[HoriCore] ";
    private Listeners listeners;

    public HashMap<Player, Integer> map = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("掘り掘りCoreの起動を開始します...");
        this.listeners = new Listeners(map);
        Bukkit.getPluginManager().registerEvents(this.listeners, this);
        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        map.put(e.getPlayer(), 0);
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent e) {
        int value = map.get(e.getPlayer());
        map.replace(e.getPlayer(), value + 1);
    }


    @Override
    public void onDisable() {
        getLogger().info("掘り掘りぱーぼーを終了します");
    }
}