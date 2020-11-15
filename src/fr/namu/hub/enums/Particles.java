package fr.namu.hub.enums;

import com.comphenix.protocol.wrappers.EnumWrappers;
import org.bukkit.Material;


public enum Particles {

    HEART("Coeurs", EnumWrappers.Particle.HEART, "selenium.particle.heart", Material.RED_ROSE),

    ;

    private final String name;
    private final EnumWrappers.Particle particle;
    private final String perm;
    private final Material mat;

    Particles(String name, EnumWrappers.Particle particle, String perm, Material mat) {
        this.name = name;
        this.particle = particle;
        this.perm = perm;
        this.mat = mat;
    }

    public String getName() {
        return this.name;
    }

    public EnumWrappers.Particle getParticle() {
        return particle;
    }

    public String getPerm() {
        return perm;
    }

    public Material getMat() {
        return mat;
    }
}
