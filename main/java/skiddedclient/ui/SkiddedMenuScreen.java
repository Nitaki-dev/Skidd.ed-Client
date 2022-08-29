//package df9client.ui;
//
//import com.mojang.blaze3d.systems.RenderSystem;
//
//import net.minecraft.client.MinecraftClient;
//import net.minecraft.client.gui.DrawableHelper;
//import net.minecraft.client.util.math.MatrixStack;
//import net.minecraft.util.Identifier;
//
//public class MainMenu {
//	
//	private static MinecraftClient mc = MinecraftClient.getInstance();
//
//	public static void render(MatrixStack matrices,	 float tickDelta) {
//		MenuRenderer(matrices);
//	}
//	
//	public static void MenuRenderer(MatrixStack matrices) {
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		mc.textRenderer.drawWithShadow(matrices, "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", (mc.getWindow().getScaledWidth()-mc.textRenderer.getWidth("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA")), 50+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight+mc.textRenderer.fontHeight, 0xff3c3b65);
//		
//		RenderSystem.setShaderTexture(0, new Identifier("df9client", "background.png"));
//		DrawableHelper.drawTexture(matrices, 20, 20, 0, 0, 100, 100, 100, 100);
//	}
//	
//}
/*
 * Decompiled with CFR 0.1.1 (FabricMC 57d88659).
 */
package skiddedclient.ui;

import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.util.function.Consumer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.Element;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.option.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screen.option.LanguageOptionsScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.screen.world.SelectWorldScreen;
//import net.minecraft.client.gui.screen.world.SelectWorldScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.PressableTextWidget;
import net.minecraft.client.gui.widget.TexturedButtonWidget;
import net.minecraft.client.realms.gui.screen.RealmsMainScreen;
import net.minecraft.client.realms.gui.screen.RealmsNotificationsScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.resource.language.I18n;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.StringVisitable;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import skiddedclient.utils.font.FontRenderer;

import org.jetbrains.annotations.Nullable;

@Environment(value=EnvType.CLIENT)
public class SkiddedMenuScreen
extends Screen {
    public static final Text COPYRIGHT = Text.literal("");
	protected static FontRenderer customFont = new FontRenderer("Montserrat.otf", new Identifier("skiddedclient", "fonts"), 20);
	private static MinecraftClient mc = MinecraftClient.getInstance();
    boolean SHover = false;

	public int x, y, width2, height2, dragX, dragY, offset;
	int MultiOffset = (int) customFont.getStringHeight("MultyplayerOffset", false) + 10;
	int SettingsOffset = (int) customFont.getStringHeight("MultyplayerOffset", false)*2 + 10;
	int ButtonOffset = 50;
    private static final Identifier ACCESSIBILITY_ICON_TEXTURE = new Identifier("textures/gui/accessibility.png");
    @SuppressWarnings("unused")
	private final boolean isMinceraft;
    @Nullable
    private String splashText;
//    private static final Identifier MINECRAFT_TITLE_TEXTURE = new Identifier("skiddedclient", "minecraft.png");
//    private static final Identifier EDITION_TITLE_TEXTURE = new Identifier("textures/gui/title/edition.png");
    private Screen realmsNotificationGui;
    private final boolean doBackgroundFade;
    private long backgroundFadeStart;
    @Nullable
    private DeprecationNotice deprecationNotice;

    public SkiddedMenuScreen() {
        this(false);
    }

    public SkiddedMenuScreen(boolean doBackgroundFade) {
        super(Text.translatable("narrator.screen.title"));
        this.doBackgroundFade = doBackgroundFade;
        this.isMinceraft = (double)Random.create().nextFloat() < 1.0E-4;
    }

    private boolean areRealmsNotificationsEnabled() {
        return this.client.options.getRealmsNotifications().getValue() != false && this.realmsNotificationGui != null;
    }

    @Override
    public void tick() {
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotificationGui.tick();
        }
        this.client.getRealms32BitWarningChecker().showWarningIfNeeded(this);
    }


    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean shouldCloseOnEsc() {
        return false;
    }

    @SuppressWarnings("unused")
	@Override
    protected void init() {
//        if (this.splashText == null) {
//            this.splashText = this.client.getSplashTextLoader().get();
//        }
        int i = this.textRenderer.getWidth(COPYRIGHT);
        int j = this.width - i - 2;
        int k = 24;
        int l = this.height / 4 + 48;
        this.initWidgetsNormal(l, 24);
        this.addDrawableChild(new TexturedButtonWidget(this.width-20,this.height-20, 20, 20, 0, 106, 20, ButtonWidget.WIDGETS_TEXTURE, 256, 256, button -> this.client.setScreen(new LanguageOptionsScreen((Screen)this, this.client.options, this.client.getLanguageManager())), Text.translatable("narrator.button.language")));
        this.addDrawableChild(new ButtonWidget(0, this.height-50, 100, 20, Text.translatable("menu.options"), button -> this.client.setScreen(new OptionsScreen(this, this.client.options))));
        this.addDrawableChild(new ButtonWidget(0, 0-30, 100, 20, Text.translatable("menu.quit"), button -> this.client.scheduleStop()));
        this.addDrawableChild(new TexturedButtonWidget(this.width-45,this.height-20, 20, 20, 0, 0, 20, ACCESSIBILITY_ICON_TEXTURE, 32, 64, button -> this.client.setScreen(new AccessibilityOptionsScreen(this, this.client.options)), Text.translatable("narrator.button.accessibility")));
        this.addDrawableChild(new PressableTextWidget(j, this.height - 10, i, 10, COPYRIGHT, button -> this.client.setScreen(new CreditsScreen(false, Runnables.doNothing())), this.textRenderer));
        this.client.setConnectedToRealms(false);
        if (this.client.options.getRealmsNotifications().getValue().booleanValue() && this.realmsNotificationGui == null) {
            this.realmsNotificationGui = new RealmsNotificationsScreen();
        }
        if (this.areRealmsNotificationsEnabled()) {
            this.realmsNotificationGui.init(this.client, this.width, this.height);
        }
        if (!this.client.is64Bit()) {
            this.deprecationNotice = new DeprecationNotice(this.textRenderer, MultilineText.create(this.textRenderer, (StringVisitable)Text.translatable("title.32bit.deprecation"), 350, 2), this.width / 2, l - 24);
        }
    }

    @SuppressWarnings("unused")
	private void initWidgetsNormal(int y, int spacingY) {
        this.addDrawableChild(new ButtonWidget(0, y-30, 100, 20, Text.translatable("menu.singleplayer"), button -> this.client.setScreen(new SelectWorldScreen(this))));
        boolean bl = this.client.isMultiplayerEnabled();
        ButtonWidget.TooltipSupplier tooltipSupplier = bl ? ButtonWidget.EMPTY : new ButtonWidget.TooltipSupplier(){
            private final Text MULTIPLAYER_DISABLED_TEXT = Text.translatable("title.multiplayer.disabled");

            @Override
            public void onTooltip(ButtonWidget buttonWidget, MatrixStack matrixStack, int i, int j) {
                if (!buttonWidget.active) {
                	SkiddedMenuScreen.this.renderOrderedTooltip(matrixStack, SkiddedMenuScreen.this.client.textRenderer.wrapLines(this.MULTIPLAYER_DISABLED_TEXT, Math.max(SkiddedMenuScreen.this.width / 2 - 43, 170)), i, j);
                }
            }

            @Override
            public void supply(Consumer<Text> consumer) {
                consumer.accept(this.MULTIPLAYER_DISABLED_TEXT);
            }
        };
        this.addDrawableChild(new ButtonWidget(0, (int)(y + spacingY * 1)-30, 100, 20, Text.translatable("menu.multiplayer"), button -> MinecraftClient.getInstance().setScreen(new MultiplayerScreen(this))));
        this.addDrawableChild(new ButtonWidget(0, (int)(y + spacingY * 2)-30, 100, 20, Text.translatable("menu.online"), button ->MinecraftClient.getInstance().setScreen(new MultiplayerScreen(this))));
//        this.addDrawableChild(new ButtonWidget((int)(this.width / 2 - 100), (int)(y + spacingY * 1), (int)200, (int)20, (Text)Text.translatable((String)"menu.multiplayer"), (ButtonWidget.PressAction)(ButtonWidget.PressAction)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/client/gui/widget/ButtonWidget;)V, onMultiplayerButtonPressed(net.minecraft.client.gui.widget.ButtonWidget ), (Lnet/minecraft/client/gui/widget/ButtonWidget;) ((TitleScreen)this), (ButtonWidget.TooltipSupplier)tooltipSupplier)).active = bl;
//        this.addDrawableChild(new ButtonWidget((int)(this.width / 2 - 100), (int)(y + spacingY * 2), (int)200, (int)20, (Text)Text.translatable((String)"menu.online"), (ButtonWidget.PressAction)(ButtonWidget.PressAction)LambdaMetafactory.metafactory(null, null, null, (Lnet/minecraft/client/gui/widget/ButtonWidget;)V, onRealmsButtonPress(net.minecraft.client.gui.widget.ButtonWidget ), (Lnet/minecraft/client/gui/widget/ButtonWidget;)V)((TitleScreen)this), (ButtonWidget.TooltipSupplier)tooltipSupplier)).active = bl;
    }

    private void switchToRealms() {
        this.client.setScreen(new RealmsMainScreen(this));
    }

    @SuppressWarnings("unused")
	@Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        if (this.backgroundFadeStart == 0L && this.doBackgroundFade) {
            this.backgroundFadeStart = Util.getMeasuringTimeMs();
        }
        float f = this.doBackgroundFade ? (float)(Util.getMeasuringTimeMs() - this.backgroundFadeStart) / 1000.0f : 1.0f;
        int i = 274;
        int j = this.width / 2 - 137;
        int k = 30;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, new Identifier("skiddedclient", "menu.png"));
		//DrawableHelper.drawTexture(matrices, 20, 20, 0, 0, 100, 100, 100, 100);
        RenderSystem.enableBlend();
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, this.doBackgroundFade ? (float)MathHelper.ceil(MathHelper.clamp(f, 0.0f, 1.0f)) : 1.0f);
        SkiddedMenuScreen.drawTexture(matrices, 0, 0, this.width, this.height, 0.0f, 0.0f, 16, 128, 16, 128);
        float g = this.doBackgroundFade ? MathHelper.clamp(f - 1.0f, 0.0f, 1.0f) : 1.0f;
        int l = MathHelper.ceil(g * 255.0f) << 24;
        if ((l & 0xFC000000) == 0) {
            return;
        }
        
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        customFont.draw(matrices, "Nitaki", 1, mc.getWindow().getScaledHeight()-16, -1, false);
        if (this.deprecationNotice != null) {
            this.deprecationNotice.render(matrices, l);
        }

        String string = "Minecraft " + SharedConstants.getGameVersion().getName();
        if (MinecraftClient.getModStatus().isModded()) {
            string = string + I18n.translate("menu.modded", new Object[0]);
        }
        for (Element element : this.children()) {
            if (!(element instanceof ClickableWidget)) continue;
            ((ClickableWidget)element).setAlpha(g);
        }
        

//        if (SinglePlayerHovered(mouseX, mouseY)) {
//            customFont.draw(matrices, "Singleplayer", 5, this.height/2-ButtonOffset, 0xff0092ff, false);
//            customFont.draw(matrices, "____________", 5, this.height/2+3-ButtonOffset, 0xff0092ff, false);
//        } else {
//            customFont.draw(matrices, "Singleplayer", 5, this.height/2-ButtonOffset, -1, false);
//            customFont.draw(matrices, "____________", 5, this.height/2+3-ButtonOffset, -1, false);
//        }
//        
//        if (MultiPlayerHovered(mouseX, mouseY)) {
//            customFont.draw(matrices, "Multiplayer", 5, this.height/2+MultiOffset-ButtonOffset, 0xff0092ff, false);
//            customFont.draw(matrices, "____________", 5, this.height/2+3+MultiOffset-ButtonOffset, 0xff0092ff, false);
//        } else {
//            customFont.draw(matrices, "Multiplayer", 5, this.height/2+MultiOffset-ButtonOffset, -1, false);
//            customFont.draw(matrices, "____________", 5, this.height/2+3+MultiOffset-ButtonOffset, -1, false);
//        }
//        
//        if (SettingsHovered(mouseX, mouseY)) {
//            customFont.draw(matrices, "Settings", 5, this.height/2+SettingsOffset-ButtonOffset, 0xff0092ff, false);
//            customFont.draw(matrices, "____________", 5, this.height/2+3+SettingsOffset-ButtonOffset, 0xff0092ff, false);
//        } else {
//            customFont.draw(matrices, "Settings", 5, this.height/2+SettingsOffset-ButtonOffset, -1, false);
//            customFont.draw(matrices, "____________", 5, this.height/2+3+SettingsOffset-ButtonOffset, -1, false);
//        }
//        SettingsOffset
        super.render(matrices, mouseX, mouseY, delta);
        if (this.areRealmsNotificationsEnabled() && g >= 1.0f) {
            this.realmsNotificationGui.render(matrices, mouseX, mouseY, delta);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
//		if (button == 0) {
//			if(SinglePlayerHovered(mouseX, mouseY)) this.client.setScreen(new SelectWorldScreen(this));
//			if(MultiPlayerHovered(mouseX, mouseY)) MinecraftClient.getInstance().setScreen(new MultiplayerScreen(this));
//			if(SettingsHovered(mouseX, mouseY)) this.client.setScreen(new OptionsScreen(this, this.client.options));
//		}
        if (super.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return this.areRealmsNotificationsEnabled() && this.realmsNotificationGui.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void removed() {
        if (this.realmsNotificationGui != null) {
            this.realmsNotificationGui.removed();
        }
    }

    @SuppressWarnings("unused")
	private /* synthetic */ void onRealmsButtonPress(ButtonWidget button) {
        this.switchToRealms();
    }

    @SuppressWarnings("unused")
	private /* synthetic */ void onMultiplayerButtonPressed(ButtonWidget button) {
        Screen screen = this.client.options.skipMultiplayerWarning ? new MultiplayerScreen(this) : new MultiplayerWarningScreen(this);
        this.client.setScreen(screen);
    }

    @Environment(value=EnvType.CLIENT)
    record DeprecationNotice(TextRenderer textRenderer, MultilineText label, int x, int y) {
        public void render(MatrixStack matrices, int color) {
            this.label.fillBackground(matrices, this.x, this.y, this.textRenderer.fontHeight, 2, 0x55200000);
            this.label.drawCenterWithShadow(matrices, this.x, this.y, this.textRenderer.fontHeight, 0xFFFFFF | color);
        }
    }
    
//	public boolean SinglePlayerHovered(double mouseX, double mouseY) {
//		return mouseX > 0 && mouseX < customFont.getStringWidth("SinglePlayer",false)+5 && mouseY > this.height/2+4-ButtonOffset && mouseY < this.height/2+customFont.getStringHeight("SinglePlayer", false)+6-ButtonOffset;
//	}
//	public boolean MultiPlayerHovered(double mouseX, double mouseY) {
//		return mouseX > 0 && mouseX < customFont.getStringWidth("MultyPlayer",false)+5 && mouseY > this.height/2+4+MultiOffset-ButtonOffset && mouseY < this.height/2+customFont.getStringHeight("MultyPlayer", false)+6+MultiOffset-ButtonOffset;
//	}
//	public boolean SettingsHovered(double mouseX, double mouseY) {
//		return mouseX > 0 && mouseX < customFont.getStringWidth("MultyPlayer",false)+5 && mouseY > this.height/2+4+SettingsOffset-ButtonOffset && mouseY < this.height/2+customFont.getStringHeight("MultyPlayer", false)+6+SettingsOffset-ButtonOffset;
//	}
}

