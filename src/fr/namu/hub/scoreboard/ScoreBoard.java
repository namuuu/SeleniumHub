package fr.namu.hub.scoreboard;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ScoreBoard {

    private MainHUB main;

    public ScoreBoard(MainHUB main) {
        this.main = main;
    }

    public void updateBoard() {
        for(FastBoard board : this.main.boards.values()) {
            basicBoard(board);
        }
    }

    private void basicBoard(FastBoard board) {
        Player player = board.getPlayer();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        String[] score = {
                "§7§m----------------------",
                " ",
                "§3Mon profil :",
                "Grade: §b" + phub.getRank().getFullname(),
                "Coins: §b" + 0,
                " ",
                "Lobby: §b" + "LOBBY01",
                "Joueurs: §b" + Bukkit.getOnlinePlayers().size(),
                " ",
                "§7play.selenium-pvp.com",
                "§7§m----------------------"
        };



        for (int i = 0; i < score.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(score[i]);
            if (sb.length() > 30)
                sb.delete(29, sb.length() - 1);
            score[i] = sb.toString();
        }

        board.updateTitle("§7» §eSELENIUM §7«");
        board.updateLines(score);
    }
}
