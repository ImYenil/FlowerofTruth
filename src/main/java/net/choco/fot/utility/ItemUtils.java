package net.choco.fot.utility;

import net.choco.fot.nbt.NBTapi;
import net.choco.fot.utility.compatbridge.model.CompMaterial;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

    public static ItemStack getFot(Player p)
    {
        ItemStack item = new ItemStack(CompMaterial.POPPY.getMaterial());
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatUtils.color("&6Flower of Truth"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        item.setItemMeta(meta);
        item = NBTapi.addNBTTag("fot", "custom-nbt", item);
        p.getInventory().addItem(item);
        return item;
    }

    public static boolean isFot(ItemStack item) {
        return NBTapi.contains("fot", item);
    }
}

