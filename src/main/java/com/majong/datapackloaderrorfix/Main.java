package com.majong.datapackloaderrorfix;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.moddiscovery.ModInfo;

@Mod("datapackloaderrorfix")
public class Main {
	public static final String MOD_ID="datapackloaderrorfix";
	public static boolean isModLoaded(String modid) {
		for(ModInfo info :FMLLoader.getLoadingModList().getMods()){
			if(info.getModId().equals(modid))
				return true;
		}
		return false;
	}
}
