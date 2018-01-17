package growthcraft.core.common.tileentity.event;

import java.util.EnumMap;
import java.util.List;

public class TileEventFunctionMap extends EnumMap<TileEventHandler.EventType, List<TileEventFunction>>
{
	public static final long serialVersionUID = 1L;

	public TileEventFunctionMap()
	{
		super(TileEventHandler.EventType.class);
	}
}
