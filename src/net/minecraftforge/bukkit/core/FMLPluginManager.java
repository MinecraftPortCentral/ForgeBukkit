package net.minecraftforge.bukkit.core;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import org.bukkit.Server;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;

import com.google.common.collect.Lists;
import com.google.common.collect.SetMultimap;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.discovery.ASMDataTable.ASMData;

public class FMLPluginManager extends SimplePluginManager {

    public FMLPluginManager(Server instance, SimpleCommandMap commandMap) {
        super(instance, commandMap);
    }

    @Override
    protected Map<String, File> prePopulatePluginList() {
        return super.prePopulatePluginList();
    }

    @Override
    public Plugin[] loadPlugins(File directory) {
        File f = new File(directory.getParentFile(), directory.getName())
        {
            public File[] listFiles() {
                ArrayList<File> list = Lists.newArrayList(super.listFiles());
                for (ModContainer mc : Loader.instance().getActiveModList())
                {
                    if (hasBukkitPlugin(mc))
                    {
                        list.add(new File(mc.getModId()+".fmlmod"));
                    }
                }
                return list.toArray(new File[list.size()]);
            };
        };
        return super.loadPlugins(f);
    }

    protected boolean hasBukkitPlugin(ModContainer mc) {
        return getBukkitPluginClass(mc) != null;
    }

    String getBukkitPluginClass(ModContainer mc) {
        SetMultimap<String, ASMData> annotations = BukkitContainer.instance.asmData.getAnnotationsFor(mc);
        String clName = null;
        if (annotations != null)
        {
            Set<ASMData> set = annotations.get(BukkitPlugin.class.getName());
            for (ASMData dat : set)
            {
                clName = (String) dat.getAnnotationInfo().get("value");
            }
        }
        return clName;
    }
}
