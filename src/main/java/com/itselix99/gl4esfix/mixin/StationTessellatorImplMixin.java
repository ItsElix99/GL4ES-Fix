package com.itselix99.gl4esfix.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.modificationstation.stationapi.impl.client.render.StationTessellatorImpl;
import net.modificationstation.stationapi.mixin.render.client.TessellatorAccessor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Pseudo
@Mixin(StationTessellatorImpl.class)
public class StationTessellatorImplMixin {
    @Shadow @Final private TessellatorAccessor access;

    @WrapOperation(
            method = "quad",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/System;arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V",
                    ordinal = 1
            )
    )
    public void gl4esfix_fixQuadArrayCopyMain(Object src, int srcPos, Object dest, int destPos, int length, Operation<Void> original) {
        original.call(src, srcPos, dest, destPos, 32);
    }

    @WrapOperation(
            method = "quad",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/System;arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V",
                    ordinal = 2
            )
    )
    public void gl4esfix_cancelQuadArrayCopy1(Object src, int srcPos, Object dest, int destPos, int length, Operation<Void> original) {
    }

    @WrapOperation(
            method = "quad",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/lang/System;arraycopy(Ljava/lang/Object;ILjava/lang/Object;II)V",
                    ordinal = 3
            )
    )
    public void gl4esfix_cancelQuadArrayCopy2(Object src, int srcPos, Object dest, int destPos, int length, Operation<Void> original) {
    }

    @ModifyArgs(method = "quad", at = @At(value = "INVOKE", target = "Lnet/modificationstation/stationapi/mixin/render/client/TessellatorAccessor;stationapi$setBufferPosition(I)V"))
    public void gl4esfix_setBufferPosition(Args args) {
        args.set(0, access.stationapi$getBufferPosition() + 32);
    }

    @ModifyArgs(method = "quad", at = @At(value = "INVOKE", target = "Lnet/modificationstation/stationapi/mixin/render/client/TessellatorAccessor;stationapi$setVertexCount(I)V"))
    public void gl4esfix_setVertexCount(Args args) {
        args.set(0, access.stationapi$getVertexCount() + 4);
    }

    @ModifyArgs(method = "quad", at = @At(value = "INVOKE", target = "Lnet/modificationstation/stationapi/impl/client/render/StationTessellatorImpl;ensureBufferCapacity(I)V"))
    public void gl4esfix_setEnsureBufferCapacity(Args args) {
        args.set(0, 32);
    }
}