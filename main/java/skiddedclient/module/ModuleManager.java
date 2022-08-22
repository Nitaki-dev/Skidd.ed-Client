package skiddedclient.module;

import java.util.ArrayList;
import java.util.List;

import skiddedclient.module.Mod.Category;
import skiddedclient.module.combat.*;
import skiddedclient.module.movement.*;
import skiddedclient.module.render.*;


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
		modules.add(new ExampleModule());
		modules.add(new CrystalAura());
		modules.add(new FakePlayer());
		modules.add(new Killaura());
		modules.add(new ClickTP());
		modules.add(new Tracers());
		modules.add(new Sprint());
		modules.add(new Flight());
		modules.add(new XRay());
		modules.add(new ESP());
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
