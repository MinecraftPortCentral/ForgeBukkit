package net.minecraftforge.bukkit.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPluginLoader;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.discovery.ASMDataTable;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.versioning.ArtifactVersion;
import cpw.mods.fml.common.versioning.DefaultArtifactVersion;

public class BukkitContainer extends DummyModContainer
{
    private EventBus eventBus;
    private LoadController controller;

    private ServerWrapper wrapper;
    private YamlConfiguration configuration;
    private File configFile;
    FMLPluginManager pluginManager;
    private SimpleCommandMap commandMap;
    private File pluginFolder;
    ASMDataTable asmData;

    static BukkitContainer instance;

    public BukkitContainer() {
        super(new ModMetadata());
        instance = this;
        ModMetadata md = getMetadata();
        md.modId = "Bukkit";
        md.name = "Bukkit";
        md.description = "The bukkit minecraft server API";
        wrapper = new ServerWrapper();
        Bukkit.setServer(wrapper);
        md.version = Bukkit.getBukkitVersion();
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        this.eventBus = bus;
        this.controller = controller;
        eventBus.register(this);
        return true;
    }

    @Subscribe
    public void preInit(FMLPreInitializationEvent evt)
    {
        FMLLog.info("Loading Bukkit coremod");
        configFile = new File(evt.getModConfigurationDirectory(),"bukkit.yml");
        configuration = YamlConfiguration.loadConfiguration(configFile);
        configuration.options().copyDefaults(true);
        InputStream defaultConfig = getClass().getClassLoader().getResourceAsStream("/configurations/bukkit-default.yml");
        if (defaultConfig != null)
        {
            configuration.setDefaults(YamlConfiguration.loadConfiguration(defaultConfig));
        }

        try {
            configuration.save(configFile);
        } catch (IOException e) {
            FMLLog.log(Level.INFO, e, "Could not save bukkit configuration file %s", configFile.getName());
        }

        commandMap = new SimpleCommandMap(wrapper);
        pluginManager = new FMLPluginManager(wrapper, commandMap);
        pluginManager.useTimings(configuration.getBoolean("settings.plugin-profiling"));
        pluginManager.registerInterface(FMLModLoader.class);
        pluginFolder = new File(evt.getModConfigurationDirectory().getParentFile(), "plugins");

        asmData = evt.getAsmData();
    }


    @Subscribe
    public void serverStarting(FMLServerStartingEvent starting)
    {
        wrapper.initializeFor(configuration, starting.getServer());
    }

    @Override
    public String getVersion() {
        return VersionInformation.getCoremodVersion();
    }


    @Subscribe
    public void loadPlugins(FMLPostInitializationEvent evt)
    {
        FMLLog.info("Loading bukkit plugins from the %s directory", pluginFolder.getAbsolutePath());
        if (pluginFolder.exists()) {
            Plugin[] plugins = pluginManager.loadPlugins(pluginFolder);
            for (Plugin plugin : plugins) {
                try {
                    String message = String.format("Loading %s", plugin.getDescription().getFullName());
                    plugin.getLogger().info(message);
                    plugin.onLoad();
                } catch (Throwable ex) {
                    FMLLog.log(Level.SEVERE, ex, "An error occurred initializing %s (Is it up to date?)", plugin.getDescription().getFullName());
                }
            }
        } else {
            pluginFolder.mkdir();
        }

    }
}
