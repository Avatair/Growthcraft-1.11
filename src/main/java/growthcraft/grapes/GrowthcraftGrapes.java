package growthcraft.grapes;

import growthcraft.grapes.init.GrowthcraftGrapesBlocks;
import growthcraft.grapes.init.GrowthcraftGrapesFluids;
import growthcraft.grapes.init.GrowthcraftGrapesItems;
import growthcraft.grapes.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class GrowthcraftGrapes {

    @Mod.Instance(Reference.MODID)
    public static GrowthcraftGrapes instance;

    @SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        GrowthcraftGrapesBlocks.init();
        GrowthcraftGrapesBlocks.register();

        GrowthcraftGrapesItems.init();
        GrowthcraftGrapesItems.register();
        
    	GrowthcraftGrapesFluids.init();
    	GrowthcraftGrapesFluids.register();

        proxy.preInit();

    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init();
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }

}
