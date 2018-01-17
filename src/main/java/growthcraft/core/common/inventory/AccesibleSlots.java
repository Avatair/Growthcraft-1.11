package growthcraft.core.common.inventory;

import java.util.Arrays;

import net.minecraft.util.EnumFacing;

public class AccesibleSlots
{
	private int[][] accessibleSlots;

	public AccesibleSlots(int[][] accs)
	{
		this.accessibleSlots = accs;
	}

	public boolean sideEnabled(EnumFacing side)
	{
		return accessibleSlots[side.ordinal()].length > 0;
	}

	public boolean sideContains(EnumFacing side, int index)
	{
		if (sideEnabled(side))
		{
			return Arrays.binarySearch(accessibleSlots[side.ordinal()], index) >= 0;
		}
		return false;
	}

	public int[] slotsAt(EnumFacing side)
	{
		return accessibleSlots[side.ordinal()];
	}
}
