package fr.namu.hub.menu;

import fr.namu.hub.PlayerHUB;
import fr.namu.hub.enums.Particles;
import fr.namu.hub.util.InfoUtil;
import fr.namu.hub.util.ItemUtil;
import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ParticleMenu {

    private MenuHUB menu;

    public ParticleMenu(MenuHUB menu) {
        this.menu = menu;
    }

    public void open(Player player) {
        Inventory inv = Bukkit.createInventory(null, 6*9, "§7Liste des Particules");

        int slot = 1;
        int line = 1;

        for(Particles particle : Particles.values()) {
            inv.setItem(slot + line * 9, createObject(player, particle));
            slot = slot + 1;
            if(slot >= 8) {
                line = line + 1;
                slot = 1;
            }
        }

        inv.setItem(49, new ItemUtil(Material.BARRIER,1).setName("§cEnlever votre particule actuelle").toItemStack());

        int[] SlotWhiteGlass = {
                0,1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,44,45,46,47,48,50,51,52,53 };
        for (int slotGlass : SlotWhiteGlass)
            inv.setItem(slotGlass, ItemUtil.glassPane(DyeColor.RED));

        player.openInventory(inv);
    }

    public void click(Player player, String itemName) {
        for(Particles particle : Particles.values()) {
            if(itemName.contains(particle.getName())) {
                if(player.hasPermission(particle.getPerm())) {
                    this.menu.main.particle.startParticles(player, particle);
                    player.closeInventory();
                    return;
                } else {
                    player.playSound(player.getLocation(), Sound.VILLAGER_NO, 1,1);
                    open(player);
                    return;
                }
            }
        }

        if(itemName.equalsIgnoreCase("§cEnlever votre particule actuelle")) {
            PlayerHUB phub = this.menu.main.phub.get(player.getUniqueId());
            phub.setParticle(null);

            player.sendMessage(InfoUtil.prefix + "§eVous n'avez plus de particule active !");
            return;
        }


    }

    private ItemStack createObject(Player player, Particles particle) {
        PlayerHUB phub = this.menu.main.phub.get(player.getUniqueId());
        String name = "§e" + particle.getName();
        Material mat = particle.getMat();
        String[] lore;

        if(player.hasPermission(particle.getPerm())) {
            lore = new String[] {"§7Vous possédez cette particule !", "", "§aCliquez pour l'activer !"};
        } else {
            lore = new String[] {"§7Vous ne possédez pas encore cette particule...", "", "§6Achetez-la maintenant: §e/boutique"};
        }
        if(phub.getParticle() != null && phub.getParticle().equals(particle)) {
            lore = new String[] {"§7Vous possédez cette particule !", "", "§aCette particule est activée !"};
        }

        return new ItemUtil(mat, 1).setName(name).setLore(lore).toItemStack();
    }





}
