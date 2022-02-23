package horihoricore.horihoricore;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

import static horihoricore.horihoricore.Horihoricore.Prefix;

public class Listeners implements Listener {
    private final HashMap<Player, Integer> map;

    public Listeners(HashMap<Player, Integer> map) {
        this.map = map;
    }

    @EventHandler
    public void onBreakCobbleStone(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.COBBLESTONE)
            if (e.getPlayer().getWorld().getName().equals("Horihori")) {
                int value = map.get(e.getPlayer());
                map.replace(e.getPlayer(), value + 1);
                e.getPlayer().sendActionBar(ChatColor.YELLOW + "現在の採掘量" + map.get(e.getPlayer()));
            }
        if (e.getBlock().getType() == Material.STONE)
            if (e.getPlayer().getWorld().getName().equals("Horihori")) {
                int value = map.get(e.getPlayer());
                map.replace(e.getPlayer(), value + 2);
                e.getPlayer().sendActionBar(ChatColor.YELLOW + "現在の採掘量" + map.get(e.getPlayer()));
            }
        if (map.containsKey(e.getPlayer()) == map.containsValue(100))
        {
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "100" + ChatColor.YELLOW + "を超えました！");
        }


    }
    @EventHandler
    public boolean onJoin(PlayerJoinEvent e) {
        if (e.getPlayer().getWorld().getName().equals("Horihori")){
            if (e.getPlayer().hasPlayedBefore())
                e.getPlayer().sendMessage(ChatColor.MAGIC.AQUA + "ll" + ChatColor.YELLOW.BOLD + "初回ログイン得点を配布しました！" + ChatColor.MAGIC.AQUA + "ll");
        }
        return false;
    }
}
