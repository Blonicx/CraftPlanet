package com.blonicx.craftplanet.mixin.rendering.entity.living;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import com.blonicx.craftplanet.rendering.Cape;
import com.blonicx.craftplanet.rendering.TextureLoader;
import com.mojang.authlib.GameProfile;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.player.PlayerSkin;
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

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerEntityMixin {
    @Shadow @Nullable
    protected abstract PlayerInfo getPlayerInfo();

    @Inject(method = "<init>", at = @At("HEAD"))
    private static void init(ClientLevel world, GameProfile profile, CallbackInfo ci){
        if (!Objects.equals(CPlanetConfig.INSTANCE.instance().cape_name, "")) {
            TextureLoader.CAPE_TEXTURE = TextureLoader.loadTextureFromFile(
                    new File(FabricLoader.getInstance().getConfigDir().toFile(), "craftplanet/cache/" + CPlanetConfig.INSTANCE.instance().cape_name),
                    "current_cape"
            );
        }
    }

    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void getSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        if(isLocal()) {
            PlayerSkin original = cir.getReturnValue();

            PlayerSkin modified = PlayerSkin.insecure(
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
        return Objects.equals(getPlayerInfo(), Minecraft.getInstance().getConnection().getPlayerInfo(Minecraft.getInstance().player.getUUID()));
    }
}
