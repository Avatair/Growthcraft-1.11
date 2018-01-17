package growthcraft.cellar.api.processing.yeast.user;

import java.util.ArrayList;
import java.util.List;

import growthcraft.core.api.schema.ICommentable;

public class UserYeastEntries implements ICommentable
{
	public String comment = "";
	public List<UserYeastEntry> data = new ArrayList<UserYeastEntry>();

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
