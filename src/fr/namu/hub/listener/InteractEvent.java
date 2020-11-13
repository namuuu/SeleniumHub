package fr.namu.hub.listener;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractEvent implements Listener {

    private MainHUB main;

    public InteractEvent(MainHUB main) {
        this.main = main;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if(event.getPlayer().getGameMode().equals(GameMode.ADVENTURE)) {
            event.setCancelled(true);
        }

        ItemStack current = event.getItem();
        Player player = event.getPlayer();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        if(current == null || !current.hasItemMeta() || !current.getItemMeta().hasDisplayName()) {
            return;
        }

        switch (current.getItemMeta().getDisplayName()) {
            case "§f§lFly":
                phub.switchFly();
                break;
            case "§e§lPlay":
                this.main.menu.navigate.open(player);
                break;
        }
    }

    @EventHandler
    public void onDropItem(PlayerDropItemEvent event) {
        event.setCancelled(true);
    }
}
