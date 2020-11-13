package fr.namu.hub;

import fr.namu.hub.enums.Rank;
import fr.namu.hub.util.InfoUtil;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class PlayerHUB {

    private Player player;

    private MainHUB main;

    private Rank rank = null;

    private Boolean canFly = Boolean.valueOf(false);
    private Boolean canBuild = Boolean.valueOf(false);

    public PlayerHUB (MainHUB main, Player player) {
        this.main = main;
        this.player = player;
    }


    public Rank getRank() {
        return rank;
    }
    public void setRank(Rank rank) {
        this.rank = rank;
        Bukkit.getScoreboardManager().getMainScoreboard().getTeam(rank.getName()).addEntry(player.getName());
        player.setPlayerListName(rank.getFullname() + player.getName());
    }

    public Boolean canFly() {
        return this.canFly;
    }
    public void switchFly() {
        if(canFly) {
            player.sendMessage(InfoUtil.prefix + "§fTu as perdu ta capacité de voler...");
            player.setAllowFlight(false);
            canFly = Boolean.valueOf(false);
        } else {
            player.sendMessage(InfoUtil.prefix + "§fTu peux fly désormais !");
            player.setAllowFlight(true);
            canFly = Boolean.valueOf(true);
        }
    }
    public void setFly(Boolean value) {
        player.setAllowFlight(value);
        canFly = Boolean.valueOf(value);
    }

    public Boolean canBuild() {
        return this.canBuild;
    }
    public void switchBuild() {
        if(canBuild) {
            player.sendMessage(InfoUtil.prefix + "§fTu ne peux plus build !");
            player.setGameMode(GameMode.ADVENTURE);
            canBuild = Boolean.valueOf(false);
            this.main.lobby.giveLobbyStuff(player);
        } else {
            player.sendMessage(InfoUtil.prefix + "§fTu peux build désormais !");
            player.setGameMode(GameMode.CREATIVE);
            canBuild = Boolean.valueOf(true);
            player.getInventory().clear();
        }
    }
    public void setBuild(Boolean value) {
        canBuild = Boolean.valueOf(value);
        if(canBuild) {
            player.setGameMode(GameMode.CREATIVE);
            player.getInventory().clear();

        } else {
            player.setGameMode(GameMode.ADVENTURE);
            this.main.lobby.giveLobbyStuff(player);
        }
    }
}
