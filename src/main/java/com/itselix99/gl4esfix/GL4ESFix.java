package com.itselix99.gl4esfix;

import com.itselix99.gl4esfix.mixin.GL4ESFixTessellatorAccessor;
import net.fabricmc.api.ClientModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GL4ESFix implements ClientModInitializer {
    public static Logger LOGGER = LogManager.getLogger("GL4ES Fix");

    @Override
    public void onInitializeClient() {
        LOGGER.info("Initializing GL4ES Fix");
        GL4ESFixTessellatorAccessor.setTriangleMode(false);
        LOGGER.info("Fix applied successfully");
    }
}