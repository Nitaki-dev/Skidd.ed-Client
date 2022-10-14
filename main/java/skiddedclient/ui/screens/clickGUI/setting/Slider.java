package skiddedclient.ui.screens.clickGUI.setting;

import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import skiddedclient.Client;
import skiddedclient.module.ModuleManager;
import skiddedclient.module.render.GUI;
import skiddedclient.module.settings.NumberSetting;
import skiddedclient.module.settings.Setting;
import skiddedclient.ui.screens.clickGUI.ModuleButton;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.RenderUtils;

@SuppressWarnings("unused")
public class Slider extends Component {
	
	public static int MainColorRGB = new Color(12,12,12).getRGB();
	public static int MainColorEnabledRGB = new Color(249,125,1).getRGB();
	public static Color MainColor = new Color(12,12,12);
	public static Color MainColorEnabled = new Color(249,125,1);
	
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 18);

	public NumberSetting numSet = (NumberSetting)setting;
	
	private boolean sliding = false;
	
	public Slider(Setting setting, ModuleButton parent, int offset) {
		super(setting, parent, offset);
		this.numSet = (NumberSetting)setting;
	}

	@SuppressWarnings("static-access")
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		
		if (ModuleManager.INSTANCE.getModule(GUI.class).theme.is("Midnight")) {
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
		
		int sy = parent.parent.x,
				sx = parent.parent.y + 12,
				ey = parent.parent.x + 100,
				ex = parent.parent.y + getWidth(120);
		
		int X1 = parent.parent.x,
				X2 = parent.parent.x + parent.parent.width,
				Y1 = parent.parent.y + parent.offset + offset,
				Y2 = parent.parent.y + parent.offset + offset + parent.parent.height;
		
		double diff = Math.min(parent.parent.width, Math.max(0, mouseX - parent.parent.x));
		int renderWidth = (int) (parent.parent.width * (numSet.getValue() - numSet.getMin()) / (numSet.getMax() - numSet.getMin()));
		
		if (numSet.getMax()!=360) {
			DrawableHelper.fill(matrices, X1, Y1, parent.parent.x + parent.parent.width, Y2, MainColorRGB);
			DrawableHelper.fill(matrices, X1, Y1+2, parent.parent.x + renderWidth, Y2-2, MainColorEnabledRGB);//0xff8a00cb
			
		} else if (numSet.getMax()==360) {

			DrawableHelper.fill(matrices, X1, Y1, parent.parent.x + parent.parent.width, Y2, MainColorRGB);

			int f1=parent.parent.x+100,
					f2=parent.parent.x;
			
			for (int i = (parent.parent.x); i < (parent.parent.x+100); i++) {
				float curHue = 1f / ((float) (f1 - f2) / (i - f2));
				DrawableHelper.fill(matrices, X1, Y1, X1+2, Y2-2, MainColorRGB);
				
				DrawableHelper.fill(matrices, i, Y1+3, i+1, Y2-2, 0xff000000 | MathHelper.hsvToRgb(curHue, 1f, 1f));
				if (numSet.getValue()!=numSet.getMax() && numSet.getValue()!=numSet.getMin()) {
					DrawableHelper.fill(matrices, parent.parent.x+renderWidth-1, Y1, parent.parent.x+renderWidth+1, Y2, MainColorRGB);
				}
				
				DrawableHelper.fill(matrices, X2-2, Y1, X2, Y2-2, MainColorRGB);
//				float curHue2 = 1f/((float) (parent.parent.x+renderWidth-f2) / (i-f2));
//				DrawableHelper.fill(matrices, X1, Y1+50, X2, Y2+50, MathHelper.hsvToRgb(curHue2, 1f, 1f));
//				Client.logger.info(MathHelper.hsvToRgb(i-renderWidth, 1f, 1f));
				
			}
			
			
		}

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
	
	public int getHeight(int len) {
		return len - len / 4 - 1;
	}
	public int getWidth(int len) {
		return len - len / 4 - 1;
	}
	
	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (isHovered(mouseX, mouseY)) {
			sliding = true;
		}
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
