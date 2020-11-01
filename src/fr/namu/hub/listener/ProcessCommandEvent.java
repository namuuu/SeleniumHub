package fr.namu.hub.listener;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import fr.namu.hub.util.InfoUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import sun.applet.Main;

public class ProcessCommandEvent implements Listener {

    private MainHUB main;

    private String[] blockedCommands = new String[] {"/me", "/tell", "/about", "/pl", "/plugins", "/?", "/version", "/ver"};

    public ProcessCommandEvent(MainHUB mainHUB) {
        this.main = main;
    }

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] cmd = event.getMessage().split(" ");

        for (int i = 0; i < blockedCommands.length; i++)
        {
            if (blockedCommands[i].equalsIgnoreCase(cmd[0])) {
                event.setCancelled(true);
                return;
            }
        }

        if(cmd[0].equalsIgnoreCase("/msg")) {

            if(cmd.length < 2) {
                player.sendMessage(InfoUtil.prefix + "§cTu n'as pas spécifié assez d'informations ! §7Usage : /msg <joueur> <message>");
                return;
            }

            event.setCancelled(true);

            for(Player mentionned : Bukkit.getOnlinePlayers()) {
                if(cmd[1].equalsIgnoreCase(player.getName())) {
                    String msg = "";
                    for (int i = 2; i < cmd.length; i++) {
                        msg = msg + cmd[i] + " ";
                    }
                    player.sendMessage(mentionned.getPlayerListName() + " §a» §7" + msg);
                    mentionned.sendMessage(player.getPlayerListName() + " §c« §7" + msg);
                    return;
                }
            }
            player.sendMessage(InfoUtil.prefix + "§cNous n'avons pas trouvé le joueur en question !");
        }
    }

    @EventHandler
    public void onTab(PlayerChatTabCompleteEvent event) {
        Player player = event.getPlayer();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        String[] msg = event.getChatMessage().split(" ");

        if(phub.getRank().getWeight() < 800) {
            switch (msg[0]) {
                case "/":
                case "/ver":
                case "/version":
                case "/about":
                    event.getTabCompletions().clear();
            }
        }
    }
}
