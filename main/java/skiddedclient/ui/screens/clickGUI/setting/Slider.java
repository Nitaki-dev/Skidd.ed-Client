package skiddedclient.ui.screens.clickGUI.setting;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.ui.screens.clickGUI.ModuleButton;
import skiddedclient.utils.font.FontRenderer;

public class Slider extends Component {
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 18);


	public NumberSetting numSet = (NumberSetting)setting;
	
	private boolean sliding = false;
	
	public Slider(Setting setting, ModuleButton parent, int offset) {
		super(setting, parent, offset);
		this.numSet = (NumberSetting)setting;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
    	
		double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.x));
		int renderWidth = (int) (parent.parent.width * (numSet.getValue() - numSet.getMin()) / (numSet.getMax() - numSet.getMin()));
		
		DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height, 0xff262626);
		DrawableHelper.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + renderWidth, parent.parent.y + parent.offset + offset + parent.parent.height, new Color(249,125,1).getRGB());//0xff8a00cb
		

		if (sliding) {
			if (diff == 0) {
				numSet.setValue(numSet.getMin());
			} else {
				numSet.setValue(roundToPlace((diff / parent.parent.width) * (numSet.getMax() - numSet.getMin()) + numSet.getMin(), 2));
			}
		}
		
		int offsetY = ((parent.parent.height / 2) - mc.textRenderer.fontHeight / 2);
		customFont.draw(matrices, "| " + numSet.getName() + ": " + roundToPlace(numSet.getValue(), 1), parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
		customFont.draw(matrices, "| ", parent.parent.x + offsetY, parent.parent.y + parent.offset + offset + offsetY-6, 0xff747474, false);
		
		super.render(matrices, mouseX, mouseY, delta);
	}
	
	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (isHovered(mouseX, mouseY)) sliding = true;
		super.mouseClicked(mouseX, mouseY, button);
	}
	
	@Override
	public void mouseReleased(double mouseX, double mouseY, int button) {
		sliding = false;
		super.mouseReleased(mouseX, mouseY, button);
	}
	 
	private double roundToPlace(double value, int place) {
		if (place < 0) {
			return value;
		}
		
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(place, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
