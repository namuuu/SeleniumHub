package fr.namu.hub;

import fr.namu.hub.listener.*;
import fr.namu.hub.menu.MenuHUB;
import fr.namu.hub.scoreboard.FastBoard;
import fr.namu.hub.scoreboard.ScoreBoard;
import fr.namu.hub.scoreboard.TabList;
import fr.namu.hub.util.InfoUtil;
import fr.namu.hub.util.LobbyUtil;
import fr.namu.hub.util.SetupUtil;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public class MainHUB extends JavaPlugin {

    public HashMap<UUID, FastBoard> boards = new HashMap<>();
    public HashMap<UUID, PlayerHUB> phub = new HashMap<>();

    public SetupUtil setup = new SetupUtil(this);
    public InfoUtil info = new InfoUtil(this);
    public ScoreBoard score = new ScoreBoard(this);
    public JoinLeaveEvent join = new JoinLeaveEvent(this);
    public LobbyUtil lobby = new LobbyUtil(this);
    public MenuHUB menu = new MenuHUB(this);
    public TabList tab = new TabList(this);

    public LuckPerms luckPerms;


    @EventHandler
    public void onEnable() {
        setLuckPerms();
        setListeners();

        setup.init();

        join.resetPlayers();
    }

    @EventHandler
    public void onDisable() {

    }

    private void setLuckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        }
    }

    private void setListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new JoinLeaveEvent(this), this);
        pm.registerEvents(new DamageEvent(this), this);
        pm.registerEvents(new InteractEvent(this), this);
        pm.registerEvents(new ClickEvent(this), this);
        pm.registerEvents(new ProcessCommandEvent(this), this);
    }
}
