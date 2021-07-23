package net.choco.fot.command;

import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Default;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.base.CommandBase;
import net.choco.fot.utility.ItemUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Command("fot")
public class FotCommand extends CommandBase {

    @Default
    @Permission("fot.give")
    public void defaultCommand(CommandSender sender) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ItemUtils.getFot(player);
        }
    }
}
