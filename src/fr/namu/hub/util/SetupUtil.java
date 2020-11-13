package fr.namu.hub.util;

import fr.namu.hub.MainHUB;
import fr.namu.hub.bungee.PluginMessage;
import fr.namu.hub.enums.Rank;
import fr.namu.hub.runnable.LobbyRunnable;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;


public class SetupUtil {

    private MainHUB main;

    public SetupUtil(MainHUB main) {
        this.main = main;
    }

    public void init() {
        setTeamRank();
        setRunnable();
        setLocations();
        setPluginChannel();
    }

    private void setTeamRank() {
        Scoreboard sb = Bukkit.getScoreboardManager().getMainScoreboard();

        for(Team team : sb.getTeams())
            team.unregister();

        for(Rank rank : Rank.values()) {
            sb.registerNewTeam(rank.getName());
            sb.getTeam(rank.getName()).setPrefix(rank.getPrefix());
        }
    }

    private void setRunnable() {
        LobbyRunnable run = new LobbyRunnable(this.main);
        run.runTaskTimer(this.main, 0L, 20L);
    }

    private void setLocations() {
        this.main.info.setLobby(new Location(Bukkit.getWorld("world"), 149.529, 89, 229.488));
    }

    private void setPluginChannel() {
        this.main.getServer().getMessenger().registerOutgoingPluginChannel(this.main, "BungeeCord");
        this.main.getServer().getMessenger().registerIncomingPluginChannel(this.main, "BungeeCord", this.main.pm);
    }
}
