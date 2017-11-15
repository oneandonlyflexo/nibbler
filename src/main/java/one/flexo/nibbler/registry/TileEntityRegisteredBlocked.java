package one.flexo.nibbler.registry;

import net.minecraft.tileentity.TileEntity;

public interface TileEntityRegisteredBlocked {

	Class<? extends TileEntity> getTileEntityClass();
}
