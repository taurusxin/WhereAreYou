package org.rhyland.minecraft.whereareyou.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;

import static net.minecraft.command.argument.EntityArgumentType.getPlayer;
import static net.minecraft.server.command.CommandManager.literal;
import static net.minecraft.server.command.CommandManager.argument;

public class CommandWhere {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(literal("where")
                .then(argument("player", EntityArgumentType.player())
                        .executes(context -> where(context.getSource(), getPlayer(context, "player"))))
        );
    }

    public static int where(ServerCommandSource source, ServerPlayerEntity player) throws CommandSyntaxException {
        ServerPlayerEntity requestedPlayer = source.getPlayer();

        BlockPos pos = player.getBlockPos();
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();

        TranslatableText text = null;

        if (requestedPlayer.getUuid() != player.getUuid()) {
            text = new TranslatableText(
                    "chat.where.other",
                    player.getName().asString(), x, y, z);
        } else {
            text = new TranslatableText(
                    "chat.where.me",
                    x, y, z);
        }
        requestedPlayer.sendSystemMessage(text, Util.NIL_UUID);

        return Command.SINGLE_SUCCESS;
    }
}
