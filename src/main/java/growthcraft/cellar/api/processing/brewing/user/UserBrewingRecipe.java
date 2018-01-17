package growthcraft.cellar.api.processing.brewing.user;

import growthcraft.core.api.schema.FluidStackSchema;
import growthcraft.core.api.schema.ICommentable;
import growthcraft.core.api.schema.ItemKeySchema;
import growthcraft.core.api.schema.ResidueSchema;

public class UserBrewingRecipe implements ICommentable
{
	public String comment = "";
	public ItemKeySchema item;
	public FluidStackSchema input_fluid;
	public FluidStackSchema output_fluid;
	public ResidueSchema residue;
	public int time;

	public UserBrewingRecipe(ItemKeySchema itm, FluidStackSchema inp, FluidStackSchema out, ResidueSchema res, int tm)
	{
		this.item = itm;
		this.input_fluid = inp;
		this.output_fluid = out;
		this.residue = res;
		this.time = tm;
	}

	public UserBrewingRecipe() {}

	@Override
	public String toString()
	{
		return String.format("UserBrewingRecipe(`%s` + `%s` / %d = `%s` & `%s`)", item, input_fluid, time, output_fluid, residue);
	}

	@Override
	public void setComment(String comm)
	{
		this.comment = comm;
	}

	@Override
	public String getComment()
	{
		return comment;
	}
}
