package fr.namu.hub.util;

import com.comphenix.protocol.PacketType;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers;
import fr.namu.hub.MainHUB;
import fr.namu.hub.PlayerHUB;
import fr.namu.hub.enums.Particles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.reflect.InvocationTargetException;

public class ParticleUtil {

    private MainHUB main;

    public ParticleUtil(MainHUB main) {
        this.main = main;
    }

    public void startParticles(Player player, Particles particle) {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        PlayerHUB phub = this.main.phub.get(player.getUniqueId());

        if(phub.getParticle() != null && phub.getParticle().equals(particle)) {
            return;
        }

        player.sendMessage(InfoUtil.prefix + "§aVous avez activé la particule §e" + particle.getName() + "§a avec succès !");
        phub.setParticle(particle);

        switch (particle) {
            case HEART: {
                double radius = 0.5;
                new BukkitRunnable() {
                    double time = 0;
                    ProtocolManager protocol = ProtocolLibrary.getProtocolManager();

                    @Override
                    public void run() {
                        if (!player.isOnline() || phub.getParticle() == null || !phub.getParticle().equals(particle)) {
                            cancel();
                            return;
                        }
                        Location loc = player.getLocation();
                        double x = radius * Math.sin(time);
                        double z = radius * Math.cos(time);
                        PacketContainer packet = protocol.createPacket(PacketType.Play.Server.WORLD_PARTICLES);
                        packet.getModifier().writeDefaults();
                        packet.getParticles().write(0, EnumWrappers.Particle.HEART);
                        packet.getFloat().write(0, (float) (loc.getX() + x)).write(1, (float) (loc.getY() + 2)).write(2, (float) (loc.getZ() + z));
                        Bukkit.getOnlinePlayers().forEach(online -> {
                            try {
                                protocol.sendServerPacket(online, packet);
                            } catch (InvocationTargetException e1) {
                                e1.printStackTrace();
                            }
                        });
                        time += 0.5;
                    }
                }.runTaskTimer(main, 20, 5);
            }
                break;
        }
    }
}
