package fr.namu.hub.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.EOFException;

public class PluginMessage implements PluginMessageListener {

    private MainHUB plugin;

    public PluginMessage(MainHUB main) {
        this.plugin = main;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] args) {
        if(!channel.equals("BungeeCord"))
            return;

        ByteArrayDataInput in = ByteStreams.newDataInput(args);
        String subchannel = in.readUTF();

        PlayerHUB phub = this.plugin.phub.get(player.getUniqueId());

        switch (subchannel){
            case "PlayerCount":
                {
                String server = in.readUTF();

                if (server.equalsIgnoreCase("ALL")) {
                    phub.setCurrentTotalPlayers(in.readInt());
                }
            }
            case "GetServer":
                {
                    try {
                        String server = in.readUTF();
                        server.replace("lobby", "SEL0");
                        phub.setCurrentServerName(server);
                    } catch (Exception e) {

                    }


                }

        }
    }

    public void connect(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
    }

    public void setTotalMembers(Player player) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("PlayerCount");
        output.writeUTF("ALL");
        player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
    }
    public void setServerName(Player player) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("GetServer");
        player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
    }
}
