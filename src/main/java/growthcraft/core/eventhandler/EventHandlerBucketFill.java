package growthcraft.core.eventhandler;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import growthcraft.core.GrowthcraftCore;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventHandlerBucketFill
{
	public static interface IBucketEntry
	{
		ItemStack getItemStack();
		boolean matches(@Nonnull World world, @Nonnull RayTraceResult res);
		void commit(@Nonnull EntityPlayer player, @Nonnull World world, @Nonnull RayTraceResult res);
	}

	public static class GenericBucketEntry implements IBucketEntry
	{
		private final Block block;
		private final ItemStack itemStack;

		public GenericBucketEntry(Block blk, ItemStack stack)
		{
			this.block = blk;
			this.itemStack = stack;
		}

		public ItemStack getItemStack()
		{
			return itemStack;
		}

		public boolean matches(@Nonnull World world, @Nonnull RayTraceResult res)
		{
			final IBlockState srcBlockState = world.getBlockState(res.getBlockPos());
			final Block srcBlock = srcBlockState.getBlock();

			if (block.equals(srcBlock))
			{
				return srcBlock.getMetaFromState(srcBlockState) == 0;
			}
			return false;
		}

		public void commit(@Nonnull EntityPlayer player, @Nonnull World world, @Nonnull RayTraceResult res)
		{
			world.setBlockToAir(res.getBlockPos());
		}

		public String toString()
		{
			return String.format("GenericBucketEntry{ block: {%s}, item_stack: {%s} }", block, itemStack);
		}
	}

	private static EventHandlerBucketFill INSTANCE = new EventHandlerBucketFill();
	private List<IBucketEntry> buckets = new ArrayList<IBucketEntry>();

	public static EventHandlerBucketFill instance()
	{
		return INSTANCE;
	}

	public void addEntry(@Nonnull IBucketEntry entry)
	{
		buckets.add(entry);
		GrowthcraftCore.logger.debug("Added new Bucket Entry {%s}", entry);
	}

	public EventHandlerBucketFill register(Block block, ItemStack stack)
	{
		addEntry(new GenericBucketEntry(block, stack));
		return this;
	}

	public EventHandlerBucketFill register(Block block, Item item)
	{
		return register(block, new ItemStack(item, 1));
	}

	private ItemStack fillCustomBucket(FillBucketEvent event)
	{
		for (IBucketEntry entry : buckets)
		{
			if (entry.matches(event.getWorld(), event.getTarget()))
			{
				entry.commit(event.getEntityPlayer(), event.getWorld(), event.getTarget());
				return entry.getItemStack();
			}
		}
		return null;
	}

	@SubscribeEvent
	public void onBucketFill(FillBucketEvent event)
	{
		if (event.getWorld().isRemote ||
			event.getResult() != null ||
			event.getResult() != Result.DEFAULT)
		{
			return;
		}

		final ItemStack result = fillCustomBucket(event);

		if (result != null)
		{
			event.setFilledBucket( result.copy() );
			event.setResult(Result.ALLOW);
		}
	}
}