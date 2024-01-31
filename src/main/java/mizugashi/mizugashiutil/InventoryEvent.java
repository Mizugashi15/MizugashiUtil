package mizugashi.mizugashiutil;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.Map;

import static mizugashi.mizugashiutil.MizugashiUtil.n;

public class InventoryEvent implements Listener {

    String prefix = "§f§l[§aMizugashi§eUtil§f§l]§r";
    Map<Player, String> map = new HashMap<>();
    Enchantment enchant;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        try {
            if (map.get(event.getPlayer()).equalsIgnoreCase("wait")) {
                if (!isNumber(event.getMessage())) {
                    event.setCancelled(true);
                    event.getPlayer().sendMessage(prefix + " §c数字で入力してください！");
                    map.remove(event.getPlayer());
                    return;
                }
                event.setCancelled(true);
                ItemStack item = event.getPlayer().getInventory().getItemInMainHand();
                if (item.getType() == Material.AIR) {
                    event.getPlayer().sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                    return;
                }
                ItemMeta itemMeta = item.getItemMeta();
                itemMeta.addEnchant(enchant, Integer.parseInt(event.getMessage()), true);
                item.setItemMeta(itemMeta);
                map.remove(event.getPlayer());
                event.getPlayer().playSound(event.getPlayer(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            }
        } catch (NullPointerException ignored) {
        }
    }

    public boolean isNumber(String s) {
        try {
            int i = Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {

        if (event.getView().getTitle().equalsIgnoreCase(prefix + " §0エンチャント")) {

            Player player = (Player) event.getWhoClicked();
            ItemStack item = event.getWhoClicked().getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();

            switch (event.getSlot()) {
                case 0 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.DURABILITY;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 1 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.PROTECTION_FIRE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 2 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.PROTECTION_FALL;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 3 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.PROTECTION_PROJECTILE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 4 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.PROTECTION_EXPLOSIONS;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 5 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.PROTECTION_ENVIRONMENTAL;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 6 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.THORNS;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 7 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.OXYGEN;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 8 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.WATER_WORKER;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 9 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.SWIFT_SNEAK;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 10 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.FROST_WALKER;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 11 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.DEPTH_STRIDER;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 12 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.SOUL_SPEED;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 13 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.DIG_SPEED;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 14 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.LOOT_BONUS_BLOCKS;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 15 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.SILK_TOUCH;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 16 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.MENDING;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 17 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.LURE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 18 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.LUCK;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 19 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.DAMAGE_ALL;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 20 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.SWEEPING_EDGE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 21 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.DAMAGE_UNDEAD;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 22 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.DAMAGE_ARTHROPODS;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 23 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.LOOT_BONUS_MOBS;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 24 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.KNOCKBACK;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 25 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.FIRE_ASPECT;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 26 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.ARROW_DAMAGE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 27 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.ARROW_KNOCKBACK;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 28 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.ARROW_FIRE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 29 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.ARROW_INFINITE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 30 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.MULTISHOT;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 31 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.PIERCING;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 32 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.QUICK_CHARGE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 33 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.IMPALING;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 34 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.CHANNELING;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 35 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.LOYALTY;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 36 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.RIPTIDE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 37 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.BINDING_CURSE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                    break;
                }
                case 38 -> {
                    map.put(player, "wait");
                    enchant = Enchantment.VANISHING_CURSE;
                    if (event.getClick().isRightClick()) {
                        event.setCancelled(true);
                        if (!itemMeta.hasEnchant(enchant)) {
                            player.sendMessage(prefix + " そのアイテムは§f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§cを持っていません");
                            map.remove(player);
                            break;
                        }
                        itemMeta.removeEnchant(enchant);
                        item.setItemMeta(itemMeta);
                        player.sendMessage(prefix + " §f" + event.getCurrentItem().getItemMeta().getDisplayName() + "§aの効果を外しました");
                        map.remove(player);
                        break;
                    }
                    event.setCancelled(true);
                    player.sendMessage(prefix + " §aレベルをチャットに入力してください");
                    player.closeInventory();
                }
                case 53 -> {
                    event.setCancelled(true);
                    player.closeInventory();
                }
            }

        }

        if (event.getView().getTitle().equalsIgnoreCase(prefix + " §aフラグデータ")) {

            ItemStack hasitem = event.getWhoClicked().getInventory().getItemInMainHand();
            ItemMeta hasitemMeta = hasitem.getItemMeta();
            ItemStack falseitem = new ItemStack(Material.REDSTONE_BLOCK);
            ItemStack trueitem = new ItemStack(Material.EMERALD_BLOCK);

            if (event.getSlot() == 0) {
                if (event.getCurrentItem().getType() == (Material.EMERALD_BLOCK)) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                    event.getInventory().setItem(0, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    event.getInventory().setItem(0, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getSlot() == 1) {
                if (event.getCurrentItem().getType() == (Material.EMERALD_BLOCK)) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    event.getInventory().setItem(1, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                    event.getInventory().setItem(1, trueitem);
                    event.setCancelled(true);
                    hasitem.setItemMeta(hasitemMeta);
                    return;
                }
            }

            if (event.getSlot() == 2) {
                if (event.getCurrentItem().getType() == (Material.EMERALD_BLOCK)) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    event.getInventory().setItem(2, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    event.getInventory().setItem(2, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getSlot() == 3) {
                if (event.getCurrentItem().getType() == (Material.EMERALD_BLOCK)) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    event.getInventory().setItem(3, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    event.getInventory().setItem(3, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getSlot() == 4) {
                if (event.getCurrentItem().getType() == (Material.EMERALD_BLOCK)) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_DESTROYS);
                    event.getInventory().setItem(4, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_DESTROYS);
                    event.getInventory().setItem(4, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getSlot() == 5) {
                if (event.getCurrentItem().getType() == (Material.EMERALD_BLOCK)) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_DYE);
                    event.getInventory().setItem(5, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_DYE);
                    event.getInventory().setItem(5, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getSlot() == 6) {
                if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                    hasitemMeta.removeItemFlags(ItemFlag.HIDE_PLACED_ON);
                    event.getInventory().setItem(6, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.addItemFlags(ItemFlag.HIDE_PLACED_ON);
                    event.getInventory().setItem(6, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            if (event.getSlot() == 8) {
                if (event.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                    hasitemMeta.setUnbreakable(false);
                    event.getInventory().setItem(8, falseitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                } else {
                    hasitemMeta.setUnbreakable(true);
                    event.getInventory().setItem(8, trueitem);
                    hasitem.setItemMeta(hasitemMeta);
                    event.setCancelled(true);
                    return;
                }
            }

            event.setCancelled(true);
        }

        if (event.getView().getTitle().equalsIgnoreCase(prefix + " §aカスタムモデルデータ")) {

            if (event.getCurrentItem() == null) {
                return;
            }

            if (event.getWhoClicked().getInventory().getItemInMainHand().getType() == event.getCurrentItem().getType()) {
                event.getWhoClicked().getInventory().addItem(event.getInventory().getItem(event.getSlot()));
                event.setCancelled(true);
            }

            if (event.getSlot() == 51) {
                Inventory inventory = Bukkit.createInventory(null, 54, prefix + " §aカスタムモデルデータ");

                Material material = event.getWhoClicked().getInventory().getItemInMainHand().getType();
                ItemStack cmditem = new ItemStack(material);
                ItemMeta cmdmeta = cmditem.getItemMeta();
                n = n + 45;

                for (int i = 0 ; i <= 44 ; i++) {
                    int cmd = i + n;
                    cmdmeta.setCustomModelData(cmd);
                    cmdmeta.setDisplayName("§a§l" + cmd);
                    cmditem.setItemMeta(cmdmeta);
                    inventory.setItem(i,cmditem);
                }

                ItemStack itemStack = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
                inventory.setItem(45, itemStack);
                inventory.setItem(46, itemStack);
                inventory.setItem(48, itemStack);
                inventory.setItem(49, itemStack);
                inventory.setItem(50, itemStack);
                inventory.setItem(52, itemStack);
                inventory.setItem(53, itemStack);
                ItemStack back = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
                ItemMeta backMeta = back.getItemMeta();
                backMeta.setCustomModelData(15);
                backMeta.setDisplayName("§a前へ");
                back.setItemMeta(backMeta);
                ItemStack next = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
                ItemMeta nextMeta = next.getItemMeta();
                nextMeta.setCustomModelData(14);
                nextMeta.setDisplayName("§a次へ");
                next.setItemMeta(nextMeta);
                inventory.setItem(47, back);
                inventory.setItem(51, next);

                event.getWhoClicked().openInventory(inventory);
                Player player = (Player) event.getWhoClicked();
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                event.setCancelled(true);
            }

            if (event.getSlot() == 47) {
                if (n == 0) {
                    event.setCancelled(true);
                    return;
                }

                Inventory inventory = Bukkit.createInventory(null, 54, prefix + " §aカスタムモデルデータ");

                Material material = event.getWhoClicked().getInventory().getItemInMainHand().getType();
                ItemStack cmditem = new ItemStack(material);
                ItemMeta cmdmeta = cmditem.getItemMeta();
                n = n - 45;

                for (int i = 0 ; i <= 44 ; i++) {
                    int cmd = i + n;
                    cmdmeta.setCustomModelData(cmd);
                    cmdmeta.setDisplayName("§a§l" + cmd);
                    cmditem.setItemMeta(cmdmeta);
                    inventory.setItem(i,cmditem);
                }

                ItemStack itemStack = new ItemStack(Material.PINK_STAINED_GLASS_PANE);
                inventory.setItem(45, itemStack);
                inventory.setItem(46, itemStack);
                inventory.setItem(48, itemStack);
                inventory.setItem(49, itemStack);
                inventory.setItem(50, itemStack);
                inventory.setItem(52, itemStack);
                inventory.setItem(53, itemStack);
                ItemStack back = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
                ItemMeta backMeta = back.getItemMeta();
                backMeta.setCustomModelData(15);
                backMeta.setDisplayName("§a前へ");
                back.setItemMeta(backMeta);
                ItemStack next = new ItemStack(Material.GOLDEN_HORSE_ARMOR);
                ItemMeta nextMeta = next.getItemMeta();
                nextMeta.setCustomModelData(14);
                nextMeta.setDisplayName("§a次へ");
                next.setItemMeta(nextMeta);
                inventory.setItem(47, back);
                inventory.setItem(51, next);
                event.getWhoClicked().openInventory(inventory);
                Player player = (Player) event.getWhoClicked();
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                event.setCancelled(true);
            }

            event.setCancelled(true);
        }

    }

}
