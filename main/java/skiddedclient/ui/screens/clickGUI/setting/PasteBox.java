package skiddedclient.ui.screens.clickGUI.setting;


import java.awt.Color;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import skiddedclient.module.settings.PasteSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.ui.screens.clickGUI.ClickGUI;
import skiddedclient.ui.screens.clickGUI.ModuleButton;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;

public class PasteBox extends Component {
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 18);

	private PasteSetting boolSet = (PasteSetting)setting;
	
	public String unChecked = "";
	
	public PasteBox(Setting setting, ModuleButton parent, int offset) {
		super(setting, parent, offset);
		this.boolSet = (PasteSetting)setting;
	}
	
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {

		if (parent.parent.buttons.indexOf(parent) == parent.parent.buttons.size() -1) {
			if (parent.components.indexOf(this) != parent.components.size() - 1) {
				//last module but not last setting
				DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, 0xff262626);
			} else if (parent.components.indexOf(this) == parent.components.size() -1) {
				//last module and last setting
				DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height-5, 0xff262626);
				RenderUtils.renderRoundedQuad(matrices, new Color(38,38,38), parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, 3, 100);
			
			}
		} else if (parent.parent.buttons.indexOf(parent) != parent.parent.buttons.size() -1) {
			//not last module
			DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, 0xff262626);
		}
		
		int offsetY = ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);

//		if (validate(Saved)) {
//			customFont.draw(matrices, "| " + boolSet.getName() +" "+ Saved, parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
//		} else if (!validate(Saved)) {
//			customFont.draw(matrices, "| Invalid HEX code", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
//		}
//		
//		if (Saved.isEmpty()) {
//			customFont.draw(matrices, "| Empty value", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
//		} else if (!boolSet.isEnabled() && !Saved.isEmpty() && !validate(Saved)) {
//			customFont.draw(matrices, "| Invalid HEX code", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
//		}
////////////////
//		if (Saved.isEmpty()) {
//			customFont.draw(matrices, "| Empty HEX value", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
//		} else if (!Saved.isEmpty()) {
//			if (validate(Saved)) {
//				customFont.draw(matrices, "| " + boolSet.getName() +" "+ Saved, parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
//			}
//		}
		customFont.draw(matrices, "| Click to set value", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
		super.render(matrices, mouseX, mouseY, delta);
	}

	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (isHovered(mouseX, mouseY) && button == 0) {
			unChecked = ClickGUI.searchBox.getText();
			if (!validate(unChecked)) mc.inGameHud.getChatHud().addMessage(Text.literal("[Skidd.ed] Invalid HEX code"));
			if (validate(unChecked)) {				
				PasteSetting.checked = unChecked.toUpperCase();
				mc.inGameHud.getChatHud().addMessage(Text.literal("[Skidd.ed] Set " + PasteSetting.checked + " as " + boolSet.getName()));
			}
		}
		super.mouseClicked(mouseX, mouseY, button);
	}

	public static boolean validate(String Saved) {
		return Saved.matches("#[0-9A-Fa-f]{6}");
	}
}