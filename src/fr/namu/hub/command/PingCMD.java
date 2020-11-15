package fr.namu.hub.command;

import fr.namu.hub.util.InfoUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class PingCMD implements TabExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player)sender;

        if(args.length == 0) {
            player.sendMessage(InfoUtil.prefix + "§eVotre ping actuel est de §a" + ((CraftPlayer) player).getHandle().ping + "§ems");
            return true;
        }

        for(Player players : Bukkit.getOnlinePlayers()) {
            if (args[0].equalsIgnoreCase(players.getName())) {
                player.sendMessage(InfoUtil.prefix + "§eLe ping actuel de §b" + players.getName() + "§e est de §a" + ((CraftPlayer) players).getHandle().ping + "§ems");
                return true;
            }
        }

        player.sendMessage(InfoUtil.prefix + "§eNous n'avons pas pu trouver la personne que vous désiriez ! Son pseudo a-t-il bien été orthographié ?");



        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
