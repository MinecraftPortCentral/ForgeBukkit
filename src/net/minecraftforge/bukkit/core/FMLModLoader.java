package net.minecraftforge.bukkit.core;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPluginLoader;

import com.google.common.collect.ObjectArrays;
import com.google.common.collect.SetMultimap;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.discovery.ASMDataTable;
import cpw.mods.fml.common.discovery.ASMDataTable.ASMData;

public class FMLModLoader extends JavaPluginLoader {
    public FMLModLoader(Server server)
    {
        super(server);
    }
    @Override
    public Plugin loadPlugin(File file) throws InvalidPluginException, UnknownDependencyException {
        if (file.getName().endsWith("fmlmod"))
        {
            String modName = file.getName().substring(0, file.getName().lastIndexOf("."));
            FMLLog.info("Attempting to load bukkit plugin for mod %s", modName);
            ModContainer mc = Loader.instance().getIndexedModList().get(modName);
            return null;
        }
        else
        {
            return super.loadPlugin(file);
        }
    }

    @Override
    public PluginDescriptionFile getPluginDescription(File file) throws InvalidDescriptionException {
        if (file.getName().endsWith("fmlmod"))
        {
            String modName = file.getName().substring(0, file.getName().lastIndexOf("."));
            FMLLog.info("Scanning %s for bukkit plugin data", modName);
            ModContainer mc = Loader.instance().getIndexedModList().get(modName);
            String clName = BukkitContainer.instance.pluginManager.getBukkitPluginClass(mc);
            return new PluginDescriptionFile(mc.getModId(), mc.getVersion(), clName);
        }
        else
        {
            return super.getPluginDescription(file);
        }
    }

    @Override
    public Pattern[] getPluginFileFilters() {
        return ObjectArrays.concat(super.getPluginFileFilters(), Pattern.compile("\\.fmlmod$"));
    }
}
