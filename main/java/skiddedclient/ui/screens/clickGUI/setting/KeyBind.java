package skiddedclient.ui.screens.clickGUI.setting;

import java.awt.Color;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.render.GUI;
import skiddedclient.module.settings.KeyBindSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.ui.screens.clickGUI.ModuleButton;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.misc.KeyUtils;
import skiddedclient.utils.render.RenderUtils;

public class KeyBind extends Component {
	
	public static int MainColorRGB = new Color(12,12,12).getRGB();
	public static int MainColorEnabledRGB = new Color(249,125,1).getRGB();
	public static Color MainColor = new Color(12,12,12);
	public static Color MainColorEnabled = new Color(249,125,1);
	
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 18);

	private KeyBindSetting binding = (KeyBindSetting)setting;
	public boolean isBinding = false;
	
	public KeyBind(Setting setting, ModuleButton parent, int offset) {
		super(setting, parent, offset);
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (isHovered(mouseX, mouseY) && button == 0) {
			binding.toggle();
			isBinding = true;
		}
		super.mouseClicked(mouseX, mouseY, button);
	}
	
	@Override
	public void keyPressed(int key) {
		if (isBinding == true) {
			parent.module.setKey(key);
			binding.setKey(key);
			isBinding = false;
		}
		if ((binding.getKey() == 256)) {
			parent.module.setKey(0);
			binding.setKey(0);
			isBinding = false;
		}
		if ((binding.getKey() == 259)) {
			parent.module.setKey(0);
			binding.setKey(0);
			isBinding = false;
		}
		super.keyPressed(key);
	}

	@SuppressWarnings("static-access")
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		
		if (ModuleManager.INSTANCE.getModule(GUI.class).theme.is("Midnight")) {				
			RenderUtils.renderRoundedQuad(matrices, MainColor, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, 3, 100);
			MainColorRGB = new Color(12,12,12).getRGB();
			MainColorEnabledRGB = new Color(249,125,1).getRGB();
			MainColor = new Color(12,12,12);
			MainColorEnabled = new Color(249,125,1);
		} else if (ModuleManager.INSTANCE.getModule(GUI.class).theme.is("Light")) {
			MainColorRGB = new Color(0,0,0,120).getRGB();
			MainColorEnabledRGB = new Color(249,125,1).getRGB();
			MainColor = new Color(0,0,0,120);
			MainColorEnabled = new Color(249,125,1);
		}
		
		if (parent.parent.buttons.indexOf(parent) == parent.parent.buttons.size() -1) {
			if (parent.components.indexOf(this) != parent.components.size() - 1) {
				//last module but not last setting
				DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, MainColorRGB);
			} else if (parent.components.indexOf(this) == parent.components.size() -1) {
				//last module and last setting
				DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height - (ModuleManager.INSTANCE.getModule(GUI.class).theme.is("Light") ? 0 : 5), MainColorRGB);
			
			}
		} else if (parent.parent.buttons.indexOf(parent) != parent.parent.buttons.size() -1) {
			//not last module
			DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, MainColorRGB);
		}
		
		int offsetY = ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);
		
		if (isBinding==false) customFont.draw(matrices, "| Keybind: " + KeyUtils.NumToKey(binding.getKey()), parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
		if (isBinding==true) customFont.draw(matrices, "| Binding...", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
		customFont.draw(matrices, "| ", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);

		super.render(matrices, mouseX, mouseY, delta);
	}
}