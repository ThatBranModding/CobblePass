package com.cobblemon.mdks.cobblepass.listeners;

import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.events.pokemon.TradeCompletedEvent;
import com.cobblemon.mod.common.trade.PlayerTradeParticipant;
import com.cobblemon.mdks.cobblepass.CobblePass;
import com.cobblemon.mdks.cobblepass.battlepass.PlayerBattlePass;
import net.minecraft.server.level.ServerPlayer;
import kotlin.Unit;

public class TradePokemonListener {
    public static void register() {
        CobblemonEvents.TRADE_COMPLETED.subscribe(Priority.NORMAL, evt -> {
            if (evt.getTradeParticipant1() instanceof PlayerTradeParticipant participant1) {
                ServerPlayer player1 = participant1.getPlayer();
                PlayerBattlePass battlePass1 = CobblePass.battlePass.getPlayerPass(player1);
                if (battlePass1 != null) {
                    battlePass1.setPlayer(player1);
                    battlePass1.addXP(CobblePass.config.getTradeXP());
                }
            }
            if (evt.getTradeParticipant2() instanceof PlayerTradeParticipant participant2) {
                ServerPlayer player2 = participant2.getPlayer();
                PlayerBattlePass battlePass2 = CobblePass.battlePass.getPlayerPass(player2);
                if (battlePass2 != null) {
                    battlePass2.setPlayer(player2);
                    battlePass2.addXP(CobblePass.config.getTradeXP());
                }
            }
            return Unit.INSTANCE;
        });
    }
}
