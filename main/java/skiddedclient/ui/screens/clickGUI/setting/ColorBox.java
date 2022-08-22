package skiddedclient.ui.screens.clickGUI.setting;

//import java.awt.Button;
import java.awt.Color;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

import skiddedclient.module.settings.ColorSetting;
import skiddedclient.module.settings.Setting;
//import skiddedclient.ui.screens.clickGUI.Button;
import skiddedclient.ui.screens.clickGUI.ModuleButton;
import skiddedclient.utils.font.FontRenderer;
import skiddedclient.utils.render.ColorUtils;
import skiddedclient.utils.render.RenderUtils;

public class ColorBox extends Component {
	
	protected static FontRenderer fontSmall = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 16);

	private ColorSetting colorSet = (ColorSetting)setting;
	private boolean lmDown = false, rmDown = false;
	public boolean open = false;
	public float h, s, v;
	int sx, sy, ex, ey;
	
	public ColorBox(Setting setting, ModuleButton parent, int offset) {
		super(setting, parent, offset);
//		this.setting = setting;
		this.offset=offset;
		this.colorSet = (ColorSetting)setting;
		colorSet.name = colorSet.name;
		
		h = colorSet.hue;
		s = colorSet.sat;
		v = colorSet.bri;
	}

	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
//		offset = parent.offset+getHeight(parent.parent.width);
		offset=75;
		
//		
//		ffs where is the fucking offset thing
//		
		
//		System.out.println(parent.parent.height);
//		offset += /*parent.parent.height*/parent.module.getSetting().size()/parent.parent.height;
//		offset += parent.parent.height;
//		offset=parent.parent.height*parent.module.getSetting().size()-parent.offset*2;
//		offset=offset-parent.parent.height;
		colorSet.name = colorSet.name;
		sx = parent.parent.getX() + 5;
		sy = parent.parent.getY() + 4 + parent.offset +offset+ parent.parent.height + 12;
		ex = parent.parent.getX() + parent.parent.width - 17;
		ey = parent.parent.getY() + 4 + parent.offset +offset+ parent.parent.height + getHeight(parent.parent.width) + 8;

		RenderUtils.fill(matrices, parent.parent.getX(), parent.parent.getY() + parent.offset + offset + parent.parent.height, parent.parent.getX() + parent.parent.width, parent.parent.getY() + parent.offset + offset + parent.parent.height * (open ? 7 : 2), 0xff262626);
//		RenderUtils.fill(matrices, parent.parent.x, parent.parent.y + parent.offset + offset, parent.parent.x + parent.parent.width, parent.parent.y + parent.offset + offset + parent.parent.height * (open?7:2), 0xff262626);
		fontSmall.drawWithShadow(matrices, colorSet.name, (int) sx, (int) sy - 12, 0xff747474, false);
		fontSmall.drawWithShadow(matrices, "#" + colorSet.getHex().toUpperCase(), (int) sx + fontSmall.getStringWidth(colorSet.name, false) + (open ? 12 : 2), (int) sy - 12, colorSet.getRGB(), false);
		
		if (hovered((int)mouseX, (int)mouseY, sx + (int) fontSmall.getStringWidth(colorSet.name + "#" + colorSet.getHex().toUpperCase(), false) + 4, sy - 12, (int) (sx + fontSmall.getStringWidth(colorSet.name + "#" + colorSet.getHex().toUpperCase(), false) + 30), sy - 4) && !open) {
			if (rmDown) open = true;
		}
		if (!open) {
			RenderUtils.fill(matrices, sx + fontSmall.getStringWidth(colorSet.name + "#" + colorSet.getHex().toUpperCase(), false) + 4, sy - 12, sx + fontSmall.getStringWidth(colorSet.name + "#" + colorSet.getHex().toUpperCase(), false) + 30, sy - 4, colorSet.getColor().getRGB());
			
			return;
		}
		RenderUtils.fill(matrices, sx + 3 + (int)fontSmall.getStringWidth(colorSet.name + colorSet.getHex().toUpperCase(), false) + 17, sy - 4, sx + 27 + (int)fontSmall.getStringWidth(colorSet.name + colorSet.getHex().toUpperCase(), false), sy - 12, new Color(0, 0, 0, 200).getRGB());
		RenderUtils.fill(matrices, sx, sy, ex, ey, -1);
		int satColor = MathHelper.hsvToRgb(colorSet.hue, 1f, 1f);
		int red = satColor >> 16 & 255;
		int green = satColor >> 8 & 255;
		int blue = satColor & 255;

		
		RenderSystem.disableBlend();
		RenderSystem.disableTexture();
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShader(GameRenderer::getPositionColorShader);
		
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
		//Draw the color
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(ex, sy, 0).color(red, green, blue, 255).next();
		bufferBuilder.vertex(sx, sy, 0).color(red, green, blue, 0).next();
		bufferBuilder.vertex(sx, ey, 0).color(red, green, blue, 0).next();
		bufferBuilder.vertex(ex, ey, 0).color(red, green, blue, 255).next();
		tessellator.draw();

		//Draw the black stuff
		bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		bufferBuilder.vertex(ex, sy, 0).color(0, 0, 0, 0).next();
		bufferBuilder.vertex(sx, sy, 0).color(0, 0, 0, 0).next();
		bufferBuilder.vertex(sx, ey, 0).color(0, 0, 0, 255).next();
		bufferBuilder.vertex(ex, ey, 0).color(0, 0, 0, 255).next();
		tessellator.draw();

		RenderSystem.disableBlend();
		RenderSystem.enableTexture();
		
		//Set the color
		if (hovered(mouseX, mouseY, sx, sy, ex, ey) && lmDown) {
			colorSet.bri = 1f - 1f / ((float) (ey - sy) / (mouseY - sy));
			colorSet.sat = 1f / ((float) (ex - sx) / (mouseX - sx));
		}

		int briY = (int) (ey - (ey - sy) * colorSet.bri);
		int satX = (int) (sx + (ex - sx) * colorSet.sat);

		RenderUtils.fill(matrices, satX - 2, briY - 2, satX + 2, briY + 2, Color.GRAY.brighter().getRGB(), Color.WHITE.darker().getRGB(), Color.WHITE.getRGB());
		// close v
		RenderUtils.fill(matrices, sx + 3 + fontSmall.getStringWidth(colorSet.name, false), sy - 4, sx + 10 + fontSmall.getStringWidth(colorSet.name, false), sy - 12, colorSet.getColor().getRGB());

		if (hovered((int)mouseX, (int)mouseY, sx + 3 + (int)fontSmall.getStringWidth(colorSet.name, false), sy - 12, sx + 10 + (int)fontSmall.getStringWidth(colorSet.name, false), sy - 4) && open) {
			if (rmDown) open = false;
		}

		//Set hex codes
		if (hovered(mouseX, mouseY, sx + 3 + (int)fontSmall.getStringWidth(colorSet.name + colorSet.getHex().toUpperCase(), false) + 17, sy - 12, sx + 27 + (int)fontSmall.getStringWidth(colorSet.name + colorSet.getHex().toUpperCase(), false), sy - 4)) {
			if (lmDown && colorSet.getColor() != ColorUtils.hexToRgb(mc.keyboard.getClipboard())) {
				Color hexColor = ColorUtils.hexToRgb(mc.keyboard.getClipboard());
				float[] vals = colorSet.rgbToHsv(hexColor.getRed(), hexColor.getGreen(), hexColor.getBlue(), hexColor.getAlpha());
				colorSet.setHSV(vals[0], vals[1], vals[2]);
				h = vals[0];
				s = vals[1];
				v = vals[2];
			}
		}
		
		sx = ex + 5;
		ex = ex + 12;

		for (int i = sy; i < ey; i++) {
			float curHue = 1f / ((float) (ey - sy) / (i - sy));
			DrawableHelper.fill(matrices, sx, i, ex, i + 1, 0xff000000 | MathHelper.hsvToRgb(curHue, 1f, 1f));
		}

		if (hovered(mouseX, mouseY, sx, sy, ex, ey) && lmDown) {
			colorSet.hue = 1f / ((float) (ey - sy) / (mouseY - sy));
		}

		int hueY = (int) (sy + (ey - sy) * colorSet.hue);
		RenderUtils.fill(matrices, sx, hueY - 2, ex, hueY + 2, Color.GRAY.brighter().getRGB(), Color.WHITE.darker().getRGB(), Color.WHITE.getRGB());
	
		super.render(matrices, mouseX, mouseY, parent.offset + offset);
	}
	
	public int getHeight(int len) {
		return len - len / 4 - 1;
	}

	public boolean hovered(int mouseX, int mouseY, int x1, int y1, int x2, int y2) {
		return mouseX >= x1 && mouseX <= x2 && mouseY >= y1 && mouseY <= y2;
	}
	
	@Override
	public void mouseClicked(double mouseX, double mouseY, int button) {
		if (button == 0) lmDown = true;
		if (button == 1) rmDown = true;
		
		super.mouseClicked(mouseX, mouseY, button);
	}
	
	@Override
	public void mouseReleased(double mouseX, double mouseY, int button) {
		if (button == 0) lmDown = false;
		if (button == 1) rmDown = false;
		super.mouseReleased(mouseX, mouseY, button);
	}
}