package fr.namu.hub.menu;

import fr.namu.hub.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;

public class NavigateMenu {

    private MenuHUB menu;

    public NavigateMenu(MenuHUB menu) {
        this.menu = menu;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, "§7Menu principal");

        inv.setItem(0, new ItemUtil(Material.BLAZE_ROD, 1)
                .setName("§6Gadgets / Particules")
                .setLore(new String[]{"§7Achetable dans la boutique", "§cIndisponible !"}).toItemStack());

        inv.setItem(4, new ItemUtil(Material.GOLD_INGOT, 1)
                .setName("§l§eBoutique")
                .setLore(new String[]{"§7Clique pour ouvrir la boutique", "§cIndisponible !"}).toItemStack());

        inv.setItem(8, new ItemUtil(Material.SNOW_BALL, 1)
                .setName("§6Amis")
                .setLore(new String[]{"§7Clique ici pour voir ton menu d'amis", "§cIndisponible !"}).toItemStack());

        inv.setItem(22, new ItemUtil(Material.GOLDEN_APPLE, 1)
                .setName("§6Serveur UHC/HOST")
                .setLore(new String[]{"§7Clique ici pour rejoindre le serveur !"}).toItemStack());


        int[] panels = {9,10,11,12,13,14,15,16,17};
        for(Integer slot : panels) {
            inv.setItem(slot, ItemUtil.glassPane(DyeColor.WHITE));
        }

        player.openInventory(inv);
    }

    public void click(Player player, String itemName, ClickType click) {

        switch (itemName) {
            case "§6Serveur UHC/HOST":
                this.menu.main.pm.connect(player, "uhc");
                break;
        }
    }
}
