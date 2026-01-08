package com.blonicx.craftplanet.mixin.rendering.entity.living;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import com.blonicx.craftplanet.rendering.Cape;
import com.blonicx.craftplanet.rendering.TextureLoader;
import com.mojang.authlib.GameProfile;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.SkinTextures;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;
import java.util.Objects;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    @Shadow @Nullable
    protected abstract PlayerListEntry getPlayerListEntry();

    @Inject(method = "<init>", at = @At("HEAD"))
    private static void init(ClientWorld world, GameProfile profile, CallbackInfo ci){
        if (!Objects.equals(ConfigManager.config.cape_name, "")) {
            TextureLoader.CAPE_TEXTURE = TextureLoader.loadTextureFromFile(
                    new File(FabricLoader.getInstance().getConfigDir().toFile(), "craftplanet/cache/" + ConfigManager.config.cape_name),
                    "current_cape"
            );
        }
    }

    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void getSkin(CallbackInfoReturnable<SkinTextures> cir) {
        if(isLocal()) {
            SkinTextures original = cir.getReturnValue();

            SkinTextures modified = SkinTextures.create(
                    original.body(),
                    TextureLoader.CAPE_TEXTURE != null ? new Cape(TextureLoader.CAPE_TEXTURE) : original.cape(),
                    original.elytra(),
                    original.model()
            );

            cir.setReturnValue(modified);
        }
    }

    @Unique
    boolean isLocal() {
        return Objects.equals(getPlayerListEntry(), MinecraftClient.getInstance().getNetworkHandler().getPlayerListEntry(MinecraftClient.getInstance().player.getUuid()));
    }
}
