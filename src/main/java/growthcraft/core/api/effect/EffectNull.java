package growthcraft.core.api.effect;

import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * Because sometimes you want an Effect that does ABSOLUTELY NOTHING.
 */
public class EffectNull extends AbstractEffect
{
	// REVISE_ME 0
	
	@Override
	public void apply(World world, Entity entity, Random random, Object data) {}

	@Override
	protected void getActualDescription(List<String> list)
	{
		// Set the description as "Does Nothing."
		list.add(I18n.format("effect.null.desc"));
	}

	@Override
	protected void readFromNBT(NBTTagCompound data)
	{
	}

	@Override
	protected void writeToNBT(NBTTagCompound data)
	{
	}
}
