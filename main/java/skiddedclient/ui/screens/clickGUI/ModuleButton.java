 package skiddedclient.ui.screens.clickGUI;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import skiddedclient.module.Mod;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.render.GUI;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.ColorSetting;
import skiddedclient.module.settings.KeyBindSetting;
import skiddedclient.module.settings.ModeSetting;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.ui.screens.clickGUI.setting.CheckBox;
import skiddedclient.ui.screens.clickGUI.setting.ColorBox;
import skiddedclient.ui.screens.clickGUI.setting.Component;
import skiddedclient.ui.screens.clickGUI.setting.KeyBind;
import skiddedclient.ui.screens.clickGUI.setting.ModeBox;
import skiddedclient.ui.screens.clickGUI.setting.Slider;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;


public class ModuleButton {
	private static MinecraftClient mc = MinecraftClient.getInstance();

	public Mod module;
	public Frame parent;
	public int offset;
	public List<Component> components;
	public boolean extended;
	float speed;
	
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 18);


	public ModuleButton(Mod module, Frame parent, int offset) {
		this.module = module;
		this.parent = parent;
		this.offset = offset;
		this.extended = false;
		this.components = new ArrayList<>();

		int setOffset = parent.height;
		for (Setting setting : module.getSetting()) {
			if (setting instanceof BooleanSetting) {
				components.add(new CheckBox(setting, this, setOffset));
			} else if (setting instanceof ModeSetting) {
				components.add(new ModeBox(setting, this, setOffset));
			} else if (setting instanceof NumberSetting) {
				components.add(new Slider(setting, this, setOffset));
			} else if (setting instanceof KeyBindSetting) {
				components.add(new KeyBind(setting, this, setOffset));
			} else if (setting instanceof ColorSetting) {
				components.add(new ColorBox(setting, this, setOffset));
			}
			setOffset += parent.height;
		}
	}
	
	@SuppressWarnings({ "unused", "static-access" })
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		int sWidth = mc.getWindow().getScaledWidth();
		int sHeight = mc.getWindow().getScaledHeight();
		
		int offsetY = ((parent.height / 2) - parent.mc.textRenderer.fontHeight / 2);
		
		if (parent.buttons.indexOf(this) != parent.buttons.size() - 1) {
			DrawableHelper.fill(matrices, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, module.isEnabled() ? ModuleManager.INSTANCE.getModule(GUI.class).MainColorEnabledRGB : ModuleManager.INSTANCE.getModule(GUI.class).MainColorRGB);
		}

		if ((parent.buttons.indexOf(this) == parent.buttons.size() - 1) && !extended) {
			DrawableHelper.fill(matrices, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height-5, module.isEnabled() ? ModuleManager.INSTANCE.getModule(GUI.class).MainColorEnabledRGB : ModuleManager.INSTANCE.getModule(GUI.class).MainColorRGB);
			RenderUtils.renderRoundedQuad(matrices, module.isEnabled() ? ModuleManager.INSTANCE.getModule(GUI.class).MainColorEnabled : ModuleManager.INSTANCE.getModule(GUI.class).MainColor, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, 3, 100);
		} else if ((parent.buttons.indexOf(this) == parent.buttons.size() - 1) && extended) {
			DrawableHelper.fill(matrices, parent.x, parent.y + offset, parent.x + parent.width, parent.y + offset + parent.height, module.isEnabled() ? ModuleManager.INSTANCE.getModule(GUI.class).MainColorEnabledRGB : ModuleManager.INSTANCE.getModule(GUI.class).MainColorRGB);
		}

		customFont.draw(matrices, module.getName(), parent.x + offsetY+speed, parent.y + offset + offsetY-6, module.isEnabled() ? 0xffffffff : isHovered(mouseX, mouseY) ? 0xffffff :0xffc4c4c4, false);
		
		if (extended) {
			for (Component component : components) {
				component.render(matrices, mouseX, mouseY, delta);
			}
		}
		
		if (isHovered(mouseX, mouseY)) {
			DrawableHelper.fill(matrices, 0, sHeight, (int) customFont.getStringWidth(module.getDescription(), false)+5, sHeight - (int)customFont.getStringHeight("|", false)-3, 0x70000000);
			customFont.drawWithShadow(matrices, module.getDescription(), 2, sHeight - customFont.getStringHeight("|", false)-1, -1, false);

			speed+=0.5;
		} else {
			if (speed>0)speed-=0.5;
		}
		
		if (speed>4.0)speed=4;
		
		
		

	}
	
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if(isHovered(mouseX, mouseY)) {
			if (button == 0) {
				module.toggle();
			} else if (button == 1) {
				extended = !extended;
				parent.updateButton();
			}
		}
		if (extended) {
			for (Component component : components) {
				component.mouseClicked(mouseX, mouseY, button);
			}
		}
	}
	
	public void mouseReleased(double mouseX, double mouseY, int button) {
		for (Component component : components) {
			component.mouseReleased(mouseX, mouseY, button);
		}
	}
	
	public boolean isHovered(double mouseX, double mouseY) {
		return mouseX > parent.x && mouseX < parent.x + parent.width && mouseY > parent.y + offset && mouseY < parent.y + offset + parent.height;
	}

	public void keyPressed(int key) {
        for (Component component : components) {
            component.keyPressed(key);
        }
    }
	
	public int getX() {
		return parent.x;
	}

	public void setX(int x) {
		this.parent.x = x;
	}

	public int getY() {
		return parent.y;
	}

	public void setY(int y) {
		this.parent.y = y;
	}
	public int getWidth() {
		return parent.width;
	}

	public void setWidth(int width) {
		this.parent.width = width;
	}

	public int getHeight() {
		return parent.height;
	}

	public void setHeight(int height) {
		this.parent.height = height;
	}

	public boolean isExtended() {
		return extended;
	}

	public void setExtended(boolean extended) {
		this.extended = extended;
	}
}
