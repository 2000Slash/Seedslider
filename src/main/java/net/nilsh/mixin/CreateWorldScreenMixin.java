package net.nilsh.mixin;

import java.util.List;
import java.util.Random;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.world.CreateWorldScreen;
import net.minecraft.client.gui.widget.GridWidget;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.option.SimpleOption;
import net.nilsh.SeedsMod;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(targets = {"net.minecraft.client.gui.screen.world.CreateWorldScreen$WorldTab"})
public class CreateWorldScreenMixin {
    @Final
    @Shadow
    CreateWorldScreen field_42182;

    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void init(CallbackInfo info) {
        Random r = new Random();
        Long seed = r.nextLong();
        this.field_42182.getWorldCreator().setSeed(String.valueOf(seed));
        GridLayoutTabAccessor accessor = (GridLayoutTabAccessor)this;
        GridLayoutAccessor gridLayoutAccessor = (GridLayoutAccessor)accessor.getGrid();
        GridWidget gridLayout = (GridWidget)gridLayoutAccessor.getChildren().get(2);
        gridLayoutAccessor = (GridLayoutAccessor)gridLayout;
        List<Widget> elements = gridLayoutAccessor.getChildren();
        SimpleOption<Long> optionInstance = new SimpleOption<Long>("selectWorld.enterSeed", SimpleOption.emptyTooltip(), SeedsMod::getGenericValueText, new SeedsMod.ValidatingIntSliderCallbacks(Long.MIN_VALUE, Long.MAX_VALUE), seed, (integer) -> {
            this.field_42182.getWorldCreator().setSeed(String.valueOf(integer));
        });
        elements.set(0, optionInstance.createWidget(MinecraftClient.getInstance().options, 0, 0, 310));
        elements.remove(1);
        gridLayoutAccessor.setChildren(elements);
    }
}

