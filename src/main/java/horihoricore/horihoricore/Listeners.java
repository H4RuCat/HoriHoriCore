package horihoricore.horihoricore;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import static horihoricore.horihoricore.Horihoricore.Prefix;
import static horihoricore.horihoricore.Horihoricore.breakM1;

public class Listeners implements Listener {
    public Listeners(HashMap<UUID, Integer> map) {
        this.map = map;
    }

    @EventHandler
    public void onBreakCobbleStone(BlockBreakEvent e) {
        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) return;
            if (e.getBlock().getType() == Material.COBBLESTONE) return;

                Inventory inv = e.getPlayer().getInventory();

                map.containsKey(e.getPlayer().getUniqueId());
                int value = map.getOrDefault(e.getPlayer().getUniqueId(), 0);
                map.put(e.getPlayer().getUniqueId(), value + 1);
                e.getPlayer().sendActionBar(ChatColor.YELLOW + "現在の採掘量" + ChatColor.AQUA + " : " + ChatColor.YELLOW + value);

        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 26) {
                e.getPlayer().sendMessage(Prefix + breakM1 + "25" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "石のピッケル" + ChatColor.YELLOW + "を配布しました。");

                inv.addItem(new ItemStack(Material.STONE_PICKAXE, 1));
            }

        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 101) {
                e.getPlayer().sendMessage(Prefix + breakM1 +  "100" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "鉄のピッケル" + ChatColor.YELLOW + "を配布しました。");

                inv.addItem(new ItemStack(Material.IRON_PICKAXE, 1));
            }

        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 251) {
                e.getPlayer().sendMessage(Prefix + breakM1 +  "250" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "ダイヤのピッケル" + ChatColor.YELLOW + "を配布しました。");

                inv.addItem(new ItemStack(Material.DIAMOND_PICKAXE, 1));
            }

        if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 501) {
                e.getPlayer().sendMessage(Prefix + breakM1 +  "500" + ChatColor.YELLOW + "を超えた為" + ChatColor.AQUA + "ネザライトのピッケル" + ChatColor.YELLOW + "を配布しました。");

                inv.addItem(new ItemStack(Material.NETHERITE_PICKAXE, 1));
            }
    }

    private static HashMap<UUID, Integer> map = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerChangedWorldEvent e) {

        if (!e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) return;

        Inventory inv = e.getPlayer().getInventory();

            if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == 0) return;
                else if (map.getOrDefault(e.getPlayer().getUniqueId(), 0) == null) {

                    inv.clear();
            }
            if (!e.getPlayer().hasPlayedBefore())
                e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + ChatColor.BOLD + "初回ログイン特典を配布しました！");

            if (!inv.contains(Material.WOODEN_PICKAXE))
                inv.addItem(new ItemStack(Material.WOODEN_PICKAXE, 1));

            if (!map.containsKey(e.getPlayer().getUniqueId())) map.put(e.getPlayer().getUniqueId(), 0);

            e.getPlayer().sendMessage(Prefix + ChatColor.YELLOW + "こんにちは！" + ChatColor.AQUA + e.getPlayer().getName() + ChatColor.YELLOW + "さん！貴方の現在の採掘量は" + ChatColor.RED + map.get(e.getPlayer().getUniqueId()) + ChatColor.YELLOW + "です！");
        }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {

        if (e.getPlayer().getWorld().getName().equalsIgnoreCase("HoriHori")) {

        Player p = e.getPlayer();

        Location worldLoc = e.getBlock().getLocation();
        Location loc1 = new Location(worldLoc.getWorld(), -65, 19, 80);
        Location loc2 = new Location(worldLoc.getWorld(), -79, 12, 94);
        Location loc = new Location(worldLoc.getWorld(), -62, 20, 87);

        boolean allAir = isAllAir(loc1, loc2, e.getBlock());

        if (allAir) {
            setCobblestone(loc1, loc2);

            p.teleport(loc);
            p.sendRawMessage("全ての丸石が採掘された為、丸石を復活させました。");
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