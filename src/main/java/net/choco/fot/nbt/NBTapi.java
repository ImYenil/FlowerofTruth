package net.choco.fot.nbt;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class NBTapi {

    private static final String prefix = "mc_mines;";

    private static boolean hasKey(String key, NBTItem nbti) {
        try {
            return nbti.hasKey(key);
        } catch (NullPointerException exception) {
            return false;
        }
    }
    public static String getKeys(ItemStack i) {
        return new NBTItem(i).getKeys() + "";
    }

    public static ItemStack removeTag(String tag, ItemStack i) {
        NBTItem nbti = new NBTItem(i);
        nbti.removeKey(tag);
        return nbti.getItem();
    }

    public static ItemStack addNBTTag(String type, String arguments, ItemStack i) {
        NBTItem nbti = new NBTItem(i);
        nbti.setString(type, arguments);
        return nbti.getItem();
    }

    public static ItemStack addNBTTag(String type, int arguments, ItemStack i) {
        NBTItem nbti = new NBTItem(i);
        nbti.setInteger(type, arguments);
        return nbti.getItem();
    }

    public static boolean contains(String type, ItemStack i) {
        if (i.getType().equals(Material.AIR)) {
            return false;
        }
        NBTItem nbti = new NBTItem(i);
        if (nbti == null || i == null) {
            return false;
        }
        if (!nbti.hasNBTData())
            return false;
        if (nbti.getKeys().size() == 0) {
            return false;
        }
        return nbti.getKeys().contains(type);
    }

    public static String get(String argument, ItemStack i) {
        if (i == null || i.getType().equals(Material.AIR))
            return null;

        NBTItem nbti = new NBTItem(i);
        if (!nbti.hasKey(argument))
            return "";
        return nbti.getString(argument);
    }

    public static int getInt(String argument, ItemStack i) {
        NBTItem nbti = new NBTItem(i);
        if (!nbti.hasKey(argument))
            return 0;
        return nbti.getInteger(argument);
    }

}