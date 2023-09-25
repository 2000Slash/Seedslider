package net.nilsh.mixin;

import net.minecraft.client.gui.widget.DirectionalLayoutWidget;
import net.minecraft.client.gui.widget.GridWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(DirectionalLayoutWidget.class)
public interface DirectionalLayoutWidgetAccessor {
    @Accessor
    GridWidget getGrid();
}
