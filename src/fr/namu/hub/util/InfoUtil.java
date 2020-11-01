package fr.namu.hub.util;


import fr.namu.hub.MainHUB;
import org.bukkit.Location;

public class InfoUtil {

    private MainHUB main;

    public static String prefix = "§e§lSelenium §7» ";

    private Location lobby;

    private Boolean muteChat = Boolean.valueOf(false);

    public InfoUtil(MainHUB main) {
        this.main = main;
    }

    public Location getLobby() {
        return lobby;
    }
    public void setLobby(Location lobby) {
        this.lobby = lobby;
    }

    public Boolean isMuteChat() {
        return muteChat;
    }
    public void setMuteChat(Boolean muteChat) {
        this.muteChat = muteChat;
    }
    public void switchMuteChat() {this.muteChat = !muteChat;}
}
