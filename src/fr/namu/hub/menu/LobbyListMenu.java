package fr.namu.hub.menu;

import fr.namu.hub.MainHUB;
import fr.namu.hub.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class LobbyListMenu {

    private MenuHUB menu;
    private MainHUB main;

    public LobbyListMenu(MenuHUB menu) {
        this.menu = menu;
        this.main = menu.main;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 27, "§7Liste des lobbys");

        inv.setItem(10, new ItemUtil(Material.BEACON, 1).setName("§eLobby N°1").setLore("Vous êtes déjà dessus !").toItemStack());

        int[] panels = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};
        for(Integer slot : panels) {
            inv.setItem(slot, ItemUtil.glassPane(DyeColor.BLUE));
        }

        player.openInventory(inv);
    }
}
