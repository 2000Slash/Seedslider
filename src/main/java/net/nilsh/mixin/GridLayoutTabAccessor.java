package net.nilsh.mixin;

import net.minecraft.client.gui.components.tabs.GridLayoutTab;
import net.minecraft.client.gui.layouts.GridLayout;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GridLayoutTab.class)
public interface GridLayoutTabAccessor {
    @Accessor
    GridLayout getLayout();
}
