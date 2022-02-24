package horihoricore.horihoricore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static horihoricore.horihoricore.Horihoricore.Prefix;

public class Listeners implements Listener {
    private final HashMap<Player, Integer> map;

    public Listeners(HashMap<Player, Integer> map) {
        this.map = map;
    }

    @EventHandler // 丸石を掘ったら～...っていうやつ //
    public void onBreakCobbleStone(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.COBBLESTONE)
            if (e.getPlayer().getWorld().getName().equals("HoriHori")) {
                int value = map.get(e.getPlayer());
                map.replace(e.getPlayer(), value + 1);
                e.getPlayer().sendActionBar(ChatColor.YELLOW + "現在の採掘量" + map.get(e.getPlayer()));
            }
        if (map.containsKey(e.getPlayer()) == map.containsValue(25)) // 採掘量が25になったらそのプレイヤーに25が超えたことを知らせる + ピッケルの進化 //
        {
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "25" + ChatColor.YELLOW + "を超えました！");
            Inventory inv = e.getPlayer().getInventory(); // ここからアイテム //
            inv.addItem(new ItemStack(Material.STONE_PICKAXE,1));
        }
        if (map.containsKey(e.getPlayer()) == map.containsValue(100)) // 採掘量が100になったらそのプレイヤーに100が超えたことを知らせる + ピッケルの進化 + 新要素←予定 //
        {
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "100" + ChatColor.YELLOW + "を超えました！");
            Inventory inv = e.getPlayer().getInventory(); // ここからアイテム //
            inv.addItem(new ItemStack(Material.IRON_PICKAXE,1));
        }
    }
    @EventHandler // 初回ログイン特典の配布 //
    public void onJoin(PlayerJoinEvent e) {

        String pName = e.getPlayer().getName();

        if (e.getPlayer().getWorld().getName().equals("HoriHori")) {
            if (e.getPlayer().hasPlayedBefore()); // ここで初参加か検知する //
                else
                e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW.BOLD + "初回ログイン特典を配布しました！");
            Inventory inv = e.getPlayer().getInventory(); // ここからアイテム //
            if (inv.contains(Material.WOODEN_PICKAXE));
            else inv.addItem(new ItemStack(Material.WOODEN_PICKAXE,1));
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "こんにちは！" + ChatColor.AQUA + pName + ChatColor.YELLOW + "さん！貴方の現在の採掘量は" + ChatColor.RED + map.get(e.getPlayer()) + ChatColor.YELLOW + "です！");
        }
    }
    // Event追加するならここから～ //
}
