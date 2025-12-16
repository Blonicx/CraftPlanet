package com.blonicx.craftplanet.mixin.rendering.entity.living;

import com.blonicx.craftplanet.resources.Cape;
import com.blonicx.craftplanet.resources.TextureLoader;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.SkinTextures;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.File;

@Mixin(AbstractClientPlayerEntity.class)
public abstract class AbstractClientPlayerEntityMixin {
    @Unique final Identifier CAPE_TEXTURE = TextureLoader.loadTextureFromFile(new File(
            FabricLoader.getInstance().getConfigDir().toFile(),
            "craftplanet/cache/example.png"
    ), "current_texture");

    @Inject(method = "getSkin", at = @At("RETURN"), cancellable = true)
    private void getSkin(CallbackInfoReturnable<SkinTextures> cir) {
        SkinTextures original = cir.getReturnValue();

        SkinTextures modified = SkinTextures.create(
                original.body(),
                new Cape(CAPE_TEXTURE),
                original.elytra(),
                original.model()
        );

        cir.setReturnValue(modified);
    }
}
