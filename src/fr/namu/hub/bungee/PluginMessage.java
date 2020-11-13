package fr.namu.hub.bungee;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import fr.namu.hub.MainHUB;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class PluginMessage implements PluginMessageListener {

    private MainHUB plugin;

    public PluginMessage(MainHUB main) {
        this.plugin = main;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] args) {
        if(!channel.equals("BungeeCord"))
            return;

        ByteArrayDataInput input = ByteStreams.newDataInput(args);
        String subchannel = input.readUTF();
    }

    public void connect(Player player, String server) {
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Connect");
        output.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", output.toByteArray());
    }
}
