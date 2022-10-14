package skiddedclient.ui.screens.clickGUI;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import skiddedclient.module.Mod;
import skiddedclient.module.Mod.Category;
import skiddedclient.module.render.GUI;
import skiddedclient.module.ModuleManager;
import skiddedclient.ui.screens.clickGUI.setting.ColorBox;
import skiddedclient.ui.screens.clickGUI.setting.Component;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;


public class Frame {

	public int x, y, width, height, dragX, dragY;
	public Category category;
	
	public boolean dragging, extended;
	
	public List<ModuleButton> buttons;
	
	public static int MainColorRGB = new Color(12,12,12).getRGB();
	public static int MainColorEnabledRGB = new Color(249,125,1).getRGB();
	public static Color MainColor = new Color(12,12,12);
	public static Color MainColorEnabled = new Color(249,125,1);
	
	protected MinecraftClient mc = MinecraftClient.getInstance();
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);
	protected static FontRenderer logoFont = new FontRenderer("logo.ttf", new Identifier("skiddedclient", "fonts"), 10);
	protected static FontRenderer logoFont2 = new FontRenderer("logo.ttf", new Identifier("skiddedclient", "fonts"), 20);

	public Frame(Category category, int x, int y, int width, int height) {
		this.category = category;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.dragging = false;
		this.extended = true;
		
		buttons = new ArrayList<>();
		
		int offset = height;
		for (Mod mod : ModuleManager.INSTANCE.getModulesInCategory(category)) {
			buttons.add(new ModuleButton(mod, this, offset));
			offset += height;
		}
	}
	
	int length = 0;
	float animTicks = 0;
	@SuppressWarnings("static-access")
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		
		if (ModuleManager.INSTANCE.getModule(GUI.class).theme.is("Midnight")) {
			RenderUtils.renderRoundedQuad(matrices, MainColor, x, y, x + width, y + height, ModuleManager.INSTANCE.getModule(GUI.class).rounded.getValue(), 100);
			DrawableHelper.fill(matrices, x, y+8, x + width, y + height, MainColorRGB);
			MainColorRGB = new Color(12,12,12).getRGB();
			MainColorEnabledRGB = new Color(249,125,1).getRGB();
			MainColor = new Color(12,12,12);
			MainColorEnabled = new Color(249,125,1);
		} else if (ModuleManager.INSTANCE.getModule(GUI.class).theme.is("Light")) {
			DrawableHelper.fill(matrices, x, y, x + width, y + height, new Color(0,0,0,160).getRGB());
			MainColorRGB = new Color(0,0,0,120).getRGB();
			MainColorEnabledRGB = new Color(249,125,1).getRGB();
			MainColor = new Color(0,0,0,120);
			MainColorEnabled = new Color(249,125,1);
		}
		
////		RenderUtils.renderRoundedQuad(matrices, MainColor, x, y, x + width, y + height, ModuleManager.INSTANCE.getModule(GUI.class).rounded.getValue(), 100);
//		if (this.extended) {
//			DrawableHelper.fill(matrices, x, y+8, x + width, y + height, MainColorRGB);
//
//		}
		
		int offsetY2 = (int) ((height / 2) - mc.textRenderer.fontHeight / 2);

		customFont.draw(matrices, category.name, 11 + x + offsetY2, y + offsetY2 - customFont.getStringHeight(category.name, false)/2, -1, false);
		logoFont.draw(matrices, extended ? "A" : "B", x + width - offsetY2 - 2 - mc.textRenderer.getWidth("--"), y + offsetY2, -1, false);

        if (category.name == "Movement") {
            logoFont2.draw(matrices, "C", x + offsetY2-4, y + offsetY2 - customFont.getStringHeight(category.name, false)/2+4, -1, false);
        } else if (category.name == "Combat") {
            logoFont2.draw(matrices, "F", x + offsetY2-4, y + offsetY2 - customFont.getStringHeight(category.name, false)/2+4, -1, false);
        } else if (category.name == "Render") {
            logoFont2.draw(matrices, "G", x + offsetY2-4, y + offsetY2 - customFont.getStringHeight(category.name, false)/2+4, -1, false);
        } else if (category.name == "Exploit") {
            logoFont2.draw(matrices, "E", x + offsetY2-4, y + offsetY2 - customFont.getStringHeight(category.name, false)/2+4, -1, false);
        } else if (category.name == "World") {
            logoFont2.draw(matrices, "D", x + offsetY2-4, y + offsetY2 - customFont.getStringHeight(category.name, false)/2+4, -1, false);
        }
		
		if (extended) {
			for (ModuleButton button : buttons) {
				button.render(matrices, mouseX, mouseY, delta);
			}
		}
	}
	
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (isHovered(mouseX, mouseY)) {
			if (button == 0) {
			dragging = true;
			dragX = (int) (mouseX - x);
			dragY = (int) (mouseY - y);
			} else if (button == 1) {
				extended = !extended;
			}
		}
		
		if (extended) {
			for (ModuleButton mc : buttons) {
				mc.mouseClicked(mouseX, mouseY, button);
			}
		}
	}
	
	public void mouseReleased(double mouseX, double mouseY, int button) {
		if (button == 0 && dragging == true) dragging = false;
		
		for (ModuleButton mb : buttons) {
			mb.mouseReleased(mouseX, mouseY, button);
		}
	}
	
	public boolean isHovered(double mouseX, double mouseY) {
		return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
	}
	
	public void updatePosition(double mouseX, double mouseY) {
		if (dragging) {
			x = (int) (mouseX - dragX);
			y = (int) (mouseY - dragY);
			
		}
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void updateButton() {
		int offset = height;
		
		for (ModuleButton button : buttons) {
			button.offset = offset;
			offset += height;
			
			if (button.extended) {
				for (Component component : button.components) {
					if (component.setting.isVisible()) offset += (component instanceof ColorBox ? height * (((ColorBox)component).open ? 7.5f : 1) : height);
				}
			}
		}
	}
	
	public void keyPressed(int key) {
        for (ModuleButton mb : buttons) {
            mb.keyPressed(key);
        }
    }
}
