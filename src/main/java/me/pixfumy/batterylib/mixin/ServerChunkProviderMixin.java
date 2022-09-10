package me.pixfumy.batterylib.mixin;

import net.minecraft.util.collection.LongObjectStorage;
import net.minecraft.world.chunk.ServerChunkProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerChunkProvider.class)
public class ServerChunkProviderMixin {
    @Redirect(method = "tickChunks", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/LongObjectStorage;get(J)Ljava/lang/Object;"))
    private Object removeAndGetChunk(LongObjectStorage instance, long l) {
        return instance.remove(l);
    }

    @Redirect(method = "tickChunks", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/collection/LongObjectStorage;remove(J)Ljava/lang/Object;"))
    private Object cancelOtherRemove(LongObjectStorage instance, long l) {
        return null;
    }
}
