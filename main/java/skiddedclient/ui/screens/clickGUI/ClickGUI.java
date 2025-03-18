package skiddedclient.ui.screens.clickGUI;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import skiddedclient.module.Mod.Category;
import skiddedclient.utils.font.FontRenderer;

public class ClickGUI extends Screen {

	public static final ClickGUI INSTANCE = new ClickGUI();
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);

	private List<Frame> frames;
	public Frame parent;
	public int offset, anim;
	
	private ClickGUI() {
		super(Text.literal("Click GUI"));
		
		frames = new ArrayList<>();
		
		int offset = 10;
		for (Category category : Category.values()) {
			frames.add(new Frame(category, offset, 20, 100, 15));
			offset += 110;
		}
		for (Frame frame : frames) {
			frame.updatePosition(frame.x, frame.y+5);
		}
	}

	
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		customFont.drawWithShadow(matrices, "Skidded client", 3, 2, -1, false);
		for (Frame frame : frames) {
			frame.render(matrices, mouseX, mouseY, delta);
			frame.updatePosition(mouseX, mouseY);
		}
		
//		for (int i = (mouseY+12); i < (mouseY+113); i++) {
//			float curHue = 1f / ((float) ((mouseY+120) - (mouseY+12)) / (i - (mouseY+12)));
//			DrawableHelper.fill(matrices, i, mouseY, i+1, mouseY+15, 0xff000000 | MathHelper.hsvToRgb(curHue, 1f, 1f));
////			DrawableHelper.fill(matrices, mouseX, i, mouseX+15, i+1, 0xff000000 | MathHelper.hsvToRgb(curHue, 1f, 1f));
//		}
		anim++;
		super.render(matrices, mouseX, mouseY, delta);
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		
		for (Frame frame : frames) {
			frame.mouseClicked(mouseX, mouseY, button);
		}
		return super.mouseClicked(mouseX, mouseY, button);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		for (Frame frame : frames) {
			frame.mouseReleased(mouseX, mouseY, button);
		}
		return super.mouseReleased(mouseX, mouseY, button);
	}
	@Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {

        for (Frame frame : frames) {
            frame.keyPressed(keyCode);
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
	
	@Override
	public boolean mouseScrolled(double mouseX, double mouseY, double amount) {
		for (Frame frame : frames) {
			if (amount > 0) frame.setY((int) (frame.getY() + 5));
			else if (amount < 0) frame.setY((int) (frame.getY() - 5));
		}
		return super.mouseScrolled(mouseX, mouseY, amount);
	}

}
