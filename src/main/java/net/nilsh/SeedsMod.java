package net.nilsh;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SeedsMod implements ModInitializer {
    public final static Logger LOGGER = LoggerFactory.getLogger(SeedsMod.class);
    @Override
    public void onInitialize() {
        LOGGER.info("Hello world");
    }
}

