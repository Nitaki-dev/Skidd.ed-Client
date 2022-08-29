package skiddedclient.ui.screens.clickGUI;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.glfw.GLFW;

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
	public int offset;
	
    public static TextBox searchBox;

	private ClickGUI() {
		super(Text.literal("Click GUI"));
		
		frames = new ArrayList<>();
		searchBox = new TextBox(0, 0, 100, 15, "#FFFFFF");
		
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
		customFont.drawWithShadow(matrices, "ClickGUI", 3, 2, -1, false);

		searchBox.setX(100);
		searchBox.setY(0);
		searchBox.setWidth(75);
		
		searchBox.render(matrices, mouseX, mouseY, delta);
		
		for (Frame frame : frames) {
			frame.render(matrices, mouseX, mouseY, delta);
			frame.updatePosition(mouseX, mouseY);
		}
		
		super.render(matrices, mouseX, mouseY, delta);
	}
	
	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		searchBox.mouseClicked(mouseX, mouseY, button);
		
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
		searchBox.keyPressed(keyCode, scanCode, modifiers);
		if (keyCode == GLFW.GLFW_KEY_BACKSPACE && searchBox.getText().length() == 0 && searchBox.isFocused()) {
			
		}
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
	
	@Override
	public boolean charTyped(char chr, int modifiers) {
		searchBox.charTyped(chr, modifiers);
		return super.charTyped(chr, modifiers);
	}
}
