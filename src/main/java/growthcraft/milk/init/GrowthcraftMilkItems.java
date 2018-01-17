package growthcraft.milk.init;

import growthcraft.milk.Reference;
import growthcraft.milk.handlers.EnumHandler;
import growthcraft.milk.items.*;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import static growthcraft.core.GrowthcraftCore.tabGrowthcraft;

import growthcraft.core.GrowthcraftCore;
import growthcraft.core.common.definition.ItemDefinition;

public class GrowthcraftMilkItems {

    public static ItemDefinition stomach;
    public static Item thistle_seed;
    public static ItemDefinition thistle;
    public static Item itemCheeseCloth;
    public static ItemDefinition butter;
    public static Item itemIceCream;
    public static Item itemYogurt;
    public static ItemDefinition starterCulture;
    public static Item itemAgedCheeseSlice;
    public static Item itemWaxedCheeseSlice;
    public static Item itemSimpleCheeseSlice;

    public static void init() {
        thistle = new ItemDefinition( new ItemThistle("thistle") );
        thistle_seed = new ItemSeedThistle("thistle_seed");
        stomach = new ItemDefinition(new ItemStomach("stomach") );
        itemCheeseCloth = new ItemCheeseCloth("cheese_cloth");
        starterCulture = new ItemDefinition( new ItemStarterCulture("starter_culture") );
        butter = new ItemDefinition( new ItemButter("butter", 2, 0.3F, false) );
        itemIceCream = new ItemIceCream("ice_cream", 2, 0.3F, false);
        itemYogurt = new ItemYogurt("yogurt", 2, 0.3F, false);
        itemAgedCheeseSlice = new ItemAgedCheeseSlice("cheese_aged_slice", 2, 0.3F, false);
        itemWaxedCheeseSlice = new ItemWaxedCheeseSlice("cheese_waxed_slice", 2, 0.3F, false);
        itemSimpleCheeseSlice = new ItemSimpleCheeseSlice("cheese_simple_slice", 2, 0.3F, false);
    }

    public static void register() {
        // registerItem(thistle);
    	thistle.getItem().setCreativeTab(tabGrowthcraft);
    	thistle.register();
        registerItem(thistle_seed);
        // registerItem(itemStomach);
        stomach.getItem().setCreativeTab(tabGrowthcraft);
        stomach.register();
        registerItem(itemCheeseCloth);
//        registerItem(itemStarterCulture);
        starterCulture.getItem().setCreativeTab(GrowthcraftCore.tabGrowthcraft);
        starterCulture.register();
        // registerItem(itemButter);
        butter.getItem().setCreativeTab(GrowthcraftCore.tabGrowthcraft);
        butter.register();
        registerItem(itemIceCream);
        registerItem(itemYogurt);
        registerItem(itemAgedCheeseSlice);
        registerItem(itemWaxedCheeseSlice);
        registerItem(itemSimpleCheeseSlice);

        registerOres();
    }
    
	private static void registerOres()
	{
		OreDictionary.registerOre("rennetSource", thistle.getItem());
		OreDictionary.registerOre("rennetSource", stomach.asStack());
	}

    public static void registerRenders() {
        // registerRender(thistle);
    	thistle.registerRender();
        registerRender(thistle_seed);
        // registerRender(itemStomach);
        stomach.registerRender();
        registerRender(itemCheeseCloth);
        // registerRender(itemStarterCulture);
        starterCulture.registerRender();

//        for (int i = 0; i < EnumHandler.ButterTypes.values().length; i++) {
//            registerRender(itemButter, i, "butter/butter_" + EnumHandler.ButterTypes.values()[i].getName());
//        }
        butter.registerRenders(EnumHandler.ButterTypes.class);

        for (int i = 0; i < EnumHandler.IceCreamTypes.values().length; i++) {
            registerRender(itemIceCream, i, "ice_cream/ice_cream_" + EnumHandler.IceCreamTypes.values()[i].getName());
        }

        for (int i = 0; i < EnumHandler.YogurtTypes.values().length; i++) {
            registerRender(itemYogurt, i, "yogurt/yogurt_" + EnumHandler.YogurtTypes.values()[i].getName());
        }

        for (int i = 0; i < EnumHandler.AgedCheeseTypes.values().length; i++) {
            registerRender(itemAgedCheeseSlice, i, "cheese_slice/" + EnumHandler.AgedCheeseTypes.values()[i].getName());
        }

        for (int i = 0; i < EnumHandler.WaxedCheeseTypes.values().length; i++) {
            registerRender(itemWaxedCheeseSlice, i, "cheese_slice/" + EnumHandler.WaxedCheeseTypes.values()[i].getName());
        }

        for (int i = 0; i < EnumHandler.SimpleCheeseTypes.values().length; i++) {
            registerRender(itemSimpleCheeseSlice, i, "cheese_slice/" + EnumHandler.SimpleCheeseTypes.values()[i].getName());
        }
    }
    
	public static void registerModelBakeryVariants() {
		butter.registerModelBakeryVariants(EnumHandler.ButterTypes.class);
	}

    public static void registerItem(Item item) {
        item.setCreativeTab(tabGrowthcraft);
        GameRegistry.register(item);
    }

    public static void  registerRender(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(new ResourceLocation(Reference.MODID, item.getUnlocalizedName().substring(5)), "inventory"));

    }

    public static void registerRender(Item item, int meta, String fileName) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MODID, fileName), "inventory"));
    }





}
