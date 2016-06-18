package mod;

import item.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import proxy.CommonProxy;

@Mod(modid = TutorialMod.modId, name = TutorialMod.name, version = TutorialMod.version, acceptedMinecraftVersions = "[1.9.4]")
public class TutorialMod {
	
	@SidedProxy(serverSide = "proxy.CommonProxy", clientSide = "proxy.ClientProxy")
	public static CommonProxy proxy;

	
	public static final String modId = "tutorial";
	public static final String name = "Tutorial Mod";
	public static final String version = "1.0.0";
	
	@Mod.Instance(modId)
	public static TutorialMod instance;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
			ModItems.init();
			System.out.println(name + " is loading");
			
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}
	


}
