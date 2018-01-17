package growthcraft.cellar.util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import growthcraft.cellar.api.booze.BoozeEffect;
import growthcraft.cellar.api.processing.common.Residue;
import growthcraft.core.api.fluids.FluidTag;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * Now you can make the same awesome we use internally
 */
public interface ICellarBoozeBuilder
{
	public ICellarBoozeBuilder tags(FluidTag... tags);
	public ICellarBoozeBuilder brewsTo(@Nonnull FluidStack result, @Nonnull Object stack, int time, @Nullable Residue residue);
	public ICellarBoozeBuilder brewsFrom(@Nonnull FluidStack src, @Nonnull Object stack, int time, @Nullable Residue residue);
	public ICellarBoozeBuilder fermentsTo(@Nonnull FluidStack resultFluid, @Nonnull Object stack, int time);
	public ICellarBoozeBuilder fermentsFrom(@Nonnull Object srcFluid, @Nonnull Object stack, int time);
	public ICellarBoozeBuilder pressesFrom(@Nonnull Object inputStack, int time, int amount, @Nullable Residue residue);
	public ICellarBoozeBuilder culturesTo(int amount, @Nonnull ItemStack stack, float heat, int time);
	public BoozeEffect getEffect();
}
