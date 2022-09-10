package me.pixfumy.batterylib.mixin;

import net.minecraft.util.collection.LongObjectStorage;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeMap;

/**
 * Rewrites this class and its relevant methods used by other classes to use a TreeMap
 */
@Mixin(LongObjectStorage.class)
public class LongObjectStorageMixin {
    private HashMap<Long, Object> storage = new HashMap<>();

    @Inject(method = "get", at = @At("HEAD"), cancellable = true)
    private void newGet(long key, CallbackInfoReturnable<Object> cir) {
        cir.setReturnValue(storage.get(key));
    }

    @Inject(method = "contains", at = @At("HEAD"), cancellable = true)
    private void newContains(long key, CallbackInfoReturnable<Object> cir) {
        cir.setReturnValue(storage.get(key) != null);
    }

    @Inject(method = "remove", at = @At("HEAD"), cancellable = true)
    private void newRemove(long key, CallbackInfoReturnable<Object> cir) {
        cir.setReturnValue(storage.remove(key));
    }

    @Inject(method = "set", at = @At("HEAD"), cancellable = true)
    private void newSet(long key, Object value, CallbackInfo ci) {
        storage.put(key, value);
    }
}
