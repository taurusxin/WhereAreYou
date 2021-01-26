package org.rhyland.minecraft.whereareyou;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.rhyland.minecraft.whereareyou.command.CommandWhere;

public class WhereAreYou implements ModInitializer {

    public static Logger LOGGER = LogManager.getLogger();

    public static final String MOD_ID = "whereareyou";
    public static final String MOD_NAME = "WhereAreYou";

    @Override
    public void onInitialize() {
        log(Level.INFO, "Initializing");
        InitializeCommands();
    }

    public static void log(Level level, String message) {
        LOGGER.log(level, "[" + MOD_NAME + "] " + message);
    }

    private void InitializeCommands() {
        CommandRegistrationCallback.EVENT.register(CommandWhere::register);
    }
}