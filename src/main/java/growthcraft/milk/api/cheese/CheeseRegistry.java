package growthcraft.milk.api.cheese;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nonnull;

import growthcraft.milk.GrowthcraftMilk;
import growthcraft.milk.api.definition.ICheeseType;
import net.minecraft.util.ResourceLocation;

public class CheeseRegistry {
	private Map<ResourceLocation, Integer> cheeseNameToId = new HashMap<ResourceLocation, Integer>();
	private List<ICheeseType> cheeseFromId = new ArrayList<ICheeseType>();	// Default cheese is cheese with Id 1
	
	public <ET extends Enum<?> & ICheeseType> void registerCheeses(@Nonnull Class<ET> enumClazz ) {
		ET[] values = enumClazz.getEnumConstants();
		for( ET type : values )
			registerCheese(type);
	}
	
	public void registerCheese(@Nonnull ICheeseType type) {
		registerCheese(type.getRegistryName(), type);
	}
	
	public void registerCheese(@Nonnull ResourceLocation name, @Nonnull ICheeseType type) {
		if( cheeseNameToId.containsKey(name) ) {
			GrowthcraftMilk.logger.warn("There is already a cheese with name " + name + " existing.");
			return;
		}
		
		cheeseFromId.add(type);
		int nextId = cheeseFromId.size();	// Ids begin with 1. 0 is reserved
		cheeseNameToId.put(name, nextId);
	}
	
	public int getCheeseIdFromName(@Nonnull ResourceLocation name) {
		Integer id = cheeseNameToId.get(name);
		return id != null ? id : 0;
	}
	
	public int getCheeseId(@Nonnull ICheeseType type) {
		return getCheeseIdFromName(type.getRegistryName());
	}
	
	public ICheeseType getCheeseByName(@Nonnull ResourceLocation name) {
		int id = getCheeseIdFromName(name);
		return id != 0? getCheeseById(id) : null;
	}
	
	public ICheeseType getSafeCheeseByName(@Nonnull ResourceLocation name) {
		ICheeseType type = getCheeseByName(name);
		return type != null ? type : cheeseFromId.get(0);
	}
	
	public ICheeseType getCheeseById(int id) {
		if( id <= 0 || id > cheeseFromId.size() )
			return null;
		return cheeseFromId.get(id-1);
	}
	
	public ICheeseType getSafeCheeseById(int id) {
		ICheeseType type = getCheeseById(id);
		return type != null ? type : cheeseFromId.get(0);
	}

}
