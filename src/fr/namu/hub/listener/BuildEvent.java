package fr.namu.hub.listener;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class BuildEvent implements Listener {

    private MainHUB main;

    public BuildEvent(MainHUB main) {
        this.main = main;
    }

    @EventHandler
    public void onBuild(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        if(!phub.canBuild()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        if(!phub.canBuild()) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplode(BlockExplodeEvent event) {
        event.setCancelled(true);
    }
}
