package growthcraft.core.api.effect;

import java.util.List;
import java.util.Random;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/**
 * As its name implies, this Effect will REMOVE a Potion Effect from the target.
 */
public class EffectRemovePotionEffect extends AbstractEffect
{
	// REVISE_ME 0
	
	private Potion potion;

	public EffectRemovePotionEffect(Potion potion)
	{
		this.potion = potion;
	}

	public EffectRemovePotionEffect() {}

	public EffectRemovePotionEffect setPotion(Potion potion)
	{
		this.potion = potion;
		return this;
	}

	public Potion getPotion()
	{
		return potion;
	}

	/**
	 * Removes the potion effect from the entity, if the entity is a EntityLivingBase
	 *
	 * @param world - world that the entity is currently present ing
	 * @param entity - entity to apply the effect to
	 * @param data - any extra data you want to pass along
	 */
	@Override
	public void apply(World world, Entity entity, Random random, Object data)
	{
		if (potion != null)
		{
			if (entity instanceof EntityLivingBase)
			{
				((EntityLivingBase)entity).removePotionEffect(potion);
			}
		}
	}

	@Override
	protected void getActualDescription(List<String> list)
	{
		final PotionEffect pe = new PotionEffect(getPotion(), 1000, 0);
		final String potionName = I18n.format(pe.getEffectName()).trim();
		list.add(I18n.format("effect.remove_potion_effect.format", potionName));
	}

	@Override
	protected void readFromNBT(NBTTagCompound data)
	{
		this.potion = Potion.getPotionById(data.getInteger("potion_id"));
	}

	@Override
	protected void writeToNBT(NBTTagCompound data)
	{
		data.setInteger("potion_id", Potion.getIdFromPotion(potion));
	}
}
