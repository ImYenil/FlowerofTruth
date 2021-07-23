package net.choco.fot;

import lombok.Getter;
import me.mattstudios.mf.base.CommandManager;
import net.choco.fot.command.FotCommand;
import net.choco.fot.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Getter
    private static Main instance;

    private CommandManager commandManager;

    public void onEnable() {

        instance = this;

        commandManager = new CommandManager(this);
        commandManager.register(new FotCommand());

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }
}
