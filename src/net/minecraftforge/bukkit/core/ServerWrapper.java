package net.minecraftforge.bukkit.core;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

import org.bukkit.GameMode;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.Warning.WarningState;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.scheduler.BukkitScheduler;

import com.avaje.ebean.config.ServerConfig;

import cpw.mods.fml.common.FMLLog;

public class ServerWrapper implements Server {
    private Server wrappedServer;

    private Logger log;

    public ServerWrapper() {
    }

    @Override
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<String> getListeningPluginChannels() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getName() {
        return "FML Bukkit Server Wrapper";
    }

    @Override
    public String getVersion() {
        return VersionInformation.getCoremodVersion();
    }

    @Override
    public String getBukkitVersion() {
        return VersionInformation.getBukkitVersion();
    }

    @Override
    public Player[] getOnlinePlayers() {
        return wrappedServer.getOnlinePlayers();
    }

    @Override
    public int getMaxPlayers() {
        return wrappedServer.getMaxPlayers();
    }

    @Override
    public int getPort() {
        return wrappedServer.getPort();
    }

    @Override
    public int getViewDistance() {
        return wrappedServer.getViewDistance();
    }

    @Override
    public String getIp() {
        return wrappedServer.getIp();
    }

    @Override
    public String getServerName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getServerId() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getWorldType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean getGenerateStructures() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getAllowEnd() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getAllowNether() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean hasWhitelist() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void setWhitelist(boolean value) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<OfflinePlayer> getWhitelistedPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reloadWhitelist() {
        // TODO Auto-generated method stub

    }

    @Override
    public int broadcastMessage(String message) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public String getUpdateFolder() {
        return "";
    }

    @Override
    public File getUpdateFolderFile() {
        return null;
    }

    @Override
    public long getConnectionThrottle() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTicksPerAnimalSpawns() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getTicksPerMonsterSpawns() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Player getPlayer(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Player getPlayerExact(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Player> matchPlayer(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PluginManager getPluginManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public BukkitScheduler getScheduler() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServicesManager getServicesManager() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<World> getWorlds() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public World createWorld(WorldCreator creator) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean unloadWorld(String name, boolean save) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean unloadWorld(World world, boolean save) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public World getWorld(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public World getWorld(UUID uid) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MapView getMap(short id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MapView createMap(World world) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reload() {
        // TODO Auto-generated method stub

    }

    @Override
    public Logger getLogger() {
        if (log == null)
        {
            log = Logger.getLogger("Bukkit");
            log.setParent(FMLLog.getLogger());
        }
        return log;
    }

    @Override
    public PluginCommand getPluginCommand(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void savePlayers() {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean dispatchCommand(CommandSender sender, String commandLine)
            throws CommandException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addRecipe(Recipe recipe) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<Recipe> getRecipesFor(ItemStack result) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Iterator<Recipe> recipeIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void clearRecipes() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resetRecipes() {
        // TODO Auto-generated method stub

    }

    @Override
    public Map<String, String[]> getCommandAliases() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getSpawnRadius() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setSpawnRadius(int value) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean getOnlineMode() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean getAllowFlight() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean useExactLoginLocation() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void shutdown() {
        // TODO Auto-generated method stub

    }

    @Override
    public int broadcast(String message, String permission) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public OfflinePlayer getOfflinePlayer(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<String> getIPBans() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void banIP(String address) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unbanIP(String address) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<OfflinePlayer> getBannedPlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<OfflinePlayer> getOperators() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GameMode getDefaultGameMode() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void setDefaultGameMode(GameMode mode) {
        // TODO Auto-generated method stub

    }

    @Override
    public ConsoleCommandSender getConsoleSender() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public File getWorldContainer() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public OfflinePlayer[] getOfflinePlayers() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Messenger getMessenger() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public HelpMap getHelpMap() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Inventory createInventory(InventoryHolder owner, int size,
            String title) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getMonsterSpawnLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getAnimalSpawnLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getWaterAnimalSpawnLimit() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isPrimaryThread() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String getMotd() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public WarningState getWarningState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void configureDbConfig(ServerConfig config) {
        // TODO Auto-generated method stub

    }

    public void initializeFor(YamlConfiguration configuration, MinecraftServer server) {
        getLogger().log(Level.FINE, "Initializing Forge Bukkit");
//        monsterSpawn = configuration.getInt("spawn-limits.monsters");
//        animalSpawn = configuration.getInt("spawn-limits.animals");
//        waterAnimalSpawn = configuration.getInt("spawn-limits.water-animals");

    }
}
