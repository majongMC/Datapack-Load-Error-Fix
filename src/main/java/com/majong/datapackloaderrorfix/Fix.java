package com.majong.datapackloaderrorfix;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;

public class Fix {
	public static void fix(File levelfile) {
		try {
			boolean changed=false;
			CompoundTag root = NbtIo.readCompressed(levelfile);
			CompoundTag Data = root.getCompound("Data");
			CompoundTag WorldGenSettings = Data.getCompound("WorldGenSettings");
			CompoundTag dimensions = WorldGenSettings.getCompound("dimensions");
			Set<String> keys =dimensions.getAllKeys();
			List<String> removedkeys=new ArrayList<>();
			for(String key:keys) {
				for(int i=0;i<key.length();i++) {
					if(key.charAt(i)==':') {
						String id=key.substring(0,i);
						if(!Main.isModLoaded(id)) {
							removedkeys.add(key);
						}
						break;
					}
				}
			}
			for(String s:removedkeys) {
				dimensions.remove(s);
				LogManager.getLogger().info("已移除残留的维度数据："+s);
				changed=true;
			}
			if(changed)
				NbtIo.writeCompressed(root,levelfile);
			else
				LogManager.getLogger().info("未检测到残留的维度数据");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
