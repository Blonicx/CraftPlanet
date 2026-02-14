package com.blonicx.craftplanet.mixin.gui.components;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import net.minecraft.client.gui.screens.multiplayer.ServerSelectionList;

// Credits: https://github.com/JustAlittleWolf/ServerPingerFixer/tree/main

/**
 * @author JustAlittleWolf
 */
@Mixin(ServerSelectionList.class)
public class MultiplayerServerListWidgetMixin {
    @Mutable @Final @Shadow
    static ThreadPoolExecutor THREAD_POOL;

    @Unique
    private static final int PINGER_THREAD_COUNT_OVERHEAD = 5;

    @Final
    @Shadow
    private List<ServerSelectionList.OnlineServerEntry> onlineServers;

    @Unique
    private static boolean threadpoolInitialized = false;

    @Inject(method = "refreshEntries", at = @At("HEAD"))
    private void updateEntriesInject(CallbackInfo ci) {
        if (!threadpoolInitialized) {
            threadpoolInitialized = true;
            clearServerPingerThreadPool();
        }
        if (THREAD_POOL.getActiveCount() >= PINGER_THREAD_COUNT_OVERHEAD) {
            clearServerPingerThreadPool();
        }
    }

    @Unique
    private void clearServerPingerThreadPool() {
        THREAD_POOL.shutdownNow();
        THREAD_POOL = new ScheduledThreadPoolExecutor(
                onlineServers.size() + PINGER_THREAD_COUNT_OVERHEAD,
                (new ThreadFactoryBuilder()).setNameFormat("Server Pinger #%d").setDaemon(true).build()
        );
    }
}
