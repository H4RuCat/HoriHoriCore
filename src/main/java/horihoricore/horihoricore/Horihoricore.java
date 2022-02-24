package horihoricore.horihoricore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public final class Horihoricore extends JavaPlugin implements Listener {

    public static String Prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + "HoriCore" + ChatColor.DARK_GRAY + "] " + ChatColor.WHITE ;
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

        String pName = e.getPlayer().getName();

        if (!map.containsKey(e.getPlayer()))
            map.put(e.getPlayer(), 0);
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "こんにちは！" + ChatColor.AQUA + pName + ChatColor.YELLOW + "さん！貴方の現在の採掘量は" + ChatColor.RED + map.get(e.getPlayer()) + ChatColor.YELLOW + "です！");
    }


    @Override
    public void onDisable() {
        getLogger().info("HoriHoriCoreぱーぼーを終了します...");
    }
}