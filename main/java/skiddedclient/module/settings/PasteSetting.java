package skiddedclient.module.settings;

public class PasteSetting extends Setting {

	private boolean enabled;
	
	public static String checked = "#FFFFFF";

	public PasteSetting(String name, boolean defaultValue) {
		super(name);
		this.enabled = defaultValue;
	}

	public void toggle() {
		this.enabled = !this.enabled;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
