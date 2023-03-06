package net.nilsh;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeedsMod implements ClientModInitializer {
    public final static Logger LOGGER = LoggerFactory.getLogger(SeedsMod.class);
    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello world");
    }
}

