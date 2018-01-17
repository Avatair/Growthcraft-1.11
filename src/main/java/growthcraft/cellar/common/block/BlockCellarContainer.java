package growthcraft.cellar.common.block;

import growthcraft.cellar.Reference;
import growthcraft.core.common.block.GrowthcraftBlockContainer;
import growthcraft.core.common.tileentity.feature.IInteractionObject;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Base class for Cellar machines and the like
 */
public abstract class BlockCellarContainer extends GrowthcraftBlockContainer
{
	public BlockCellarContainer(Material material)
	{
		super(material);
	}

	protected boolean openGui(EntityPlayer player, World world, BlockPos pos)
	{
		final TileEntity te = getTileEntity(world, pos);
		if (te instanceof IInteractionObject)
		{
			player.openGui(Reference.MODID, 0, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
		return false;
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ)) return true;
		return !playerIn.isSneaking() && openGui(playerIn, worldIn, pos);
	}
}
