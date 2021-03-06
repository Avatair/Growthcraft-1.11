package growthcraft.fishtrap;

import growthcraft.fishtrap.client.gui.GuiHandler;
import growthcraft.fishtrap.handlers.RecipeHandler;
import growthcraft.fishtrap.init.GrowthcraftFishtrapBlocks;
import growthcraft.fishtrap.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class GrowthcraftFishtrap {

    @Mod.Instance(Reference.MODID)
    public static GrowthcraftFishtrap instance;

    @SidedProxy(serverSide = Reference.SERVER_PROXY_CLASS, clientSide = Reference.CLIENT_PROXY_CLASS)
    public static CommonProxy proxy;

    @Mod.EventHandler
    public static void preInit(FMLPreInitializationEvent event) {
        GrowthcraftFishtrapBlocks.init();
        GrowthcraftFishtrapBlocks.register();
        proxy.registerRenders();
        proxy.registerTitleEntities();
    }

    @Mod.EventHandler
    public static void init(FMLInitializationEvent event) {
        proxy.init();

        RecipeHandler.registerCraftingRecipes();

        NetworkRegistry.INSTANCE.registerGuiHandler(Reference.MODID, new GuiHandler());
    }

    @Mod.EventHandler
    public static void postInit(FMLPostInitializationEvent event) {

    }


}
