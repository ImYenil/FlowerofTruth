package net.choco.fot.listener;

import net.choco.fot.Main;
import net.choco.fot.utility.ItemUtils;
import net.choco.fot.utility.compatbridge.MinecraftVersion;
import net.choco.fot.utility.compatbridge.model.CompMaterial;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class PlayerListener implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getItem() == null) {
            return;
        }
        if (!ItemUtils.isFot(e.getItem()))
            return;

        if (MinecraftVersion.atLeast(MinecraftVersion.V.v1_13)) {
            if (e.getHand() != EquipmentSlot.HAND)
                return;
        }

        if (e.getAction() == Action.LEFT_CLICK_AIR) {
            return;
        }

            ArmorStand ar = (ArmorStand) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARMOR_STAND);
            ar.setVisible(false);
            ar.setGravity(false);
            ar.setArms(true);
            ItemStack poppy = new ItemStack(CompMaterial.POPPY.getMaterial());
            ar.setItemInHand(poppy);


            Vector copy = player.getLocation().getDirection();
            ar.getLocation().setDirection(copy);
            new BukkitRunnable() {
                @Override
                public void run() {

                    if (ar.getLocation().getBlock().getType().equals(Material.AIR)) {
                        ar.teleport(ar.getLocation().add(ar.getLocation().getDirection().multiply(1)));
                    } else {
                        World world = ar.getWorld();
                        float power = 2.0f;
                        world.createExplosion(ar.getLocation().getX(), ar.getLocation().getY(), ar.getLocation().getZ(), power, false, false);
                        this.cancel();
                        ar.remove();
                    }
                }
            }.runTaskTimer(Main.getInstance(), 10, 1);
        }
    }
