package fr.namu.hub.runnable;

import fr.namu.hub.MainHUB;
import org.bukkit.scheduler.BukkitRunnable;

public class LobbyRunnable extends BukkitRunnable {

    private MainHUB main;

    public LobbyRunnable(MainHUB main) {
        this.main = main;
    }

    @Override
    public void run() {
        this.main.score.updateBoard();
    }
}
