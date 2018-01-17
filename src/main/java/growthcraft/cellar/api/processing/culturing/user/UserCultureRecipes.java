package growthcraft.cellar.api.processing.culturing.user;

import java.util.ArrayList;
import java.util.List;

import growthcraft.core.api.schema.ICommentable;

public class UserCultureRecipes implements ICommentable
{
	public String comment = "";
	public List<UserCultureRecipe> data = new ArrayList<UserCultureRecipe>();

	@Override
	public String getComment()
	{
		return comment;
	}

	@Override
	public void setComment(String com)
	{
		this.comment = com;
	}
}
