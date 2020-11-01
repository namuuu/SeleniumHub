package fr.namu.hub.util;

import fr.namu.hub.MainHUB;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LobbyUtil {

    private MainHUB main;

    public LobbyUtil(MainHUB mainHUB) {
        this.main = mainHUB;
    }

    public void giveLobbyStuff(Player player) {
        Inventory inv = player.getInventory();

        inv.clear();

        inv.setItem(0, new ItemUtil(Material.COMPASS).setName("§e§lPlay").toItemStack());
        inv.setItem(2, new ItemUtil(Material.FEATHER).setName("§f§lFly").toItemStack());
        inv.setItem(6, new ItemUtil(Material.GOLD_INGOT).setName("§e§lBoutique").toItemStack());
        inv.setItem(8, new ItemUtil(Material.BEACON).setName("§b§lChanger de Lobby").toItemStack());
    }
}
