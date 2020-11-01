package fr.namu.hub.listener;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import fr.namu.hub.util.InfoUtil;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    private MainHUB main;

    public ChatEvent(MainHUB mainHUB) {
        this.main = mainHUB;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        if(this.main.info.isMuteChat() && phub.getRank().getWeight() < 500) {
            event.setCancelled(true);
            player.sendMessage(InfoUtil.prefix + "§cLe chat est actuellement mute. Essayez plus tard !");
            return;
        }

        event.setFormat(player.getPlayerListName() + " §7» §f" + event.getMessage());
    }
}
