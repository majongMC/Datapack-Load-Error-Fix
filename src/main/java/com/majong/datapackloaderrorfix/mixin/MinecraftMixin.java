package com.majong.datapackloaderrorfix.mixin;

import java.io.File;
import java.io.IOException;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.majong.datapackloaderrorfix.Fix;

import net.minecraft.client.Minecraft;
import net.minecraft.world.level.storage.LevelStorageSource;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Shadow
	LevelStorageSource levelSource;
	@Inject(at=@At("HEAD"),method="loadLevel")
	public void readAdditionalSaveData(String p_91201_,CallbackInfo callbackInfo) {
		 try {
			LevelStorageSource.LevelStorageAccess levelstorageaccess= this.levelSource.createAccess(p_91201_);
			File savefile=levelstorageaccess.getWorldDir().toFile();
			File levelfile=new File(savefile,"level.dat");
			levelstorageaccess.close();
			Fix.fix(levelfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
