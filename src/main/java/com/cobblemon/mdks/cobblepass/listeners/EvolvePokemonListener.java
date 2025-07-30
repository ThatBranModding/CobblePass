package com.cobblemon.mdks.cobblepass.listeners;

import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.pokemon.evolution.EvolutionCompleteEvent;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.cobblemon.mdks.cobblepass.CobblePass;
import com.cobblemon.mdks.cobblepass.battlepass.PlayerBattlePass;
import net.minecraft.server.level.ServerPlayer;
import kotlin.Unit;

import java.util.UUID;

public class EvolvePokemonListener {
    public static void register() {
        CobblemonEvents.EVOLUTION_COMPLETE.subscribe(Priority.NORMAL, evt -> {
            Pokemon pokemon = evt.getPokemon();
            UUID ownerUUID = pokemon.getOwnerUUID();
            if (ownerUUID != null) {
                ServerPlayer player = CobblePass.server.getPlayerList().getPlayer(ownerUUID);
                if (player != null) {
                    PlayerBattlePass battlePass = CobblePass.battlePass.getPlayerPass(player);
                    if (battlePass != null) {
                        // Set the player to send level-up chat message on level increase
                        battlePass.setPlayer(player);
                        // Add XP from evolving a Pokemon
                        battlePass.addXP(CobblePass.config.getEvolveXP());
                    }
                }
            }
            return Unit.INSTANCE;
        });
    }
}
