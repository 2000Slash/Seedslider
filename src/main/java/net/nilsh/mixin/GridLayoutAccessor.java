package net.nilsh.mixin;

import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(GridLayout.class)
public interface GridLayoutAccessor {
    @Accessor
    List<LayoutElement> getChildren();

    @Accessor
    @Final
    @Mutable
    void setChildren(List<LayoutElement> children);
}
