package skiddedclient.module;

import java.util.ArrayList;
import java.util.List;

import skiddedclient.module.Mod.Category;
import skiddedclient.module.combat.*;
import skiddedclient.module.exploit.*;
import skiddedclient.module.movement.*;
import skiddedclient.module.render.*;
import skiddedclient.module.world.*;


public class ModuleManager {

	public static final ModuleManager INSTANCE = new ModuleManager();
	private List<Mod> modules = new ArrayList<>();
	
	public ModuleManager() {
		addModules();
	}
	
	public List<Mod> getModules() {
		return modules;
	}
	
	public List<Mod> getEnabledModules() {
		List<Mod> enabled = new ArrayList<>();
		for (Mod module : modules) {
			if (module.isEnabled()) enabled.add(module);
		}
		
		return enabled;
	}
	
    @SuppressWarnings("unchecked")
	public <T extends Mod> T getModule(Class<T> clazz) { 
        return (T) modules.stream().filter(mod -> mod.getClass() == clazz).findFirst().orElse(null); 
    }
	
	public List<Mod> getModulesInCategory(Category category) {
		List<Mod> categoryModules = new ArrayList<>();
		
		for (Mod mod : modules) {
			if (mod.getCategory() == category) {
				categoryModules.add(mod);
			}
		}
		
		return categoryModules;
	}
	
	private void addModules() {
		modules.add(new FakeGMode());
		modules.add(new ElytraReplace());
		modules.add(new ExampleModule());
		modules.add(new ChestStealer());
		modules.add(new CrystalAura());
		modules.add(new FakePlayer());
		modules.add(new LavaBounce());
		modules.add(new TargetHud());
		modules.add(new FastClimb());
		modules.add(new Fullbright());
		modules.add(new AutoArmor());
		modules.add(new IceSpeed());
		modules.add(new AutoSoup());
		modules.add(new HoleESP());
		modules.add(new ElytraFly());
//		modules.add(new Freecam()); FIX THIS
		modules.add(new AirJump());
		modules.add(new Scaffold());
		modules.add(new Jetpack());
		modules.add(new InvWalk());
		modules.add(new BoatFly());
		modules.add(new Killaura());
		modules.add(new ClickTP());
		modules.add(new Trigger());
		modules.add(new Tracers());
		modules.add(new FastXP());
		modules.add(new Spider());
		modules.add(new NoFall());
		modules.add(new Reach());
		modules.add(new Nuker());
		modules.add(new Sprint());
		modules.add(new Flight());
		modules.add(new Strafe());
		modules.add(new Timer());
		modules.add(new Jesus());
		modules.add(new XRay());
		modules.add(new Step());
		modules.add(new ESP());
		//36 Modules
	}

	public Mod getModuleByName(String moduleName) {
		for(Mod mod : modules) {
			if ((mod.getName().trim().equalsIgnoreCase(moduleName)) || (mod.toString().trim().equalsIgnoreCase(moduleName.trim()))) {
				return mod;
			}
		}
		return null;
	}
}
