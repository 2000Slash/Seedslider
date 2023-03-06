package net.nilsh.mixin;

import net.minecraft.client.gui.tab.GridScreenTab;
import net.minecraft.client.gui.widget.GridWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GridScreenTab.class)
public interface GridLayoutTabAccessor {
    @Accessor
    GridWidget getGrid();
}
