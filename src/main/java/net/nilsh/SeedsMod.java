package net.nilsh;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.*;

public class SeedsMod implements ClientModInitializer {
    public final static Logger LOGGER = LoggerFactory.getLogger(SeedsMod.class);

    @Override
    public void onInitializeClient() {
        LOGGER.info("Hello world");
    }

    public static interface LongSliderCallbacks extends SimpleOption.SliderCallbacks<Long> {
        public long minInclusive();

        public long maxInclusive();

        @Override
        default public double toSliderProgress(Long number) {
            return MathHelper.map(number.longValue(), minInclusive(), maxInclusive(), 0.0f, 1.0f);
        }

        @Override
        default public Long toValue(double d) {
            return MathHelper.lfloor(MathHelper.map(d, 0.0, 1.0, (double)this.minInclusive(), (double)this.maxInclusive()));
        }

        default public SimpleOption.SliderCallbacks<Long> withModifier(final LongFunction<? extends Long> sliderProgressValueToValue, final ToLongFunction<? super Long> valueToSliderProgressValue) {
            return new SimpleOption.SliderCallbacks<Long>(){

                @Override
                public Optional<Long> validate(Long value) {
                    return this.validate(valueToSliderProgressValue.applyAsLong(value)).map(sliderProgressValueToValue::apply);
                }

                @Override
                public double toSliderProgress(Long value) {
                    return this.toSliderProgress(valueToSliderProgressValue.applyAsLong(value));
                }

                @Override
                public Long toValue(double sliderProgress) {
                    return sliderProgressValueToValue.apply(this.toValue(sliderProgress));
                }

                @Override
                public Codec<Long> codec() {
                    return this.codec().xmap(sliderProgressValueToValue::apply, valueToSliderProgressValue::applyAsLong);
                }
            };
        }
    }

    static <N extends Number & Comparable<N>> Function<N, DataResult<N>> checkRange(final N minInclusive, final N maxInclusive) {
        return value -> {
            if (value.compareTo(minInclusive) >= 0 && value.compareTo(maxInclusive) <= 0) {
                return DataResult.success(value);
            }
            return DataResult.error(() -> "Value " + value + " outside of range [" + minInclusive + ":" + maxInclusive + "]", value);
        };
    }

    @Environment(value= EnvType.CLIENT)
    public static record ValidatingIntSliderCallbacks(long minInclusive, long maxInclusive) implements LongSliderCallbacks
    {
        @Override
        public Optional<Long> validate(Long integer) {
            return integer.compareTo(this.minInclusive()) >= 0 && integer.compareTo(this.maxInclusive()) <= 0 ? Optional.of(integer) : Optional.empty();
        }

        @Override
        public Codec<Long> codec() {
            Function<Long, DataResult<Long>> checker = checkRange(minInclusive, maxInclusive);
            return Codec.LONG.flatXmap(checker, checker);
        }
    }

    public static Text getGenericValueText(Text prefix, long value) {
        return GameOptions.getGenericValueText(prefix, Text.literal(Long.toString(value)));
    }
}