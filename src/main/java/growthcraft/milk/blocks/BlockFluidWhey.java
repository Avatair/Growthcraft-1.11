package growthcraft.milk.blocks;

import growthcraft.milk.Reference;
import growthcraft.milk.init.GrowthcraftMilkFluids;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.BlockFluidClassic;

import static growthcraft.core.GrowthcraftCore.tabGrowthcraft;

public class BlockFluidWhey extends BlockFluidClassic {

    public BlockFluidWhey(String unlocalizedName) {
        super(GrowthcraftMilkFluids.fluidWhey, Material.WATER);
        this.setUnlocalizedName(unlocalizedName);
        this.setRegistryName(new ResourceLocation(Reference.MODID, unlocalizedName));
        this.setCreativeTab(tabGrowthcraft);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

}