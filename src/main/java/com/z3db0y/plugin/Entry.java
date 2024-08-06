package com.z3db0y.plugin;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Path;

public class Entry implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        Path configPath = FabricLoader.getInstance().getConfigDir().resolve("seedfixer.toml");
        Config.load(configPath);
    }
}
