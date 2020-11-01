package fr.namu.hub.util;


import fr.namu.hub.MainHUB;
import org.bukkit.Location;

public class InfoUtil {

    private MainHUB main;

    public static String prefix = "§eSelenium §7» ";

    private Location lobby;

    public InfoUtil(MainHUB main) {
        this.main = main;
    }

    public Location getLobby() {
        return lobby;
    }
    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }
}
