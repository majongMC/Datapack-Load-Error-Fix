package com.majong.datapackloaderrorfix.mixin;

import java.io.File;
import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.majong.datapackloaderrorfix.Fix;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.WorldOpenFlows;
import net.minecraft.world.level.storage.LevelResource;
import net.minecraft.world.level.storage.LevelStorageSource;

@Mixin(WorldOpenFlows.class)
public class WorldOpenFlowsMixin {
	@Shadow
	LevelStorageSource levelSource;
	@Inject(at=@At("HEAD"),method="doLoadLevel")
	public void doLoadLevel(Screen p_233146_, String p_233147_, boolean p_233148_, boolean p_233149_,CallbackInfo callbackInfo) {
		try {
			LevelStorageSource.LevelStorageAccess levelstorageaccess= this.levelSource.createAccess(p_233147_);
			File levelfile=levelstorageaccess.getLevelPath(LevelResource.LEVEL_DATA_FILE).toFile();
			levelstorageaccess.close();
			Fix.fix(levelfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
