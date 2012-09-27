package net.minecraftforge.bukkit.core.loader;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.TransformerExclusions({"net.minecraftforge.bukkit.core.loader."})
public class BukkitLoaderPlugin implements IFMLLoadingPlugin {

   @Override
   public String[] getLibraryRequestClass() {
      return null;
   }

   @Override
   public String[] getASMTransformerClass() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public String getModContainerClass() {
      return "net.minecraftforge.bukkit.core.BukkitContainer";
   }

   @Override
   public String getSetupClass() {
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public void injectData(Map<String, Object> data) {
      // TODO Auto-generated method stub

   }

}
