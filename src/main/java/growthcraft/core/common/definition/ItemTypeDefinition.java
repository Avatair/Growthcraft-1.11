package growthcraft.core.common.definition;

import javax.annotation.Nonnull;

import growthcraft.core.api.definition.ISubItemStackFactory;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import growthcraft.core.api.definition.IObjectVariant;

public class ItemTypeDefinition<T extends Item> extends ObjectDefinition<T> implements ISubItemStackFactory
{
	public ItemTypeDefinition(@Nonnull T item)
	{
		super(item);
	}

	@Nonnull
	public T getItem()
	{
		return getObject();
	}

	@Nonnull
	@Override
	public ItemStack asStack(int size, int damage)
	{
		return new ItemStack(getItem(), size, damage);
	}

	public boolean equals(Item other)
	{
		if (other == null) return false;
		return getItem() == other;
	}

	/**
	 * @param name - item name
	 */
	public void register(ResourceLocation name)
	{
		getItem().setUnlocalizedName(name.getResourcePath());
		getItem().setRegistryName(name);
		GameRegistry.register(getItem());
	}
	
	public void register()
	{
		GameRegistry.register(getItem());
	}
	
    public void registerRender() {
        ModelLoader.setCustomModelResourceLocation(getItem(), 0,
                new ModelResourceLocation(getItem().getRegistryName(), "inventory"));
    }

    public void registerRender(int meta, String fileName) {
    	String modID = getItem().getRegistryName().getResourceDomain();
    	
        ModelLoader.setCustomModelResourceLocation(getItem(), meta,
                new ModelResourceLocation(new ResourceLocation(modID, fileName), "inventory"));
    }
    
    public <ET extends Enum<?> & IObjectVariant & IStringSerializable> void registerRenders(Class<ET> clazz) {
    	ET[] values = clazz.getEnumConstants();
    	ResourceLocation itemResloc = getItem().getRegistryName();
    	
    	for( ET type : values ) {
    		registerRender(type.getVariantID(), itemResloc.getResourcePath() + "_" + type.getName() );
    	}
    }
    
    public <ET extends Enum<?> & IStringSerializable> void registerModelBakeryVariants(Class<ET> clazz) {
    	ET[] values = clazz.getEnumConstants();
    	ResourceLocation itemResloc = getItem().getRegistryName();
    	ResourceLocation reslocs[] = new ResourceLocation[values.length];
    	
    	for( int i = 0; i < reslocs.length; i ++ ) {
    		ET type = values[i]; 
    		reslocs[i] = new ResourceLocation(itemResloc.getResourceDomain(), itemResloc.getResourcePath() + "_" + type.getName() );	
    	}
    	
    	ModelBakery.registerItemVariants(getItem(), reslocs);
    }
}
