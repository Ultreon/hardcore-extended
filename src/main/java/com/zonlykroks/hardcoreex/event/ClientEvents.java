package com.zonlykroks.hardcoreex.event;

import com.zonlykroks.hardcoreex.HardcoreExtended;
import com.zonlykroks.hardcoreex.network.Network;
import com.zonlykroks.hardcoreex.network.RequestChallengesPacket;
import net.minecraft.client.gui.screen.DirtMessageScreen;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = HardcoreExtended.MOD_ID)
public class ClientEvents {
    private static boolean joined = false;

    @SubscribeEvent
    public static void onOpenScreen(GuiOpenEvent event) {
        if (event.getGui() == null && !joined) {
            DirtMessageScreen screen = new DirtMessageScreen(new TranslationTextComponent("message.hardcoreex.world_init"));
            event.setGui(screen);
            joined = true;
            Network.sendToServer(new RequestChallengesPacket());
        }
    }

    @SubscribeEvent
    public static void onPlayerLoggerOut(PlayerEvent.PlayerLoggedOutEvent event) {
        joined = false;
    }
}
