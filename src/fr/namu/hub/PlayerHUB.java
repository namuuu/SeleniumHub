package fr.namu.hub;

import fr.namu.hub.enums.Rank;
import fr.namu.hub.util.InfoUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerHUB {

    private Player player;

    private MainHUB main;

    private Rank rank = null;

    private Boolean canFly = Boolean.valueOf(false);

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
}
