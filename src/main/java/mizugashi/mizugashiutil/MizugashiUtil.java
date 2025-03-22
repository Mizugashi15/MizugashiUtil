package mizugashi.mizugashiutil;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.Repairable;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.List;

public final class MizugashiUtil extends JavaPlugin implements Listener {

    String Perm = "minezero.admin";
    static String prefix = "§f§l[§aMizugashi§eUtil§f§l]§r";
    String atmpre = "§e§l[§b§lMZN§e§lATM] ";
    static int n = 0;
    VaultManager vault;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getServer().getPluginManager().registerEvents(new InventoryEvent(), this);
        vault = new VaultManager(this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("mz")) {

            if (!hasPerm((Player) sender)) return false;

            ItemStack item = player.getInventory().getItemInMainHand();
            ItemMeta itemMeta = item.getItemMeta();
            if (args.length == 0) {
                sender.sendMessage("§7========"+prefix+"§7========");
                sender.sendMessage("§f§l/mz §e§lhelp [数字]");
                sender.sendMessage(" §r§7➡ 1 [アイテムモデル]");
                sender.sendMessage(" §r§7➡ 2 [アイテムメタ]");
                sender.sendMessage(" §r§7➡ 3 [アイテムモディファイア]");
                sender.sendMessage(" §r§7➡ 4 [サーバー管理]");
                sender.sendMessage("§f§l/mz §e§lcolor §r§7➡ 装飾コードを表示します");
                sender.sendMessage("§7============================");
                player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                return true;
            }
            if (args.length == 1) {

                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage("§7========"+prefix+"§7========");
                    sender.sendMessage("§f§l/mz §e§lhelp [数字]");
                    sender.sendMessage(" §r§7➡ 1 [アイテムモデル]");
                    sender.sendMessage(" §r§7➡ 2 [アイテムメタ]");
                    sender.sendMessage(" §r§7➡ 3 [アイテムモディファイア]");
                    sender.sendMessage(" §r§7➡ 4 [サーバー管理]");
                    sender.sendMessage("§f§l/mz §e§lcolor §r§7➡ 装飾コードを表示します");
                    sender.sendMessage("§7============================");
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("servertp")) {

                    for (Player player1 : player.getServer().getOnlinePlayers()) {
                        if (player1 == player) continue;
                        player1.teleport(player.getLocation());
                        player1.sendMessage(prefix + " §aテレポートしました");
                    }
                    return true;
                }

                if (args[0].equalsIgnoreCase("hover")) {

                    TextComponent message = new TextComponent("ここをクリック");
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("click here")));
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/me ano"));
                    player.spigot().sendMessage(message);

                    return true;
                }

                if (args[0].equalsIgnoreCase("gamerule")) {

                    World world = player.getWorld();

                    world.setGameRule(GameRule.COMMAND_BLOCK_OUTPUT, false);
                    world.setGameRule(GameRule.DISABLE_ELYTRA_MOVEMENT_CHECK, true);
                    world.setGameRule(GameRule.DISABLE_RAIDS, true);
                    world.setGameRule(GameRule.DO_FIRE_TICK, false);
                    world.setGameRule(GameRule.DO_INSOMNIA, false);
                    world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
                    world.setGameRule(GameRule.DO_PATROL_SPAWNING, false);
                    world.setGameRule(GameRule.DO_TRADER_SPAWNING, false);
                    world.setGameRule(GameRule.KEEP_INVENTORY, true);
                    world.setGameRule(GameRule.MOB_GRIEFING, false);
                    world.setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);

                    sender.sendMessage(prefix + " §a§lコマンドログを管理者に表示しないようにしました");
                    sender.sendMessage(prefix + " §a§lエリトラによる速度チェックを無効化しました");
                    sender.sendMessage(prefix + " §a§l襲撃を無効化しました");
                    sender.sendMessage(prefix + " §a§l火か燃え移らないようにしました");
                    sender.sendMessage(prefix + " §a§lファントムがスポーンしないようにしました");
                    sender.sendMessage(prefix + " §a§lMOBがスポーンしないようにしました");
                    sender.sendMessage(prefix + " §a§l略奪者がスポーンしないようにしました");
                    sender.sendMessage(prefix + " §a§l行商人がスポーンしないようにしました");
                    sender.sendMessage(prefix + " §a§l死亡時インベントリ内アイテムがドロップしないようにしました");
                    sender.sendMessage(prefix + " §a§lMOBがブロックを破壊できないようにしました");
                    sender.sendMessage(prefix + " §a§lコマンドログを表示しないようにしました");

                    return true;

                }

                if (args[0].equals("color")) {

                    sender.sendMessage(prefix + " §11 §22 §33 §44 §55 §66 §77 §88 §99 §00 §aa §bb §cc §dd §ee §ff §kk§r §ll§r §mm§r §nn§r §oo§r");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;

                }
                if (args[0].equalsIgnoreCase("name")) {
                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }
                    player.sendMessage(prefix + " " + itemMeta.getDisplayName().replace("&", "*").replace("§", "&"));
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("setflag")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }

                    Inventory inventory = Bukkit.createInventory(null, 18, prefix + " §aフラグデータ");

                    ItemStack i1 = new ItemStack(Material.PAPER);
                    ItemMeta i1Meta = i1.getItemMeta();
                    i1Meta.setDisplayName("§lエンチャントを隠す");
                    i1.setItemMeta(i1Meta);

                    ItemStack i2 = new ItemStack(Material.PAPER);
                    ItemMeta i2Meta = i2.getItemMeta();
                    i2Meta.setDisplayName("§l不可壊を隠す");
                    i2.setItemMeta(i2Meta);

                    ItemStack i3 = new ItemStack(Material.PAPER);
                    ItemMeta i3Meta = i3.getItemMeta();
                    i3Meta.setDisplayName("§l身に着けたときの効果の詳細を隠す");
                    i3.setItemMeta(i3Meta);

                    ItemStack i4 = new ItemStack(Material.PAPER);
                    ItemMeta i4Meta = i4.getItemMeta();
                    i4Meta.setDisplayName("§lポーション効果の詳細を隠す");
                    i4.setItemMeta(i4Meta);

                    ItemStack i5 = new ItemStack(Material.PAPER);
                    ItemMeta i5Meta = i5.getItemMeta();
                    i5Meta.setDisplayName("§l何を破壊できるかを隠す");
                    i5.setItemMeta(i5Meta);

                    ItemStack i6 = new ItemStack(Material.PAPER);
                    ItemMeta i6Meta = i6.getItemMeta();
                    i6Meta.setDisplayName("§l染料の詳細を隠す");
                    i6.setItemMeta(i6Meta);

                    ItemStack i7 = new ItemStack(Material.PAPER);
                    ItemMeta i7Meta = i7.getItemMeta();
                    i7Meta.setDisplayName("§l何に設置できるかを隠す");
                    i7.setItemMeta(i7Meta);

                    ItemStack i8 = new ItemStack(Material.PAPER);
                    ItemMeta i8Meta = i8.getItemMeta();
                    i8Meta.setDisplayName("§l不可壊");
                    i8.setItemMeta(i8Meta);

                    inventory.setItem(9,i1);
                    inventory.setItem(10,i2);
                    inventory.setItem(11,i3);
                    inventory.setItem(12,i4);
                    inventory.setItem(13,i5);
                    inventory.setItem(14,i6);
                    inventory.setItem(15,i7);
                    inventory.setItem(17,i8);

                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_ENCHANT TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(0, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_ENCHANT FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(0, enchant);
                    }
                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_UNBREAKABLE)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_UNBREAKABLE TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(1, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_UNBREAKABLE FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(1, enchant);
                    }
                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_ATTRIBUTES)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_ATTRIBUTES TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(2, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_ATTRIBUTES FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(2, enchant);
                    }
                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_POTION_EFFECTS)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_POTION_EFFECTS TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(3, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_POTION_EFFECTS FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(3, enchant);
                    }
                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_DESTROYS)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_DESTROYS TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(4, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_DESTROYS FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(4, enchant);
                    }
                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_DYE)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_DYE TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(5, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_DYE FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(5, enchant);
                    }
                    if (itemMeta.hasItemFlag(ItemFlag.HIDE_PLACED_ON)) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lHIDE_PLACED_ON TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(6, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lHIDE_PLACED_ON FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(6, enchant);
                    }
                    if (itemMeta.isUnbreakable()) {
                        ItemStack enchant = new ItemStack(Material.EMERALD_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§a§lUNBREAKABLE TRUE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(8, enchant);
                    } else {
                        ItemStack enchant = new ItemStack(Material.REDSTONE_BLOCK);
                        ItemMeta enchantMeta = enchant.getItemMeta();
                        enchantMeta.setDisplayName("§4§lUNBREAKABLE FALSE");
                        enchant.setItemMeta(enchantMeta);
                        inventory.setItem(8, enchant);
                    }
                    player.openInventory(inventory);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("cmdlist")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }

                    n = 0;

                    Inventory inventory = Bukkit.createInventory(null, 54, prefix + " §aカスタムモデルデータ");

                    Material material = item.getType();
                    ItemStack cmditem = new ItemStack(material);
                    ItemMeta cmdmeta = cmditem.getItemMeta();

                    for (int i = 0 ; i <= 44 ; i++) {
                        cmdmeta.setCustomModelData(i);
                        cmdmeta.setDisplayName("§a§l" + i);
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
                    ItemStack back = new ItemStack(Material.ARROW);
                    ItemMeta backMeta = back.getItemMeta();
                    backMeta.setCustomModelData(15);
                    backMeta.setDisplayName("§a前へ");
                    back.setItemMeta(backMeta);
                    ItemStack next = new ItemStack(Material.ARROW);
                    ItemMeta nextMeta = next.getItemMeta();
                    nextMeta.setCustomModelData(14);
                    nextMeta.setDisplayName("§a次へ");
                    next.setItemMeta(nextMeta);
                    inventory.setItem(47, back);
                    inventory.setItem(51, next);

                    player.openInventory(inventory);
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("cmd")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }
                    if (!itemMeta.hasCustomModelData()) {
                        player.sendMessage(prefix + " §cこのアイテムはモデルを持っていません！");
                        return false;
                    }
                    player.sendMessage(prefix + " §f§lアイテムのモデル番号は §e§l" + itemMeta.getCustomModelData() + " §f§lです");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("setench")) {
                    player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                    player.openInventory(createenchInv());
                    return true;
                }

            }
            if (args.length == 2) {

                if (args[0].equalsIgnoreCase("help")) {
                    if (args[1].equalsIgnoreCase("1")) {
                        sender.sendMessage("§7========"+prefix+"§7=§f§l[アイテムモデル]§7=======");
                        sender.sendMessage("§f§l/mz §e§lcmd §r§7➡ 手に持っているアイテムのモデル番号を表示します");
                        sender.sendMessage("§f§l/mz §e§lcmdlist");
                        sender.sendMessage(" §r§7➡ 手に持っているアイテムのカスタムモデルデータを");
                        sender.sendMessage(" §r§7➡ GUIで表示します(そのGUIのアイテムをクリックすれば入手できます)");
                        sender.sendMessage("§f§l/mz §e§lsetdata [数字]");
                        sender.sendMessage(" §r§7➡ 手に持っているアイテムをカスタムモデルデータ[数字]に設定します");
                        sender.sendMessage("§7============================");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("2")) {
                        sender.sendMessage("§7========"+prefix+"§7=§f§l[アイテムメタ]§7=======");
                        sender.sendMessage("§f§l/mz §e§lsetench");
                        sender.sendMessage(" §r§7➡ 右クリックで付与、左クリックでエンチャント消去ができるGUIを開きます");
                        sender.sendMessage("§f§l/mz §e§lsetname [名前]§r§7➡ 手に持っているアイテムの名前を変更します");
                        sender.sendMessage(" §r§7➡ (*は空白になります)");
                        sender.sendMessage("§f§l/mz §e§lsetlore [説明文] §r§7➡ 説明文を変更します");
                        sender.sendMessage(" §r§7➡ (*は空白に、;は改行になります)");
                        sender.sendMessage("§f§l/mz §e§lsetcost [数値]");
                        sender.sendMessage(" §r§7➡ 手に持っているアイテムの経験値コストを設定します");
                        sender.sendMessage("§f§l/mz §e§lname");
                        sender.sendMessage(" §r§7➡ 手に持っているアイテムの名前の装飾コードを確認します");
                        sender.sendMessage("§7============================");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("3")) {
                        sender.sendMessage("§7========"+prefix+"§7=§f§l[アイテムMOD]§7=======");
                        sender.sendMessage("§f§l/mz §e§lsetflag §r§7➡ アイテムのフラグを設定するGUIを開きます");
                        sender.sendMessage("§f§l/mz §e§lsetmod [mod名] [部位] 数値 [演算]");
                        sender.sendMessage(" §r§7➡ 例: /mz setmod speed hand 0.05 add");
                        sender.sendMessage(" §r§7➡ 例: そのアイテムを手に持っている時、移動速度が0.05加算されます");
                        sender.sendMessage("§f§l/mz §e§ldelmod [部位]");
                        sender.sendMessage(" §r§7➡ [部位]についているmodを削除します");
                        sender.sendMessage("§7============================");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                        return true;
                    }
                    if (args[1].equalsIgnoreCase("4")) {
                        sender.sendMessage("§7========"+prefix+"§7=§f§l[サーバー管理]§7=======");
                        sender.sendMessage("§f§l/mz §e§lannounce §r§7➡ prefix無しのメッセージを全体に送ります");
                        sender.sendMessage("§f§l/mz §e§lgamerule");
                        sender.sendMessage(" §r§7➡ 今いるワールドのgameruleをマルチ、荒らし対策用に切り替えます");
                        sender.sendMessage(" §r§7➡ （特定のgameruleコマンドを切り替えるだけ）");
                        sender.sendMessage("§f§l/mz §e§lservertp §r§7➡ サーバーにいるプレイヤーを自分の位置にテレポートします");
                        sender.sendMessage("§f§l/mz §e§lworldtp [ワールド名]");
                        sender.sendMessage(" §r§7➡ [ワールド名]にいるプレイヤーを自分の位置にテレポートします");
                        sender.sendMessage("§7============================");
                        player.playSound(player, Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                        return true;
                    }
                }

                if (args[0].equalsIgnoreCase("announce")) {
                    if (args[1].isEmpty()) {
                        player.sendMessage(prefix + " §c使い方が間違っています");
                        return false;
                    }
                    String s = args[1].replace("&", "§").replace("*", " ");
                    getServer().broadcastMessage(s);
                    return true;
                }

                if (args[0].equalsIgnoreCase("worldtp")) {

                    try {
                        for (Player player1 : Bukkit.getWorld(args[1]).getPlayers()) {
                            if (player1 == player) continue;
                            player1.teleport(player.getLocation());
                            player1.sendMessage(prefix + " §aテレポートしました");
                        }
                    } catch (NullPointerException ignore) {
                        return false;
                    }
                    return true;
                }

                if (args[0].equalsIgnoreCase("setdata")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }

                    itemMeta.setCustomModelData(Integer.valueOf(args[1]));
                    item.setItemMeta(itemMeta);
                    player.sendMessage(prefix + " §lカスタムモデルデータ §r: §e§l" + args[1] + "§r§lに変更しました。");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("delmod")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }
                    if (!itemMeta.hasAttributeModifiers()) {
                        player.sendMessage(prefix + " §c外せるModifireは存在しません！");
                        return false;
                    }
                    EquipmentSlot slot = EquipmentSlot.valueOf(args[1]);
                    itemMeta.removeAttributeModifier(slot);
                    item.setItemMeta(itemMeta);
                    player.sendMessage(prefix + " §e§l" + args[1] + " §r§lについているModifireを外しました");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("setname")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }
                    itemMeta.setDisplayName(args[1].replace("&","§").replace("*", " "));
                    item.setItemMeta(itemMeta);
                    player.sendMessage(prefix + " §f§l名前を §r" + args[1].replace("&","§") + " §r§lに変更しました");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("setlore")) {
                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }
                    List<String> lore = List.of(args[1].replace("*"," ").replace("&","§").split(";"));
                    itemMeta.setLore(lore);
                    item.setItemMeta(itemMeta);
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

                if (args[0].equalsIgnoreCase("setcost")) {

                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってください！");
                        return false;
                    }

                    Repairable repairable = (Repairable) itemMeta;
                    repairable.setRepairCost(Integer.parseInt(args[1]));
                    item.setItemMeta(itemMeta);
                    player.sendMessage(prefix + " " + itemMeta.getDisplayName() + " §f§lのコストを §e§l" + args[1] + " §f§lに設定しました");
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                }

            }
            if (args.length == 5) {
                if (args[0].equalsIgnoreCase("setmod")) {
                    if (item.getType() == Material.AIR) {
                        player.sendMessage(prefix + " §c手に何かアイテムを持ってださい！");
                        return false;
                    }
                    AttributeModifier modifier;
                    String modname;
                    EquipmentSlot slot;
                    AttributeModifier.Operation operation;
                    double num = Double.parseDouble(args[3]);
                    switch (args[1]) {
                        case "armor" -> modname = "GENERIC_ARMOR";
                        case "armortoughness" -> modname = "GENERIC_ARMOR_TOUGHNESS";
                        case "attackdamage" -> modname = "GENERIC_ATTACK_DAMAGE";
                        case "attackknockback" -> modname = "GENERIC_ATTACK_KNOCKBACK";
                        case "attackspeed" -> modname = "GENERIC_ATTACK_SPEED";
                        case "flyspeed" -> modname = "GENERIC_FLYING_SPEED";
                        case "followrange" -> modname = "GENERIC_FOLLOW_RANGE";
                        case "knockbackresistance" -> modname = "GENERIC_KNOCKBACK_RESISTANCE";
                        case "luck" -> modname = "GENERIC_LUCK";
                        case "maxhealth" -> modname = "GENERIC_MAX_HEALTH";
                        case "speed" -> modname = "GENERIC_MOVEMENT_SPEED";
                        case "horsejump" -> modname = "HORSE_JUMP_STRENGTH";
                        case "zombie" -> modname = "ZOMBIE_SPAWN_REINFORCEMENTS";
                        default -> {
                            player.sendMessage(prefix + " §c使い方が間違っています");
                            return false;
                        }
                    }
                    switch (args[2]) {
                        case "head" -> slot = EquipmentSlot.HEAD;
                        case "chest" -> slot = EquipmentSlot.CHEST;
                        case "legs" -> slot = EquipmentSlot.LEGS;
                        case "feet" -> slot = EquipmentSlot.FEET;
                        case "hand" -> slot = EquipmentSlot.HAND;
                        case "offhand" -> slot = EquipmentSlot.OFF_HAND;
                        default -> {
                            player.sendMessage(prefix + " §c使い方が間違っています");
                            return false;
                        }
                    }
                    switch (args[4]) {
                        case "add" -> operation = AttributeModifier.Operation.ADD_NUMBER;
                        case "scalar" -> operation = AttributeModifier.Operation.ADD_SCALAR;
                        case "multiply" -> operation = AttributeModifier.Operation.MULTIPLY_SCALAR_1;
                        default -> {
                            player.sendMessage(prefix + " §c使い方が間違っています");
                            return false;
                        }
                    }
                    modifier = new AttributeModifier(UUID.randomUUID(), modname, num, operation, slot);
                    itemMeta.addAttributeModifier(Attribute.valueOf(modname), modifier);
                    item.setItemMeta(itemMeta);
                    player.playSound(player, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;

                }
            } else {
                sender.sendMessage(prefix + " §c使い方が間違っています！");
                return false;
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args.length==1) {
            return strings(args[0], Arrays.asList("color","cmd","setdata","setench","delmod","setflag","setname","setmod","setlore","help","cmdlist","name","setcost","gamerule","worldtp","servertp","announce"));
        }
        if(args.length==2) {
            if (args[0].equalsIgnoreCase("setmod")) {
                List<String> list = new ArrayList<>();
                list.add("armor");
                list.add("armortoughness");
                list.add("attackdamage");
                list.add("attackknockback");
                list.add("attackspeed");
                list.add("flyspeed");
                list.add("followrange");
                list.add("knockbackresistance");
                list.add("luck");
                list.add("maxhealth");
                list.add("speed");
                list.add("horsejump");
                list.add("zombie");
                return strings(args[1], list);
            }
            if (args[0].equalsIgnoreCase("delmod")) {
                List<String> list = new ArrayList<>();
                list.add("HEAD");
                list.add("CHEST");
                list.add("LEGS");
                list.add("FEET");
                list.add("HAND");
                list.add("OFF_HAND");
                return strings(args[1], list);
            }
            if (args[0].equalsIgnoreCase("worldtp")) {
                List<String> list = new ArrayList<>();
                for (World world : Bukkit.getServer().getWorlds()) {
                    list.add(world.getName());
                }
                return strings(args[1], list);
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("setmod")) {
                List<String> list = new ArrayList<>();
                list.add("head");
                list.add("chest");
                list.add("legs");
                list.add("feet");
                list.add("hand");
                list.add("offhand");
                return strings(args[2], list);
            }
        }
        if (args.length == 5) {
            if (args[0].equalsIgnoreCase("setmod")) {
                List<String> list = new ArrayList<>();
                list.add("add");
                list.add("scalar");
                list.add("multiply");
                return strings(args[4], list);
            }
        }
        return null;
    }

    private List<String> strings(String s,List<String> args){
        List<String> list = new ArrayList<>();
        for(String s1:args){
            if(s1.startsWith(s))
                list.add(s1);
        }
        return list;
    }

    public boolean hasPerm(Player player) {
        if (!player.hasPermission(Perm)) {
            player.sendMessage(prefix + "§cあなたには権限がありません！");
            return false;
        }
        return true;
    }

    public static Inventory createenchInv() {

        Inventory inventory = Bukkit.createInventory(null, 54, prefix + " §0エンチャント");
        ItemStack pro = new ItemStack(Material.OBSIDIAN);
        ItemMeta proMeta = pro.getItemMeta();
        proMeta.setDisplayName("§a耐久力");
        pro.setItemMeta(proMeta);
        inventory.setItem(0, pro);
        ItemStack firepro = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta fireproMeta = firepro.getItemMeta();
        fireproMeta.setDisplayName("§a火炎耐性");
        firepro.setItemMeta(fireproMeta);
        inventory.setItem(1, firepro);
        ItemStack fallpro = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta fallproMeta = fallpro.getItemMeta();
        fallproMeta.setDisplayName("§a落下耐性");
        fallpro.setItemMeta(fallproMeta);
        inventory.setItem(2, fallpro);
        ItemStack propro = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta proproMeta = propro.getItemMeta();
        proproMeta.setDisplayName("§a飛び道具耐性");
        propro.setItemMeta(proproMeta);
        inventory.setItem(3, propro);
        ItemStack bakuha = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta bakuhaMeta = bakuha.getItemMeta();
        bakuhaMeta.setDisplayName("§a爆発耐性");
        bakuha.setItemMeta(bakuhaMeta);
        inventory.setItem(4, bakuha);
        ItemStack protection = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta protectionMeta = protection.getItemMeta();
        protectionMeta.setDisplayName("§aダメージ軽減");
        protection.setItemMeta(protectionMeta);
        inventory.setItem(5, protection);
        ItemStack thorn = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta thornMeta = thorn.getItemMeta();
        thornMeta.setDisplayName("§a棘の鎧");
        thorn.setItemMeta(thornMeta);
        inventory.setItem(6, thorn);
        ItemStack kokyu = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta kokyuMeta = kokyu.getItemMeta();
        kokyuMeta.setDisplayName("§a水中呼吸");
        kokyu.setItemMeta(kokyuMeta);
        inventory.setItem(7, kokyu);
        ItemStack saikutu = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta saikutuMeta = saikutu.getItemMeta();
        saikutuMeta.setDisplayName("§a水中採掘");
        saikutu.setItemMeta(saikutuMeta);
        inventory.setItem(8, saikutu);
        ItemStack sneak = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta sneakMeta = sneak.getItemMeta();
        sneakMeta.setDisplayName("§aスニーク速度上昇");
        sneak.setItemMeta(sneakMeta);
        inventory.setItem(9, sneak);
        ItemStack frost = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta frostMeta = frost.getItemMeta();
        frostMeta.setDisplayName("§a氷渡り");
        frost.setItemMeta(frostMeta);
        inventory.setItem(10, frost);
        ItemStack hokou = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta hokouMeta = hokou.getItemMeta();
        hokouMeta.setDisplayName("§a水中歩行");
        hokou.setItemMeta(hokouMeta);
        inventory.setItem(11, hokou);
        ItemStack soul = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta soulMeta = soul.getItemMeta();
        soulMeta.setDisplayName("§aソウルスピード");
        soul.setItemMeta(soulMeta);
        inventory.setItem(12, soul);
        ItemStack kouritu = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta kourituMeta = kouritu.getItemMeta();
        kourituMeta.setDisplayName("§a効率強化");
        kouritu.setItemMeta(kourituMeta);
        inventory.setItem(13, kouritu);
        ItemStack kouun = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta kouunMeta = kouun.getItemMeta();
        kouunMeta.setDisplayName("§a幸運");
        kouun.setItemMeta(kouunMeta);
        inventory.setItem(14, kouun);
        ItemStack silk = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta silkMeta = silk.getItemMeta();
        silkMeta.setDisplayName("§aシルクタッチ");
        silk.setItemMeta(silkMeta);
        inventory.setItem(15, silk);
        ItemStack syuzen = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemMeta syuzenMeta = syuzen.getItemMeta();
        syuzenMeta.setDisplayName("§a修繕");
        syuzen.setItemMeta(syuzenMeta);
        inventory.setItem(16, syuzen);
        ItemStack ire = new ItemStack(Material.FISHING_ROD);
        ItemMeta ireMeta = ire.getItemMeta();
        ireMeta.setDisplayName("§a入れ食い");
        ire.setItemMeta(ireMeta);
        inventory.setItem(17, ire);
        ItemStack takara = new ItemStack(Material.FISHING_ROD);
        ItemMeta takaraMeta = takara.getItemMeta();
        takaraMeta.setDisplayName("§a宝釣り");
        takara.setItemMeta(takaraMeta);
        inventory.setItem(18, takara);
        ItemStack dame = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta dameMeta = dame.getItemMeta();
        dameMeta.setDisplayName("§aダメージ増加");
        dame.setItemMeta(dameMeta);
        inventory.setItem(19, dame);
        ItemStack hanidame = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta hanidameMeta = hanidame.getItemMeta();
        hanidameMeta.setDisplayName("§a範囲ダメージ増加");
        hanidame.setItemMeta(hanidameMeta);
        inventory.setItem(20, hanidame);
        ItemStack undead = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta undeadMeta = undead.getItemMeta();
        undeadMeta.setDisplayName("§aアンデッド特攻");
        undead.setItemMeta(undeadMeta);
        inventory.setItem(21, undead);
        ItemStack musi = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta musiMeta = musi.getItemMeta();
        musiMeta.setDisplayName("§a虫特攻");
        musi.setItemMeta(musiMeta);
        inventory.setItem(22, musi);
        ItemStack drop = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta dropMeta = drop.getItemMeta();
        dropMeta.setDisplayName("§aドロップ増加");
        drop.setItemMeta(dropMeta);
        inventory.setItem(23, drop);
        ItemStack knock = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta knockMeta = knock.getItemMeta();
        knockMeta.setDisplayName("§aノックバック");
        knock.setItemMeta(knockMeta);
        inventory.setItem(24, knock);
        ItemStack hizokusei = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta hizokuseiMeta = hizokusei.getItemMeta();
        hizokuseiMeta.setDisplayName("§a火属性");
        hizokusei.setItemMeta(hizokuseiMeta);
        inventory.setItem(25, hizokusei);
        ItemStack syageki = new ItemStack(Material.BOW);
        ItemMeta syagekiMeta = syageki.getItemMeta();
        syagekiMeta.setDisplayName("§a射撃ダメージ増加");
        syageki.setItemMeta(syagekiMeta);
        inventory.setItem(26, syageki);
        ItemStack punch = new ItemStack(Material.BOW);
        ItemMeta punchMeta = punch.getItemMeta();
        punchMeta.setDisplayName("§aパンチ");
        punch.setItemMeta(punchMeta);
        inventory.setItem(27, punch);
        ItemStack flame = new ItemStack(Material.BOW);
        ItemMeta flameMeta = flame.getItemMeta();
        flameMeta.setDisplayName("§aフレイム");
        flame.setItemMeta(flameMeta);
        inventory.setItem(28, flame);
        ItemStack mugen = new ItemStack(Material.BOW);
        ItemMeta mugenMeta = mugen.getItemMeta();
        mugenMeta.setDisplayName("§a無限");
        mugen.setItemMeta(mugenMeta);
        inventory.setItem(29, mugen);
        ItemStack kakusan = new ItemStack(Material.CROSSBOW);
        ItemMeta kakusanMeta = kakusan.getItemMeta();
        kakusanMeta.setDisplayName("§a拡散");
        kakusan.setItemMeta(kakusanMeta);
        inventory.setItem(30, kakusan);
        ItemStack kantu = new ItemStack(Material.CROSSBOW);
        ItemMeta kantuMeta = kantu.getItemMeta();
        kantuMeta.setDisplayName("§a貫通");
        kantu.setItemMeta(kantuMeta);
        inventory.setItem(31, kantu);
        ItemStack kousoku = new ItemStack(Material.CROSSBOW);
        ItemMeta kousokuMeta = kousoku.getItemMeta();
        kousokuMeta.setDisplayName("§a高速装填");
        kousoku.setItemMeta(kousokuMeta);
        inventory.setItem(32, kousoku);
        ItemStack suisei = new ItemStack(Material.TRIDENT);
        ItemMeta suiseiMeta = suisei.getItemMeta();
        suiseiMeta.setDisplayName("§a水生特攻");
        suisei.setItemMeta(suiseiMeta);
        inventory.setItem(33, suisei);
        ItemStack syorai = new ItemStack(Material.TRIDENT);
        ItemMeta syoraiMeta = syorai.getItemMeta();
        syoraiMeta.setDisplayName("§a召雷");
        syorai.setItemMeta(syoraiMeta);
        inventory.setItem(34, syorai);
        ItemStack tyusei = new ItemStack(Material.TRIDENT);
        ItemMeta tyuseiMeta = tyusei.getItemMeta();
        tyuseiMeta.setDisplayName("§a忠誠");
        tyusei.setItemMeta(tyuseiMeta);
        inventory.setItem(35, tyusei);
        ItemStack geki = new ItemStack(Material.TRIDENT);
        ItemMeta gekiMeta = geki.getItemMeta();
        gekiMeta.setDisplayName("§a激流");
        geki.setItemMeta(gekiMeta);
        inventory.setItem(36, geki);
        ItemStack sokubaku = new ItemStack(Material.BARRIER);
        ItemMeta sokubakuMeta = sokubaku.getItemMeta();
        sokubakuMeta.setDisplayName("§c束縛の呪い");
        sokubaku.setItemMeta(sokubakuMeta);
        inventory.setItem(37, sokubaku);
        ItemStack syometu = new ItemStack(Material.BARRIER);
        ItemMeta syometuMeta = syometu.getItemMeta();
        syometuMeta.setDisplayName("§c消滅の呪い");
        syometu.setItemMeta(syometuMeta);
        inventory.setItem(38, syometu);

        ItemStack toziru = new ItemStack(Material.REDSTONE_BLOCK);
        ItemMeta toziruMeta = toziru.getItemMeta();
        toziruMeta.setDisplayName("§c閉じる");
        toziru.setItemMeta(toziruMeta);
        inventory.setItem(53, toziru);

        return inventory;
    }

}
