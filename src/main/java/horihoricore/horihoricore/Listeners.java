package horihoricore.horihoricore;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import static horihoricore.horihoricore.Horihoricore.Prefix;

public class Listeners implements Listener {
    public Listeners(HashMap<UUID, Integer> map) {
        this.map = map;
    }

    @EventHandler
    public void onBreakCobbleStone(BlockBreakEvent e) {
        if (e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori"))
            if (e.getBlock().getType() == Material.COBBLESTONE) {
                map.containsKey(e.getPlayer().getUniqueId());
                int value = map.getOrDefault(e.getPlayer().getUniqueId(), 0);
                map.put(e.getPlayer().getUniqueId(), value + 1);
                e.getPlayer().sendActionBar(ChatColor.YELLOW + "現在の採掘量" + ChatColor.AQUA + " : " + ChatColor.YELLOW + value);
            }
        // 採掘量が25になったらそのプレイヤーに25が超えたことを知らせる + ピッケルの進化 //
        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 26)
            if (e.getBlock().getType() == Material.COBBLESTONE) {
                e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "25" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "石のピッケル" + ChatColor.YELLOW + "を配布しました。");
                // ここからアイテム //D
                Inventory inv = e.getPlayer().getInventory();
                inv.addItem(new ItemStack(Material.STONE_PICKAXE, 1));
                // ここでアイテム終わり //
                // 採掘量25のやつ終わり //
            }
        // 採掘量が100になったらそのプレイヤーに100が超えたことを知らせる + ピッケルの進化 + 新要素←予定 //
        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 101)
            if (e.getBlock().getType() == Material.COBBLESTONE) {
                e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "100" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "鉄のピッケル" + ChatColor.YELLOW + "を配布しました。");
                // ここからアイテム //
                Inventory inv = e.getPlayer().getInventory();
                inv.addItem(new ItemStack(Material.IRON_PICKAXE, 1));
                // ここでアイテム終わり //
                // 採掘量100のやつ終わり //
            }
        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 251)
            if (e.getBlock().getType() == Material.COBBLESTONE) {
                e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "250" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "ダイヤのピッケル" + ChatColor.YELLOW + "を配布しました。");
                // ここからアイテム //
                Inventory inv = e.getPlayer().getInventory();
                inv.addItem(new ItemStack(Material.IRON_PICKAXE, 1));
                // ここでアイテム終わり //
                // 採掘量250のやつ終わり //
            }
        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 501)
            if (e.getBlock().getType() == Material.COBBLESTONE) {
                e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "採掘量が" + ChatColor.RED + "500" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "ネザライトのピッケル" + ChatColor.YELLOW + "を配布しました。");
                // ここからアイテム //
                Inventory inv = e.getPlayer().getInventory();
                inv.addItem(new ItemStack(Material.NETHERITE_PICKAXE, 1));
                // ここでアイテム終わり //
                // 採掘量500のやつ終わり //
            }
    }

    // ログインのやつ //
    private static HashMap<UUID, Integer> map = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerChangedWorldEvent e) {

        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) return;

        if (!e.getPlayer().hasPlayedBefore())
            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + ChatColor.BOLD + "初回ログイン特典を配布しました！");

        Inventory inv = e.getPlayer().getInventory(); // インベントリ取得
        if (!inv.contains(Material.WOODEN_PICKAXE))
            inv.addItem(new ItemStack(Material.WOODEN_PICKAXE, 1)); //木のピッケルがない場合giveする

        if (!map.containsKey(e.getPlayer().getUniqueId())) map.put(e.getPlayer().getUniqueId(), 0);

        e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "こんにちは！" + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.YELLOW + "さん！貴方の現在の採掘量は" + ChatColor.RED + map.get(e.getPlayer().getUniqueId()) + ChatColor.YELLOW + "です！");
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if (e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) {

        Location worldLoc = e.getBlock().getLocation();

        Location loc1 = new Location(worldLoc.getWorld(), -65, 19, 80);
        Location loc2 = new Location(worldLoc.getWorld(), -79, 12, 94);

        boolean allAir = isAllAir(loc1, loc2, e.getBlock());

        if (allAir) {
            setCobblestone(loc1, loc2);
        }
        }
    }

    public boolean isAllAir(Location loc1, Location loc2, Block exempt) {

        int minX = Integer.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Integer.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Integer.min(loc1.getBlockZ(), loc2.getBlockZ());

        int maxX = Integer.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Integer.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Integer.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = loc1.getWorld().getBlockAt(x, y, z);
                    if (block.getLocation().equals(exempt.getLocation())) {
                        continue;
                    }
                    if (block.getType() != Material.AIR) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void setCobblestone(Location loc1, Location loc2) {

        int minX = Integer.min(loc1.getBlockX(), loc2.getBlockX());
        int minY = Integer.min(loc1.getBlockY(), loc2.getBlockY());
        int minZ = Integer.min(loc1.getBlockZ(), loc2.getBlockZ());

        int maxX = Integer.max(loc1.getBlockX(), loc2.getBlockX());
        int maxY = Integer.max(loc1.getBlockY(), loc2.getBlockY());
        int maxZ = Integer.max(loc1.getBlockZ(), loc2.getBlockZ());

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    loc1.getWorld().getBlockAt(x, y, z).setType(Material.COBBLESTONE);
                }
            }
        }
    }
}