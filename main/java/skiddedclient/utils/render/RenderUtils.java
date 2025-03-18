package skiddedclient.utils.render;

import java.awt.Color;

import org.lwjgl.opengl.GL11;

import com.mojang.blaze3d.systems.RenderSystem;

//import df9client.mixins.WorldRendererAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Frustum;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexFormat;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import net.minecraft.util.math.Vector4f;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import skiddedclient.mixins.WorldRendererAccessor;
import skiddedclient.utils.misc.QuadColor;


public class RenderUtils {
	private static MinecraftClient mc = MinecraftClient.getInstance();

	 public static void setup3DRender(boolean disableDepth) {
	        RenderSystem.setShader(GameRenderer::getPositionColorShader);
	        RenderSystem.disableTexture();
	        RenderSystem.enableBlend();
	        RenderSystem.defaultBlendFunc();
	        if (disableDepth)
	            RenderSystem.disableDepthTest();
	        RenderSystem.depthMask(MinecraftClient.isFabulousGraphicsOrBetter());
	        RenderSystem.enableCull();
	    }
	 
	 public static void end3DRender() {
	        RenderSystem.enableTexture();
	        RenderSystem.disableCull();
	        RenderSystem.disableBlend();
	        RenderSystem.enableDepthTest();
	        RenderSystem.depthMask(true);
	        RenderSystem.setShader(GameRenderer::getPositionColorShader);
	}
	
	public static void setup2DRender(boolean disableDepth) {
	        RenderSystem.enableBlend();
	        RenderSystem.disableTexture();
	        RenderSystem.defaultBlendFunc();
	        if (disableDepth)
	            RenderSystem.disableDepthTest();
	}

	public static void end2DRender() {
	        RenderSystem.disableBlend();
	        RenderSystem.enableTexture();
	        RenderSystem.enableDepthTest();
	}

	public static MatrixStack matrixFrom(double x, double y, double z) {
		MatrixStack matrices = new MatrixStack();

		Camera camera = mc.gameRenderer.getCamera();
		matrices.multiply(Vec3f.POSITIVE_X.getDegreesQuaternion(camera.getPitch()));
		matrices.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(camera.getYaw() + 180.0F));

		matrices.translate(x - camera.getPos().x, y - camera.getPos().y, z - camera.getPos().z);

		return matrices;
	}
	
	
	public static Vec3d center() {
		Vec3d pos = new Vec3d(0, 0, 1);
		
        return new Vec3d(pos.x, -pos.y, pos.z)
            .rotateX(-(float) Math.toRadians(mc.gameRenderer.getCamera().getPitch()))
            .rotateY(-(float) Math.toRadians(mc.gameRenderer.getCamera().getYaw()))
            .add(mc.gameRenderer.getCamera().getPos());
	}
	
    public static void shaderColor(int rgb) {
        float alpha = (rgb >> 24 & 0xFF) / 255.0F;
        float red = (rgb >> 16 & 0xFF) / 255.0F;
        float green = (rgb >> 8 & 0xFF) / 255.0F;
        float blue = (rgb & 0xFF) / 255.0F;
        RenderSystem.setShaderColor(red, green, blue, alpha);
    }
	
    public static void renderRoundedQuadInternal(Matrix4f matrix, float cr, float cg, float cb, float ca, double fromX, double fromY, double toX, double toY, double rad, double samples) { 
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer(); 
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR); 
 
        double toX1 = toX - rad; 
        double toY1 = toY - rad; 
        double fromX1 = fromX + rad; 
        double fromY1 = fromY + rad; 
        double[][] map = new double[][]{new double[]{toX1, toY1}, new double[]{toX1, fromY1}, new double[]{fromX1, fromY1}, new double[]{fromX1, toY1}}; 
        for (int i = 0; i < 4; i++) { 
            double[] current = map[i]; 
            for (double r = i * 90d; r < (360 / 4d + i * 90d); r += (90 / samples)) { 
                float rad1 = (float) Math.toRadians(r); 
                float sin = (float) (Math.sin(rad1) * rad); 
                float cos = (float) (Math.cos(rad1) * rad); 
                bufferBuilder.vertex(matrix, (float) current[0] + sin, (float) current[1] + cos, 0.0F).color(cr, cg, cb, ca).next(); 
            } 
        } 
        BufferRenderer.drawWithShader(bufferBuilder.end()); 
    }
	
    public static void renderRoundedQuad(MatrixStack matrices, Color c, double fromX, double fromY, double toX, double toY, double rad, double samples) { 
        int color = c.getRGB(); 
        Matrix4f matrix = matrices.peek().getPositionMatrix(); 
        float f = (float) (color >> 24 & 255) / 255.0F; 
        float g = (float) (color >> 16 & 255) / 255.0F; 
        float h = (float) (color >> 8 & 255) / 255.0F; 
        float k = (float) (color & 255) / 255.0F; 
        RenderSystem.enableBlend(); 
        RenderSystem.disableTexture(); 
        RenderSystem.setShader(GameRenderer::getPositionColorShader); 
 
        renderRoundedQuadInternal(matrix, g, h, k, f, fromX, fromY, toX, toY, rad, samples); 
 
        RenderSystem.enableTexture(); 
        RenderSystem.disableBlend(); 
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f); 
    }
    
    public static void bindTexture(Identifier identifier) {
        RenderSystem.setShaderTexture(0, identifier);
    }

	public static double slowDownTo(double x1, double x2, float smooth) {
		return (x2 - x1) / smooth;
	}
	
	
	public static Vec3d getEntityRenderPosition(Entity entity, double partial) {
	        double x = entity.prevX + ((entity.getX() - entity.prevX) * partial) - mc.getEntityRenderDispatcher().camera.getPos().x;
	        double y = entity.prevY + ((entity.getY() - entity.prevY) * partial) - mc.getEntityRenderDispatcher().camera.getPos().y;
	        double z = entity.prevZ + ((entity.getZ() - entity.prevZ) * partial) - mc.getEntityRenderDispatcher().camera.getPos().z;
	        return new Vec3d(x, y, z);
	}
	

	public static void line(Vec3d start, Vec3d end, Color color, MatrixStack matrices) {
        float red = color.getRed() / 255f;
        float green = color.getGreen() / 255f;
        float blue = color.getBlue() / 255f;
        float alpha = color.getAlpha() / 255f;
        Camera c = mc.gameRenderer.getCamera();
        Vec3d camPos = c.getPos();
        start = start.subtract(camPos);
        end = end.subtract(camPos);
        Matrix4f matrix = matrices.peek().getPositionMatrix();
        float x1 = (float) start.x;
        float y1 = (float) start.y;
        float z1 = (float) start.z;
        float x2 = (float) end.x;
        float y2 = (float) end.y;
        float z2 = (float) end.z;
        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        GL11.glDepthFunc(GL11.GL_ALWAYS);
        
        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableBlend();
        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES,
                VertexFormats.POSITION_COLOR);
        buffer.vertex(matrix, x1, y1, z1).color(red, green, blue, alpha).next();
        buffer.vertex(matrix, x2, y2, z2).color(red, green, blue, alpha).next();

        BufferRenderer.drawWithShader(buffer.end());
        GL11.glDepthFunc(GL11.GL_LEQUAL);
        RenderSystem.disableBlend();
    }
	 
	public static double getScaleFactor() {
		return mc.getWindow().getScaleFactor();
	}

	public static int getScaledWidth() {
	    	return mc.getWindow().getScaledWidth();
	}

	public static int getScaledHeight() {
		return mc.getWindow().getScaledHeight();
	}

    private static Vec3d to2D(double x, double y, double z) {
        int displayHeight = mc.getWindow().getHeight();
        Vector3D screenCoords = new Vector3D();
        int[] viewport = new int[4];
        GL11.glGetIntegerv(GL11.GL_VIEWPORT, viewport);
        Matrix4x4 matrix4x4Proj = Matrix4x4.copyFromColumnMajor(RenderSystem.getProjectionMatrix());
        Matrix4x4 matrix4x4Model = Matrix4x4.copyFromColumnMajor(RenderSystem.getModelViewMatrix());
        matrix4x4Proj.mul(matrix4x4Model).project((float) x, (float) y, (float) z, viewport, screenCoords);

        return new Vec3d(screenCoords.x / getScaleFactor(), (displayHeight - screenCoords.y) / getScaleFactor(), screenCoords.z);
    }
    
    public static Vec3d getPos(Entity entity, float yOffset, float tickDelta, MatrixStack matrixStack) {
        Vec3d bound = getEntityRenderPosition(entity, tickDelta).add(0, yOffset, 0);
        Vector4f vector4f = new Vector4f((float)bound.x, (float)bound.y, (float)bound.z, 1.f);
        vector4f.transform(matrixStack.peek().getPositionMatrix());
        Vec3d twoD = to2D(vector4f.getX(), vector4f.getY(), vector4f.getZ());
        return new Vec3d(twoD.x, twoD.y, twoD.z);
    }
    
	public static Frustum getFrustum() {
		return ((WorldRendererAccessor) mc.worldRenderer).getFrustum();
	}
    
	 public static void drawFilledBox(MatrixStack matrixStack, Box bb, Color color, boolean draw) {
	        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
	        Color color1 = color;
	        setup3DRender(true);
	        
	        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
	        if (draw)
	        	bufferBuilder.begin(VertexFormat.DrawMode.QUADS/*QUADS*/, VertexFormats.POSITION_COLOR);
	        float minX = (float)bb.minX;
	        float minY = (float)bb.minY;
	        float minZ = (float)bb.minZ;
	        float maxX = (float)bb.maxX;
	        float maxY = (float)bb.maxY;
	        float maxZ = (float)bb.maxZ;

	        bufferBuilder.vertex(matrix4f, minX, minY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, minY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, minY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, minY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();

	        bufferBuilder.vertex(matrix4f, minX, maxY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, maxY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, maxY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, maxY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();

	        bufferBuilder.vertex(matrix4f, minX, minY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, maxY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, maxY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, minY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();

	        bufferBuilder.vertex(matrix4f, maxX, minY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, maxY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, maxY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, minY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();

	        bufferBuilder.vertex(matrix4f, minX, minY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, minY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, maxX, maxY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, maxY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();

	        bufferBuilder.vertex(matrix4f, minX, minY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, minY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, maxY, maxZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        bufferBuilder.vertex(matrix4f, minX, maxY, minZ).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        if (draw) {
		        BufferRenderer.drawWithShader(bufferBuilder.end());
	        }
	        end3DRender();
	    }
	 
	
	public static void drawBoxFill(Box box, QuadColor color, Direction... excludeDirs) {
		if (!getFrustum().isVisible(box)) {
			return;
		}

		setup3DRender(true);

		MatrixStack matrices = matrixFrom(box.minX, box.minY, box.minZ);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();

		// Fill
		RenderSystem.setShader(GameRenderer::getPositionColorShader);

		buffer.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
		Vertexer.vertexBoxQuads(matrices, buffer, Boxes.moveToZero(box), color, excludeDirs);
		tessellator.draw();

		end3DRender();
	}
	
	public static void drawBoxOutline(Box box, QuadColor color, float lineWidth, Direction... excludeDirs) {
		if (!getFrustum().isVisible(box)) {
			return;
		}

		setup3DRender(true);

		MatrixStack matrices = matrixFrom(box.minX, box.minY, box.minZ);

		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buffer = tessellator.getBuffer();

		// Outline
		RenderSystem.disableCull();
		RenderSystem.setShader(GameRenderer::getRenderTypeLinesShader);
		RenderSystem.lineWidth(lineWidth);

		buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
		Vertexer.vertexBoxLines(matrices, buffer, Boxes.moveToZero(box), color, excludeDirs);
		tessellator.draw();

		RenderSystem.enableCull();

		end3DRender();
	}
	
	
    public static void drawEntityBox(MatrixStack matrixstack, Entity entity, double x, double y, double z, Color color) {
        setup3DRender(true);
        matrixstack.translate(x, y, z);
        matrixstack.multiply(new Quaternion(new Vec3f(0, -1, 0), 0, true));
        matrixstack.translate(-x, -y, -z);

        Box bb = new Box(x - entity.getWidth() + 0.25, y, z - entity.getWidth() + 0.25, x + entity.getWidth() - 0.25, y + entity.getHeight() + 0.1, z + entity.getWidth() - 0.25);
        if (entity instanceof ItemEntity)
            bb = new Box(x - 0.15, y + 0.1f, z - 0.15, x + 0.15, y + 0.5, z + 0.15);


        drawFilledBox(matrixstack, bb, new Color(color.getRed(), color.getGreen(), color.getBlue(), 130), true);
        RenderSystem.lineWidth(1.5f);

        //drawOutlineBox(matrixstack, bb, color, true);

        end3DRender();
        matrixstack.translate(x, y, z);
        matrixstack.multiply(new Quaternion(new Vec3f(0, 1, 0), 0, true));
        matrixstack.translate(-x, -y, -z);
    }
	
	 public static void drawOutlineBox(MatrixStack matrixStack, Box bb, Color color, boolean draw) {
	        Color color1 = color;
	        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

	        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
	        if (draw)
	        	bufferBuilder.begin(VertexFormat.DrawMode.DEBUG_LINES/*LINES*/, VertexFormats.POSITION_COLOR);

	        VoxelShape shape = VoxelShapes.cuboid(bb);
	        shape.forEachEdge((x1, y1, z1, x2, y2, z2) -> {
	            bufferBuilder.vertex(matrix4f, (float)x1, (float)y1, (float)z1).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	            bufferBuilder.vertex(matrix4f, (float)x2, (float)y2, (float)z2).color(color1.getRed(), color1.getGreen(), color1.getBlue(), color1.getAlpha()).next();
	        });
	        if (draw) {
		        BufferRenderer.drawWithShader(bufferBuilder.end());
	        }
	 }
	
	 
	 

	    public static void fill(MatrixStack matrixStack, double x1, double y1, double x2, double y2, int color) {
	        Matrix4f matrix = matrixStack.peek().getPositionMatrix();
	        double j;
	        if (x1 < x2) {
	            j = x1;
	            x1 = x2;
	            x2 = j;
	        }

	        if (y1 < y2) {
	            j = y1;
	            y1 = y2;
	            y2 = j;
	        }

	        float f = (float)(color >> 24 & 255) / 255.0F;
	        float g = (float)(color >> 16 & 255) / 255.0F;
	        float h = (float)(color >> 8 & 255) / 255.0F;
	        float k = (float)(color & 255) / 255.0F;
	        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
	        RenderSystem.enableBlend();
	        RenderSystem.disableTexture();
	        RenderSystem.defaultBlendFunc();
	        RenderSystem.setShader(GameRenderer::getPositionColorShader);
	        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
	        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, k, f).next();
	        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, k, f).next();
	        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, k, f).next();
	        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, k, f).next();
	        BufferRenderer.drawWithShader(bufferBuilder.end());
	        RenderSystem.enableTexture();
	        RenderSystem.disableBlend();
	    }
	 

	    public static void fill(MatrixStack matrixStack, double x1, double y1, double x2, double y2, Color color) {
	        Matrix4f matrix = matrixStack.peek().getPositionMatrix();
	        double j;
	        if (x1 < x2) {
	            j = x1;
	            x1 = x2;
	            x2 = j;
	        }

	        if (y1 < y2) {
	            j = y1;
	            y1 = y2;
	            y2 = j;
	        }

	        float f = (float)color.getRed() / 255.0F;
	        float g = (float)color.getGreen() / 255.0F;
	        float h = (float)color.getBlue() / 255.0F;
	        float k = (float)color.getAlpha() / 255.0F;
	        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
	        RenderSystem.enableBlend();
	        RenderSystem.disableTexture();
	        RenderSystem.defaultBlendFunc();
	        RenderSystem.setShader(GameRenderer::getPositionColorShader);
	        bufferBuilder.begin(VertexFormat.DrawMode.QUADS, VertexFormats.POSITION_COLOR);
	        bufferBuilder.vertex(matrix, (float)x1, (float)y2, 0.0F).color(g, h, k, f).next();
	        bufferBuilder.vertex(matrix, (float)x2, (float)y2, 0.0F).color(g, h, k, f).next();
	        bufferBuilder.vertex(matrix, (float)x2, (float)y1, 0.0F).color(g, h, k, f).next();
	        bufferBuilder.vertex(matrix, (float)x1, (float)y1, 0.0F).color(g, h, k, f).next();
	        BufferRenderer.drawWithShader(bufferBuilder.end());
	        RenderSystem.enableTexture();
	        RenderSystem.disableBlend();
	    }
	    
		public static void fill(MatrixStack matrices, int x1, int y1, int x2, int y2, int colTop, int colBot, int colFill) {
			DrawableHelper.fill(matrices, x1, y1 + 1, x1 + 1, y2 - 1, colTop);
			DrawableHelper.fill(matrices, x1 + 1, y1, x2 - 1, y1 + 1, colTop);
			DrawableHelper.fill(matrices, x2 - 1, y1 + 1, x2, y2 - 1, colBot);
			DrawableHelper.fill(matrices, x1 + 1, y2 - 1, x2 - 1, y2, colBot);
			DrawableHelper.fill(matrices, x1 + 1, y1 + 1, x2 - 1, y2 - 1, colFill);
		}
		
		

		 public static void drawCircle(MatrixStack matrices, int xx, int yy, float radius, float width, int sides, Color color) {
			 	int sections = sides;
		        xx *= 2.0f;
		        yy *= 2.0f;
		        float theta = (float)(6.2831852 / (double)sections);
		        float p = (float)Math.cos(theta);
		        float s = (float)Math.sin(theta);
		        float x = radius *= 2.0f;
		        float y = 0.0f;
		        float lastX = x, lastY = 0;
		        for (int ii = -1; ii < sections; ++ii) {
		            Tessellator tessellator = Tessellator.getInstance();
					BufferBuilder buffer = tessellator.getBuffer();

					RenderSystem.enableBlend();
					RenderSystem.disableDepthTest();
					RenderSystem.disableCull();
					RenderSystem.setShader(GameRenderer::getRenderTypeLinesShader);
					RenderSystem.lineWidth(width);

					buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
					Vertexer.vertexLine(matrices, buffer, x + xx, yy + y, 0f, xx + lastX, yy + lastY, 0f, LineColor.single(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha()));
					tessellator.draw();

					RenderSystem.enableCull();
					RenderSystem.enableDepthTest();

					lastX = x;
					lastY = y;
		            
		            float t = x;
		            x = p * x - s * y;
		            y = s * t + p * y;
		        }
		    }

			public static void drawCircle(MatrixStack matrices, Vec3d pos, float partialTicks, double rad, double height, int color) {
		        double lastX = 0;
				double lastZ = rad;
				for (int angle = 0; angle <= 360; angle += 6) {
					float cos = MathHelper.cos((float) Math.toRadians(angle));
					float sin = MathHelper.sin((float) Math.toRadians(angle));

					double x = rad * sin;
					double z = rad * cos;
					drawLine(
							pos.x + lastX, pos.y, pos.z + lastZ,
							pos.x + x, pos.y, pos.z + z,
							LineColor.single(color), 2);

					lastX = x;
					lastZ = z;
				}
		    }
			
			public static void drawLine(double x1, double y1, double z1, double x2, double y2, double z2, LineColor color, float width) {
//				if (!isPointVisible(x1, y1, z1) && !isPointVisible(x2, y2, z2)) {
//					return;
//				}

				setup3DRender(true);

				MatrixStack matrices = matrixFrom(x1, y1, z1);

				Tessellator tessellator = Tessellator.getInstance();
				BufferBuilder buffer = tessellator.getBuffer();

				// Line
				RenderSystem.disableDepthTest();
				RenderSystem.disableCull();
				RenderSystem.setShader(GameRenderer::getRenderTypeLinesShader);
				RenderSystem.lineWidth(width);

				buffer.begin(VertexFormat.DrawMode.LINES, VertexFormats.LINES);
				Vertexer.vertexLine(matrices, buffer, 0f, 0f, 0f, (float) (x2 - x1), (float) (y2 - y1), (float) (z2 - z1), color);
				tessellator.draw();

				RenderSystem.enableCull();
				RenderSystem.enableDepthTest();
				end3DRender();
			}
			
			public static void renderOutlineRect(Entity e, Color color, MatrixStack stack) {
		        float red = color.getRed() / 255f;
		        float green = color.getGreen() / 255f;
		        float blue = color.getBlue() / 255f;
		        float alpha = color.getAlpha() / 255f;
		        Camera c = mc.gameRenderer.getCamera();
		        Vec3d camPos = c.getPos();
		        Vec3d start = e.getPos().subtract(camPos);
		        float x = (float) start.x;
		        float y = (float) start.y;
		        float z = (float) start.z;

		        double r = Math.toRadians(-c.getYaw() + 90);
		        float sin = (float) (Math.sin(r) * (e.getWidth() / 1.7));
		        float cos = (float) (Math.cos(r) * (e.getWidth() / 1.7));
		        stack.push();

		        Matrix4f matrix = stack.peek().getPositionMatrix();
		        BufferBuilder buffer = Tessellator.getInstance().getBuffer();
		        RenderSystem.setShader(GameRenderer::getPositionColorShader);
		        GL11.glDepthFunc(GL11.GL_ALWAYS);
		        RenderSystem.setShaderColor(1f, 1f, 1f, 1f);
		        RenderSystem.defaultBlendFunc();
		        RenderSystem.enableBlend();
		        buffer.begin(VertexFormat.DrawMode.DEBUG_LINES,
		                VertexFormats.POSITION_COLOR);

		        buffer.vertex(matrix, x + sin, y, z + cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x - sin, y, z - cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x - sin, y, z - cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x - sin, y + e.getHeight(), z - cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x - sin, y + e.getHeight(), z - cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x + sin, y + e.getHeight(), z + cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x + sin, y + e.getHeight(), z + cos).color(red, green, blue, alpha).next();
		        buffer.vertex(matrix, x + sin, y, z + cos).color(red, green, blue, alpha).next();

		        BufferRenderer.drawWithShader(buffer.end());
		        GL11.glDepthFunc(GL11.GL_LEQUAL);
		        RenderSystem.disableBlend();
		        stack.pop();
		    }
}
