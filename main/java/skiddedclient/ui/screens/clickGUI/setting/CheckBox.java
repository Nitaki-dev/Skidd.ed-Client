package skiddedclient.ui.screens.clickGUI.setting;

import java.awt.Color;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.render.GUI;
import skiddedclient.module.settings.BooleanSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.ui.screens.clickGUI.ModuleButton;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;

public class CheckBox extends Component {
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 18);

	private BooleanSetting boolSet = (BooleanSetting)setting;
	
	public CheckBox(Setting setting, ModuleButton parent, int offset) {
		super(setting, parent, offset);
		this.boolSet = (BooleanSetting)setting;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

		if (parent.parent.buttons.indexOf(parent) == parent.parent.buttons.size() -1) {
			if (parent.components.indexOf(this) != parent.components.size() - 1) {
				//last module but not last setting
				DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, ModuleManager.INSTANCE.getModule(GUI.class).MainColorRGB);
			} else if (parent.components.indexOf(this) == parent.components.size() -1) {
				//last module and last setting
				DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height-5, ModuleManager.INSTANCE.getModule(GUI.class).MainColorRGB);
				RenderUtils.renderRoundedQuad(matrices, ModuleManager.INSTANCE.getModule(GUI.class).MainColor, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, 3, 100);
			
			}
		} else if (parent.parent.buttons.indexOf(parent) != parent.parent.buttons.size() -1) {
			//not last module
			DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, ModuleManager.INSTANCE.getModule(GUI.class).MainColorRGB);
		}
		
		int offsetY = ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);
		
		if (boolSet.isEnabled()) {
			DrawableHelper.fill(matrices, (parent.parent.x + offsetY) + 89, (parent.parent.y + parent.offset + offset + offsetY) + 1, parent.parent.x + offsetY + 71, parent.parent.y + parent.offset + offset + offsetY + 9, new Color(249,125,1).getRGB());
			DrawableHelper.fill(matrices, (parent.parent.x + offsetY) + 87, (parent.parent.y + parent.offset + offset + offsetY) + 3, parent.parent.x + offsetY + 83, parent.parent.y + parent.offset + offset + offsetY + 7, 0xff262626);
		} if (!boolSet.isEnabled()) {
			DrawableHelper.fill(matrices, (parent.parent.x + offsetY) + 89, (parent.parent.y + parent.offset + offset + offsetY) + 1, parent.parent.x + offsetY + 71, parent.parent.y + parent.offset + offset + offsetY + 9, new Color(70, 70, 70).getRGB());
			DrawableHelper.fill(matrices, (parent.parent.x + offsetY) + 73, (parent.parent.y + parent.offset + offset + offsetY) + 3, parent.parent.x + offsetY + 77, parent.parent.y + parent.offset + offset + offsetY + 7, 0xff262626);
		}
		
		customFont.draw(matrices, "| " + boolSet.getName(), parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
		customFont.draw(matrices, "| ", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);


		super.render(matrices, mouseX, mouseY, delta);
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (isHovered(mouseX, mouseY) && button == 0) {
			boolSet.toggle();
		}
		super.mouseClicked(mouseX, mouseY, button);
	}
}