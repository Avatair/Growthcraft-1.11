package growthcraft.cellar.api.processing.heatsource.user;

import java.util.ArrayList;
import java.util.List;

import growthcraft.core.api.schema.ICommentable;

public class UserHeatSourceEntries implements ICommentable
{
	public String comment = "";
	public List<UserHeatSourceEntry> data = new ArrayList<UserHeatSourceEntry>();

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
