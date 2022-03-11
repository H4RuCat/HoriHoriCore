package horihoricore.horihoricore;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import static horihoricore.horihoricore.Horihoricore.Prefix;

public class Listeners implements Listener {
    public Listeners(HashMap<Player, Integer> map) {
        this.map = map;
    }

    @EventHandler // 丸石を掘ったら～...っていうやつ //
    public void onBreakCobbleStone(BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.COBBLESTONE)
            if (e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) {
                int value = map.get(e.getPlayer());
                map.replace(e.getPlayer(), value + 1);
                e.getPlayer().sendActionBar(ChatColor.YELLOW + "現在の採掘量" + ChatColor.AQUA + " : " + ChatColor.YELLOW + map.get(e.getPlayer()));
            }
        // 採掘量が25になったらそのプレイヤーに25が超えたことを知らせる + ピッケルの進化 //
        if (map.containsKey(e.getPlayer()) == map.containsValue(25))
            if (e.getBlock().getType() == Material.COBBLESTONE)
        {
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "25" + ChatColor.YELLOW + "を超えました！");
            // ここからアイテム //
            Inventory inv = e.getPlayer().getInventory();
            inv.addItem(new ItemStack(Material.STONE_PICKAXE,1));
            // ここでアイテム終わり //
            // 採掘量25のやつ終わり //
        }
        // 採掘量が100になったらそのプレイヤーに100が超えたことを知らせる + ピッケルの進化 + 新要素←予定 //
        if (map.containsKey(e.getPlayer()) == map.containsValue(100))
            if (e.getBlock().getType() == Material.COBBLESTONE)
        {
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "100" + ChatColor.YELLOW + "を超えました！");
            // ここからアイテム //
            Inventory inv = e.getPlayer().getInventory();
            inv.addItem(new ItemStack(Material.IRON_PICKAXE,1));
            // ここでアイテム終わり //
            // 採掘量100のやつ終わり //
        }
    }
    // ログインのやつ //
    private static HashMap<UUID, Integer> map = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerChangedWorldEvent e) {

        //String pName = e.getPlayer().getName(); 一度しか使わないならいらん気がする

        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) return;

        if (!e.getPlayer().hasPlayedBefore()) e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW.BOLD + "初回ログイン特典を配布しました！");

        Inventory inv = e.getPlayer().getInventory(); // インベントリ取得
        if (!inv.contains(Material.WOODEN_PICKAXE)) inv.addItem(new ItemStack(Material.WOODEN_PICKAXE, 1)); //木のピッケルがない場合giveする

        if (!map.containsKey(e.getPlayer().getUniqueId())) map.put(e.getPlayer().getUniqueId(), 0);

        e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "こんにちは！" + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.YELLOW + "さん！貴方の現在の採掘量は" + ChatColor.RED + map.get(e.getPlayer()) + ChatColor.YELLOW + "です！");
    }
    // Event追加するならここから //
    }

