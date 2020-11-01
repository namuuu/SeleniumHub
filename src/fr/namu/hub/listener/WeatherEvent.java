package fr.namu.hub.listener;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherEvent implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent event) {
        World world = Bukkit.getWorld("world");
        world.setThundering(false);
        world.setStorm(false);

        System.out.println("[SELENIUM] Détection de changement de Weather ! Correction...");
    }
}
