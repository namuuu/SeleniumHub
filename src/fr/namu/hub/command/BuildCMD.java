package fr.namu.hub.command;

import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import fr.namu.hub.enums.Rank;
import fr.namu.hub.util.InfoUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildCMD implements CommandExecutor {

    private MainHUB main;

    public BuildCMD(MainHUB main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player))
            return true;

        Player player = (Player)sender;
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        if(phub.getRank() != Rank.BUILDER) {
            player.sendMessage(InfoUtil.prefix + "§cTu n'as pas les droits d'utiliser cette commande !");
            return true;
        }

        if(args.length == 1) {
            if(args[0].equalsIgnoreCase("on")) {
                phub.setBuild(true);
            } else if (args[0].equalsIgnoreCase("off")) {
                phub.setBuild(false);
            } else {
                player.sendMessage(InfoUtil.prefix + "§cNous n'avons pas compris la demande spécifiée ! Vous pouvez utiliser: §7on§8, §7off");
                return true;
            }
        }
        if(args.length == 0) {
            phub.switchBuild();
        }

        if(phub.canBuild()) {
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("    §aTu peux à présent build sur le Lobby !");
            Bukkit.broadcastMessage(" ");
            return true;
        } else {
            Bukkit.broadcastMessage(" ");
            Bukkit.broadcastMessage("    §aTu ne peux plus build sur le Lobby !");
            Bukkit.broadcastMessage(" ");
        }



        return true;
    }
}
