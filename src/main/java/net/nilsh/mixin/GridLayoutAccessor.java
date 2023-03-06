package net.nilsh.mixin;

import java.util.List;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.Widget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({GridWidget.class})
public interface GridLayoutAccessor {
    @Accessor
    List<Widget> getChildren();

    @Accessor
    @Final
    @Mutable
    void setChildren(List<Widget> var1);
}
