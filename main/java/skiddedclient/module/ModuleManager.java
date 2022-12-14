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
		modules.add(new AutoRespawn());
		modules.add(new ElytraReplace());
		modules.add(new ChestStealer());
		modules.add(new BlockOutline());
		modules.add(new FakeHacker());
		modules.add(new LavaBounce());
		modules.add(new TridentPlus());
		modules.add(new FakeGMode());
//		modules.add(new ExampleModule());
		modules.add(new CrystalAura());
		modules.add(new AutoArmor());
		modules.add(new TrueSight());
		modules.add(new FakePlayer());
		modules.add(new TargetHud());
		modules.add(new FastClimb());
		modules.add(new BoatPhase());
		modules.add(new AutoWalk());
		modules.add(new BlockESP());
		modules.add(new Fullbright());
		modules.add(new AutoSoup());
		modules.add(new Surround());
		modules.add(new FastStop());
		modules.add(new ElytraFly());
		modules.add(new FastBreak());
		modules.add(new GUI());
		modules.add(new HoleESP());
		modules.add(new Velocity());
		modules.add(new AirJump());
		modules.add(new InvWalk());
		modules.add(new Jetpack());
		modules.add(new FastUse());
		modules.add(new BoatFly());
		modules.add(new Freecam());
		modules.add(new KillAura());
		modules.add(new Offhand());
		modules.add(new Criticals());
		modules.add(new ClickTP());
		modules.add(new HoleTP());
		modules.add(new NoFall());
		modules.add(new Tracers());
		modules.add(new Trigger());
		modules.add(new Scaffold());
		modules.add(new FastXP());
		modules.add(new Spider());
		modules.add(new Sprint());
		modules.add(new Speed());
		modules.add(new Reach());
		modules.add(new Flight());
		modules.add(new Nuker());
		modules.add(new Timer());
		modules.add(new Strafe());
		modules.add(new Jesus());
		modules.add(new Chams());
		modules.add(new XRay());
		modules.add(new BHop());
		modules.add(new Step());
		modules.add(new ESP());

		//kill me please
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
