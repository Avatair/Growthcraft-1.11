package growthcraft.core.api.effect;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import growthcraft.core.api.CoreRegistry;
import growthcraft.core.api.nbt.NBTHelper;
import growthcraft.core.description.Describer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;

/**
 * A variation of the EffectRandomList, this version uses weights instead
 * linear distribution.
 */
public class EffectWeightedRandomList extends AbstractEffect
{
	// REVISE_ME 0
	
	public static class WeightedEffect extends WeightedRandom.Item implements IEffect
	{
		private IEffect effect;

		public WeightedEffect(int weight, @Nonnull IEffect eff)
		{
			super(weight);
			this.effect = eff;
		}

		public WeightedEffect()
		{
			super(1);
		}

		/**
		 * Returns the underlying effect
		 *
		 * @return effect
		 */
		public IEffect getEffect()
		{
			return effect;
		}

		@Override
		public void apply(World world, Entity entity, Random random, Object data)
		{
			effect.apply(world, entity, random, data);
		}

		@Override
		public void getDescription(List<String> list)
		{
			effect.getDescription(list);
		}

		protected void readFromNBT(NBTTagCompound data)
		{
			this.itemWeight = data.getInteger("item_weight");
			if (data.hasKey("effect"))
			{
				this.effect = CoreRegistry.instance().getEffectsRegistry().loadEffectFromNBT(data, "effect");
			}
		}

		@Override
		public void readFromNBT(NBTTagCompound data, String name)
		{
			if (data.hasKey(name))
			{
				final NBTTagCompound effectData = data.getCompoundTag(name);
				readFromNBT(effectData);
			}
			else
			{
				// LOG error
			}
		}

		protected void writeToNBT(NBTTagCompound data)
		{
			data.setInteger("item_weight", itemWeight);
			if (effect != null)
			{
				effect.writeToNBT(data, "effect");
			}
		}

		@Override
		public void writeToNBT(NBTTagCompound data, String name)
		{
			final NBTTagCompound target = new NBTTagCompound();
			final String effectName = CoreRegistry.instance().getEffectsRegistry().getName(this.getClass());
			// This is a VERY important field, this is how the effects will reload their correct class.
			target.setString("__name__", effectName);
			writeToNBT(target);

			data.setTag(name, target);
		}
	}

	private List<WeightedEffect> effects = new ArrayList<WeightedEffect>();

	/**
	 * Adds a new WeightedEffect
	 *
	 * @param weight - effect weight
	 * @param effect - the effect
	 * @return this
	 */
	public EffectWeightedRandomList add(int weight, @Nonnull IEffect effect)
	{
		effects.add(new WeightedEffect(weight, effect));
		return this;
	}

	/**
	 * Returns the weighted effect at the given index
	 *
	 * @param index - item index
	 * @return weighted effect
	 */
	public WeightedEffect getWeightedEffect(int index)
	{
		return effects.get(index);
	}

	/**
	 * Returns the weight of the item at the given index, returns 0 if
	 * no item was found
	 *
	 * @param index - item index
	 * @return item weight
	 */
	public int getItemWeight(int index)
	{
		final WeightedEffect effect = getWeightedEffect(index);
		if (effect != null) return effect.itemWeight;
		return 0;
	}

	/**
	 * Returns the underlying effect at the given index
	 *
	 * @param index - item index
	 * @return effect
	 */
	public IEffect getItemEffect(int index)
	{
		final WeightedEffect effect = getWeightedEffect(index);
		if (effect != null) return effect.getEffect();
		return null;
	}

	/**
	 * Merges another weighted effect list into the target list
	 *
	 * @param other - weighted effect list
	 * @return this
	 */
	public EffectWeightedRandomList concat(@Nonnull EffectWeightedRandomList other)
	{
		effects.addAll(other.effects);
		return this;
	}

	/**
	 * Merges a WeightedEffect list into the current weighted list
	 *
	 * @param other - weighted effect list
	 * @return this
	 */
	public EffectWeightedRandomList concat(@Nonnull List<WeightedEffect> other)
	{
		effects.addAll(other);
		return this;
	}

	/**
	 * Performs a shallow copy of the EffectList
	 *
	 * @return new effect list
	 */
	public EffectWeightedRandomList copy()
	{
		return new EffectWeightedRandomList().concat(effects);
	}

	/**
	 * Returns one of the weighted items
	 *
	 * @param random - rng
	 * @return a weighted effect
	 */
	public WeightedEffect getRandomItem(@Nonnull Random random)
	{
		return (WeightedEffect)WeightedRandom.getRandomItem(random, effects);
	}

	/**
	 * Returns the size of the list
	 *
	 * @return size
	 */
	public int size()
	{
		return effects.size();
	}

	/**
	 * Applies all of the internal effects to the targets
	 *
	 * @param world - world that the entity is currently present ing
	 * @param entity - entity to apply the effect to
	 * @param data - any extra data you want to pass along
	 */
	@Override
	public void apply(World world, Entity entity, Random random, Object data)
	{
		final IEffect effect = getRandomItem(random);
		if (effect != null) effect.apply(world, entity, random, data);
	}

	/**
	 * Adds the description of all the internal effects
	 *
	 * @param list - list to add description lines to
	 */
	@Override
	protected void getActualDescription(List<String> list)
	{
		final int totalWeight = WeightedRandom.getTotalWeight(effects);
		final List<String> tempList = new ArrayList<String>();
		for (WeightedEffect effect : effects)
		{
			tempList.clear();
			effect.getDescription(tempList);
			if (tempList.size() > 0)
			{
				final float chance = totalWeight > 0 ? (float)effect.itemWeight / (float)totalWeight : 0f;
				final String head = I18n.format("effect.weighted_random_list.format", (int)(chance * 100f));
				Describer.compactDescription(head, list, tempList);
			}
		}
	}

	@Override
	protected void readFromNBT(NBTTagCompound data)
	{
		effects.clear();
		final List<IEffect> list = new ArrayList<IEffect>();
		NBTHelper.loadEffectsList(list, data);
		for (IEffect effect : list)
		{
			if (effect instanceof WeightedEffect)
			{
				effects.add((WeightedEffect)effect);
			}
		}
	}

	@Override
	protected void writeToNBT(NBTTagCompound data)
	{
		final List<IEffect> list = new ArrayList<IEffect>();
		for (IEffect effect : effects)
		{
			list.add(effect);
		}
		NBTHelper.writeEffectsList(data, list);
	}
}
