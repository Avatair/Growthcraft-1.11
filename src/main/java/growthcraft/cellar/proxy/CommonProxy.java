package growthcraft.cellar.proxy;

import growthcraft.cellar.Reference;
import growthcraft.cellar.common.tileentity.PREVTileEntityBrewKettle;
import growthcraft.cellar.common.tileentity.TileEntityCultureJar;
import growthcraft.cellar.common.tileentity.TileEntityFermentBarrel;
import growthcraft.cellar.init.GrowthcraftCellarBlocks;
import growthcraft.cellar.tileentity.TileEntityBrewKettle;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit() {
    }

    public void init() {
        registerModelBakeryVariants();
        registerSpecialRenders();
    }

    public void registerRenders() {
    }
    public void registerModelBakeryVariants() { }

    public void registerSpecialRenders() {
    }

    public void registerTitleEntities() {
    	GrowthcraftCellarBlocks.registerTileEntities();
    }

}
