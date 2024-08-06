package com.z3db0y.plugin;

import com.moandjiezana.toml.Toml;
import com.moandjiezana.toml.TomlWriter;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;

public class Config {
    static Config instance;
    Values values;

    public static class Values {
        public long nether_seed;
        public long end_seed;

        Values() {
            Random rand = new Random();

            this.nether_seed = rand.nextLong();
            this.end_seed = rand.nextLong();
        }
    }

    public long getEndSeed() {
        return this.values.end_seed;
    }

    public long getNetherSeed() {
        return this.values.nether_seed;
    }
    
    public static void load(Path path) {
        Config.instance = new Config();

        if (path.toFile().exists()) {
            Config.instance.values = new Toml().read(path.toFile()).to(Values.class);
        }

        LoggerFactory.getLogger("SeedFixer").info(String.format("Loaded config [%d, %d]", Config.instance.getNetherSeed(), Config.instance.getEndSeed()));

        try {
            new TomlWriter().write(Config.instance.values, path.toFile());
        } catch (IOException ignored) {}
    }

    Config() {
        this.values = new Values();
    }

    public static Config getInstance() {
        return Config.instance;
    }
}
