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

        inv.setItem(15, new ItemUtil(Material.BLAZE_ROD, 1)
                .setName("§6Gadgets / Particules")
                .setLore(new String[]{"§7Achetable dans la boutique", "§cIndisponible !"}).toItemStack());

        inv.setItem(11, new ItemUtil(Material.GOLD_INGOT, 1)
                .setName("§l§eBoutique")
                .setLore(new String[]{"§7Clique pour ouvrir la boutique", "§cIndisponible !"}).toItemStack());

        inv.setItem(13, new ItemUtil(Material.SNOW_BALL, 1)
                .setName("§6Amis")
                .setLore(new String[]{"§7Clique ici pour voir ton menu d'amis", "§cIndisponible !"}).toItemStack());

        inv.setItem(49, new ItemUtil(Material.REDSTONE, 1)
                .setName("§cFermer le menu.")
                .toItemStack());




        inv.setItem(28, new ItemUtil(Material.GOLDEN_APPLE, 1)
                .setName("§6Serveur UHC/HOST")
                .setLore(new String[]{"§7Clique ici pour rejoindre le serveur !"}).toItemStack());





        int[] panels = {0,1,7,8,9,17,36,45,46,44,52,53};
        for(Integer slot : panels) {
            inv.setItem(slot, ItemUtil.glassPane(DyeColor.PURPLE));
        }

        player.openInventory(inv);
    }

    public void click(Player player, String itemName, ClickType click) {

        switch (itemName) {
            case "§6Serveur UHC/HOST":
                this.menu.main.pm.connect(player, "uhc");
                break;
            case "§cFermer le menu.":
                player.closeInventory();
                break;
            case "§6Gadgets / Particules":
                this.menu.particle.open(player);
                break;
        }
    }
}
