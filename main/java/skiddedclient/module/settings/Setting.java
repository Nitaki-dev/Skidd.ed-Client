package skiddedclient.module.settings;

public class Setting {

	public String name;
	protected boolean visible = true;
	
	public Setting(String name) {
		this.name = name;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public String getName() {
		return name;
	}
}