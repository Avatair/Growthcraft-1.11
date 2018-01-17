package growthcraft.apples.init;

import growthcraft.apples.blocks.*;
import growthcraft.apples.handlers.GrowthcraftApplesColorHandler;
import growthcraft.core.common.definition.BlockDefinition;
import growthcraft.core.common.definition.BlockTypeDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.ItemSlab;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import static growthcraft.core.GrowthcraftCore.tabGrowthcraft;

public class GrowthcraftApplesBlocks {

    public static BlockDefinition blockApple;
    public static BlockDefinition blockAppleDoor;
    public static BlockDefinition blockAppleFence;
    public static BlockDefinition blockAppleFenceGate;
    public static BlockDefinition blockAppleLeaves;
    public static BlockDefinition blockAppleLog;
    public static BlockDefinition blockApplePlanks;
    public static BlockDefinition blockAppleSapling;
    public static BlockTypeDefinition<BlockSlab> blockAppleSlabHalf;
    public static BlockTypeDefinition<BlockSlab> blockAppleSlabDouble;
    public static BlockDefinition blockAppleStairs;

    public static void preInit() {
        blockApple = new BlockDefinition( new BlockApple("apple_crop") );
        blockAppleDoor = new BlockDefinition( new BlockAppleDoor("apple_door") );
        blockAppleFence = new BlockDefinition( new BlockAppleFence("apple_fence") );
        blockAppleFenceGate = new BlockDefinition( new BlockAppleFenceGate("apple_fence_gate") );
        blockAppleLeaves = new BlockDefinition( new BlockAppleLeaves("apple_leaves") );
        blockAppleLog = new BlockDefinition( new BlockAppleLog("apple_log") );
        blockApplePlanks = new BlockDefinition( new BlockApplePlanks("apple_planks") );
        blockAppleSapling = new BlockDefinition( new BlockAppleSapling("apple_sapling") );
        blockAppleSlabHalf = new BlockTypeDefinition<BlockSlab>( new BlockAppleSlabHalf("apple_slab_half") );
        blockAppleSlabDouble = new BlockTypeDefinition<BlockSlab>( new BlockAppleSlabDouble("apple_slab_double") );
        blockAppleStairs = new BlockDefinition( new BlockAppleStairs("apple_stairs", blockApplePlanks.getDefaultState()) );
        // Register the blocks
        register();
    }

    public static void register() {
    	blockApple.register(false);
    	blockAppleDoor.register(false);
    	blockAppleFence.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleFence.register(true);
    	blockAppleFenceGate.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleFenceGate.register(true);
    	blockAppleLeaves.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleLeaves.register(true);
    	blockAppleLog.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleLog.register(true);
    	blockApplePlanks.getBlock().setCreativeTab(tabGrowthcraft);
    	blockApplePlanks.register(true);
    	blockAppleSapling.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleSapling.register(true);
    	blockAppleSlabHalf.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleSlabHalf.register(new ItemSlab(blockAppleSlabHalf.getBlock(), blockAppleSlabHalf.getBlock(), blockAppleSlabDouble.getBlock()));
    	blockAppleSlabDouble.register(false);
    	blockAppleStairs.getBlock().setCreativeTab(tabGrowthcraft);
    	blockAppleStairs.register(true);
    }

    public static void registerRenders() {
    	blockApple.registerRender();
    	blockAppleDoor.registerRender();
    	blockAppleFence.registerRender();
    	blockAppleFenceGate.registerRender();
    	blockAppleLeaves.registerRender();
    	blockAppleLog.registerRender();
    	blockAppleSapling.registerRender();
    	blockApplePlanks.registerRender();
    	blockAppleStairs.registerRender();
    	blockAppleSlabHalf.registerRender();
    }

    public static void registerBlockColorHandlers() {
        registerBlockColorHandler(blockAppleLeaves.getBlock());
    }
    /*
     * Credit to CJMinecraft for identifying how to ignore properties.
     */
    @SideOnly(Side.CLIENT)
    public static void setCustomStateMappers() {
        ModelLoader.setCustomStateMapper(blockAppleFenceGate.getBlock(), (new StateMap.Builder().ignore(BlockFenceGate.POWERED)).build());
    }

    @SideOnly(Side.CLIENT)
    public static void registerBlockColorHandler(Block block) {
        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        blockColors.registerBlockColorHandler(new GrowthcraftApplesColorHandler(), block);
    }

    /* No need to edit below */
/*
    public static void registerBlock(Block block) {
        registerBlock(block, true, true);
    }

    public static void registerBlock(Block block, ItemBlock itemBlock) {
        block.setCreativeTab(tabGrowthcraft);
        GameRegistry.register(block);
        GameRegistry.register(itemBlock.setRegistryName(block.getRegistryName()));
    }

    public static void registerBlock(Block block, boolean setCreativeTab, boolean registerItemBlock ) {
        GameRegistry.register(block);

        if(setCreativeTab) {
            block.setCreativeTab(tabGrowthcraft);
        }
        if(registerItemBlock) {
            GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    public static void registerRender(Block block) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0,
                new ModelResourceLocation(new ResourceLocation(Reference.MODID,
                        block.getUnlocalizedName().substring(5)), "inventory"));
    }

    public static void  registerRender(Block block, int meta, String fileName){
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta,
                new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
    } */
}
