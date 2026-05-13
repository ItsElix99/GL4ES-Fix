package com.itselix99.gl4esfix.mixin;

import net.minecraft.client.render.Tessellator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Tessellator.class)
public interface GL4ESFixTessellatorAccessor {
    @Accessor("TRIANGLE_MODE")
    static void setTriangleMode(boolean bl) {
        throw new AssertionError();
    }

    @Accessor("TRIANGLE_MODE")
    static boolean isTriangleMode() {
        throw new AssertionError();
    }
}