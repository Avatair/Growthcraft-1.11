package growthcraft.milk.common.tileentity.device;

import growthcraft.core.common.tileentity.device.DeviceFluidSlot;
import growthcraft.core.common.tileentity.device.DeviceProgressive;
import growthcraft.core.common.tileentity.device.IFluidTanks;
import growthcraft.milk.api.MilkRegistry;
import growthcraft.milk.api.processing.pancheon.PancheonRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;

public class Pancheon extends DeviceProgressive
{
	private DeviceFluidSlot inputSlot;
	private DeviceFluidSlot topSlot;
	private DeviceFluidSlot bottomSlot;

	/**
	 * @param te - tile entity
	 * @param fsInput - input fluid slot
	 * @param fsTop - top output slot
	 * @param fsBottom - bottom output slot
	 */
	public Pancheon(TileEntity te, int fsInput, int fsTop, int fsBottom)
	{
		super(te);
		if (te instanceof IFluidTanks)
		{
			final IFluidTanks ifl = (IFluidTanks)te;
			this.inputSlot = new DeviceFluidSlot(ifl, fsInput);
			this.topSlot = new DeviceFluidSlot(ifl, fsTop);
			this.bottomSlot = new DeviceFluidSlot(ifl, fsBottom);
		}
		else
		{
			throw new IllegalArgumentException("The provided TileEntity MUST implement the IFluidTanks interface");
		}
	}

	/**
	 * Get the matching recipe
	 *
	 * @return recipe
	 */
	private PancheonRecipe getRecipe()
	{
		return MilkRegistry.instance().pancheon().getRecipe(inputSlot.get());
	}

	/**
	 * Get the matching recipe AND it can be worked on with the current pancheon
	 *
	 * @return recipe
	 */
	public PancheonRecipe getWorkingRecipe()
	{
		final PancheonRecipe recipe = getRecipe();
		if (recipe == null) return null;
		if (!this.topSlot.hasMatchingWithCapacity(recipe.getTopOutputFluid())) return null;
		if (!this.bottomSlot.hasMatchingWithCapacity(recipe.getBottomOutputFluid())) return null;
		return recipe;
	}

	/**
	 * Complete the process and commit the changes
	 */
	private void commitRecipe()
	{
		final PancheonRecipe recipe = getRecipe();
		if (recipe != null)
		{
			this.inputSlot.consume(recipe.getInputFluid().amount, true);

			final FluidStack top = recipe.getTopOutputFluid();
			if (top != null) this.topSlot.fill(top, true);

			final FluidStack bottom = recipe.getBottomOutputFluid();
			if (bottom != null) this.bottomSlot.fill(bottom, true);
			markDirty();
			markForUpdate();
		}
	}

	/**
	 * Tick update
	 */
	public void update()
	{
		final PancheonRecipe recipe = getWorkingRecipe();
		if (recipe != null)
		{
			setTimeMax(recipe.getTime());
			increaseTime();
			if (time >= timeMax)
			{
				resetTime();
				commitRecipe();
			}
		}
		else
		{
			if (resetTime()) markDirty();
		}
	}
}
