package fr.namu.hub.listener;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import fr.namu.hub.enums.Rank;
import fr.namu.hub.scoreboard.FastBoard;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.model.user.User;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinLeaveEvent implements Listener {

    private MainHUB main;

    public JoinLeaveEvent(MainHUB main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        newPlayer(player);

        event.setJoinMessage(null);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        event.setQuitMessage(null);

        this.main.boards.remove(player.getUniqueId());
        this.main.phub.remove(player.getUniqueId());
    }


    private void newPlayer(Player player) {
        player.teleport(this.main.info.getLobby());

        this.main.boards.put(player.getUniqueId(), new FastBoard(player));
        this.main.phub.put(player.getUniqueId(), new PlayerHUB(this.main, player));

        PlayerHUB phub = this.main.phub.get(player.getUniqueId());
        LuckPerms lp = this.main.luckPerms;
        User user = lp.getUserManager().getUser(player.getUniqueId());

        player.setHealth(player.getMaxHealth());
        player.setFoodLevel(20);

        this.main.lobby.giveLobbyStuff(player);
        phub.setFly(false);

        for(PotionEffect pe : player.getActivePotionEffects())
            player.removePotionEffect(pe.getType());

        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 1));

        Bukkit.getScheduler().runTaskLater(this.main, () -> player.setGameMode(GameMode.ADVENTURE), 3L);

        this.main.tab.setTabList(player,
                new ChatComponentText(
                        " \n" +
                                "§7• §eSELENIUM §7•\n" +
                                " \n" +
                                "§7Vous êtes sur le serveur: §aLOBBY01\n" +
                                " "),

                new ChatComponentText(
                        " \n" +
                                "      §7Faites vous plaisir dans la boutique: §e/boutique     \n" +
                                " \n"
                ));

        for(Rank rank : Rank.values()) {
            if(rank.getName().equalsIgnoreCase(user.getPrimaryGroup())) {
                phub.setRank(rank);
                System.out.println(rank);
            }
        }
        if(phub.getRank() == null) {
            phub.setRank(Rank.PLAYER);
        }

        if(phub.getRank() != Rank.PLAYER) {
            Bukkit.broadcastMessage(player.getPlayerListName() + "§e a rejoint le Lobby !");
        }
    }

    public void resetPlayers() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player player : Bukkit.getOnlinePlayers()) {
                    newPlayer(player);
                }
            }
        }.runTaskLater(this.main, 20*3L);

    }
}
