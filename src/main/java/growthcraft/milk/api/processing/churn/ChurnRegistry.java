package growthcraft.milk.api.processing.churn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import growthcraft.core.api.fluids.FluidKey;
import growthcraft.core.api.fluids.FluidTest;
import growthcraft.milk.GrowthcraftMilk;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class ChurnRegistry
{
	private Map<Fluid, ChurnRecipe> recipes = new HashMap<Fluid, ChurnRecipe>();
	private Set<FluidKey> fluidIngredients = new HashSet<FluidKey>();

	public void addRecipe(@Nonnull ChurnRecipe recipe)
	{
		final FluidStack fluidStack = recipe.getInputFluidStack();
		final Fluid fluid = fluidStack.getFluid();
		if (recipes.containsKey(fluid))
		{
			GrowthcraftMilk.logger.warn("Overwriting existing churn recipe for {%s} with {%s}", fluidStack, recipe);
		}
		else
		{
			GrowthcraftMilk.logger.debug("Adding new churn recipe {%s}", recipe);
		}
		fluidIngredients.add(new FluidKey(fluidStack));
		recipes.put(fluid, recipe);
	}


	public void addRecipe(@Nonnull FluidStack inputFluid, @Nonnull FluidStack outputFluid, @Nullable ItemStack outputItem, int churns)
	{
		final Fluid fluid = inputFluid.getFluid();
		if (fluid == null)
		{
			throw new IllegalArgumentException("The provided input fluid is invalid.");
		}

		final ChurnRecipe recipe = new ChurnRecipe(inputFluid, outputFluid, outputItem, churns);
		addRecipe(recipe);
	}

	public boolean isFluidIngredient(@Nullable Fluid fluid)
	{
		if (fluid != null)
		{
			return fluidIngredients.contains(new FluidKey(fluid));
		}
		return false;
	}

	public boolean isFluidIngredient(@Nullable FluidStack stack)
	{
		if (FluidTest.isValid(stack))
		{
			return fluidIngredients.contains(new FluidKey(stack));
		}
		return false;
	}

	@Nullable
	public ChurnRecipe getRecipe(FluidStack stack)
	{
		if (stack == null) return null;
		final Fluid fluid = stack.getFluid();
		if (fluid == null) return null;
		final ChurnRecipe recipe = recipes.get(fluid);
		if (recipe != null)
		{
			if (recipe.isValidForRecipe(stack)) return recipe;
		}
		return null;
	}
}
