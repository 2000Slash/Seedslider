package net.nilsh.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.client.gui.layouts.GridLayout;
import net.minecraft.client.gui.layouts.LayoutElement;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Random;

@Mixin(targets = "net.minecraft.client.gui.screens.worldselection.CreateWorldScreen$WorldTab")
public class CreateWorldScreenMixin {
    @Final
    @Shadow
    CreateWorldScreen field_42182;

    @Inject(method = "<init>()V", at = @At(value = "RETURN"))
    private void init(CallbackInfo info) {
        Random r = new Random();
        int seed = r.nextInt();
        field_42182.getUiState().setSeed(String.valueOf(seed));
        GridLayoutTabAccessor accessor = (GridLayoutTabAccessor) (Object) this;
        GridLayoutAccessor gridLayoutAccessor = (GridLayoutAccessor) accessor.getLayout();
        GridLayout gridLayout = (GridLayout) gridLayoutAccessor.getChildren().get(2);
        gridLayoutAccessor = (GridLayoutAccessor) gridLayout;
        List<LayoutElement> elements = gridLayoutAccessor.getChildren();
        OptionInstance<Integer> optionInstance = new OptionInstance<Integer>("selectWorld.enterSeed", OptionInstance.noTooltip(), Options::genericValueLabel, new OptionInstance.IntRange(Integer.MIN_VALUE, Integer.MAX_VALUE), seed, integer -> field_42182.getUiState().setSeed(String.valueOf(integer)));
        elements.set(0, optionInstance.createButton(Minecraft.getInstance().options, 0, 0, 310));
        elements.remove(1);
        gridLayoutAccessor.setChildren(elements);
    }
}
