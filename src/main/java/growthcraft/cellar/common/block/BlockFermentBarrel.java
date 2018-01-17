package growthcraft.cellar.common.block;

import growthcraft.cellar.GrowthcraftCellar;
import growthcraft.cellar.Reference;
import growthcraft.cellar.common.tileentity.TileEntityFermentBarrel;
import growthcraft.cellar.events.EventBarrelDrained;
import growthcraft.core.Utils;
import growthcraft.core.api.utils.BlockFlags;
import growthcraft.core.lib.legacy.ILegacyFluidHandler;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;

public class BlockFermentBarrel extends BlockOrientedCellarContainer {

	public BlockFermentBarrel(String unlocalizedName) {
		super(Material.WOOD);
		setTileEntityType(TileEntityFermentBarrel.class);
		setHardness(2.5F);
//TODO:		setStepSound(soundTypeWood);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));

		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean shouldRestoreBlockState(World world, BlockPos pos, ItemStack stack)
	{
		return true;
	}

	@Override
	protected boolean shouldDropTileStack(World world, BlockPos pos, IBlockState state, int fortune)
	{
		return true;
	}

	@Override
	protected boolean playerDrainTank(World world, BlockPos pos, ILegacyFluidHandler tank, ItemStack held, EntityPlayer player)
	{
		final FluidStack available = Utils.playerDrainTank(world, pos, tank, held, player);
		if (available != null && available.amount > 0)
		{
			GrowthcraftCellar.CELLAR_BUS.post(new EventBarrelDrained(player, world, pos, available));
			return true;
		}
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
		setOrientWhenPlacing(worldIn, pos, state, placer, true);
	}
	
	/************
	 * RENDERS
	 ************/
    
    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

	/************
	 * COMPARATOR
	 ************/
	@Override
	public boolean hasComparatorInputOverride(IBlockState state)
	{
		return true;
	}

	@Override
	public int getComparatorInputOverride(IBlockState state, World world, BlockPos pos)
	{
		final TileEntityFermentBarrel te = getTileEntity(world, pos);
		if (te != null)
		{
			return te.getDeviceProgressScaled(15);
		}
		return 0;
	}
}
