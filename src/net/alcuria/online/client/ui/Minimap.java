package net.alcuria.online.client.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import net.alcuria.online.client.Config;
import net.alcuria.online.client.Device;
import net.alcuria.online.client.Map;
import net.alcuria.online.client.Player;
import net.alcuria.online.client.screens.Field;

/**
 * Imported from game source
 */
public class Minimap {
    private int camOffsetX = 0;
    private int camOffsetY = 0;

    private int nameOffsetY = 0;
    private Texture minimap;
    private TextureRegion minimapRegion;
    private TextureRegion player;
    private TextureRegion otherPlayer;
    private TextureRegion lightFrame;
    private TextureRegion darkFrame;
    private TextureRegion brownFrame;
    private TextureRegion monster;
    private Pixmap pixmap;
    public static final int MAX_WIDTH = 60;
    public static final int MAX_HEIGHT = 45;
    public static int mapOffsetX;
    public static int mapOffsetY;
    private int minimapCameraX = 0;
    private int minimapCameraY = 0;

    public int minimapWidth = 0;
    public int minimapHeight = 0;
    private Rectangle crosshair;
    private float crosshairX;
    private float crosshairY;
    private boolean crosshairActive = false;
    private float crosshairRemoveTimer = 0.0F;
    public static final int PIXMAP_SIZE = 256;

    public Minimap(Map m, AssetManager assets) {
        this.crosshair = new Rectangle(0.0F, 0.0F, 64.0F, 64.0F);

        this.pixmap = new Pixmap(256, 256, Pixmap.Format.RGB888);

        for (int i = 0; i < m.width; i++) {
            for (int j = 0; j < m.height; j++) {
                if ((m.collisionLayer[i][j] > 0) && (m.lowerLayer[i][j] < 255)) {
                    if (m.collisionLayer[i][j] == 20) {
                        this.pixmap.setColor(CustomColors.DARK_BLUE);
                    } else if ((m.collisionLayer[i][j] >= 30) && (m.collisionLayer[i][j] <= 33)) {
                        this.pixmap.setColor(CustomColors.MED_BLUE);
                    } else if (m.collisionLayer[i][j] == 36) {
                        this.pixmap.setColor(CustomColors.RED);
                    } else {
                        this.pixmap.setColor(CustomColors.BLACK);
                    }
                } else {
                    this.pixmap.setColor(CustomColors.MED_BLUE);
                }
                this.pixmap.drawPixel(i, j);
            }

        }

        this.pixmap.setColor(new Color(0.07843138F, 0.04705882F, 0.1098039F, 1.0F));
        this.pixmap.drawPixel(0, 255);
        this.pixmap.drawPixel(0, 254);
        this.pixmap.drawPixel(0, 253);
        this.pixmap.drawPixel(0, 252);

        this.pixmap.drawPixel(2, 255);
        this.pixmap.drawPixel(2, 254);
        this.pixmap.drawPixel(2, 253);
        this.pixmap.drawPixel(2, 252);

        this.pixmap.drawPixel(1, 255);
        this.pixmap.drawPixel(1, 252);
        this.pixmap.setColor(new Color(0.854902F, 0.8313726F, 0.3686275F, 1.0F));
        this.pixmap.drawPixel(1, 254);
        this.pixmap.drawPixel(1, 253);

        this.pixmap.setColor(new Color(0.07843138F, 0.04705882F, 0.1098039F, 1.0F));
        this.pixmap.drawPixel(3, 255);
        this.pixmap.drawPixel(3, 254);
        this.pixmap.drawPixel(3, 253);
        this.pixmap.drawPixel(3, 252);

        this.pixmap.drawPixel(5, 255);
        this.pixmap.drawPixel(5, 254);
        this.pixmap.drawPixel(5, 253);
        this.pixmap.drawPixel(5, 252);

        this.pixmap.drawPixel(4, 255);
        this.pixmap.drawPixel(4, 252);
        this.pixmap.setColor(new Color(0.8156863F, 0.2745098F, 0.282353F, 1.0F));
        this.pixmap.drawPixel(4, 254);
        this.pixmap.drawPixel(4, 253);

        this.pixmap.setColor(new Color(0.5215687F, 0.2980392F, 0.188235F, 1.0F));
        this.pixmap.drawPixel(6, 255);
        this.pixmap.setColor(new Color(0.8235294F, 0.4901961F, 0.172549F, 1.0F));
        this.pixmap.drawPixel(7, 255);
        this.pixmap.setColor(new Color(0.2666667F, 0.1411765F, 0.2039216F, 1.0F));
        this.pixmap.drawPixel(8, 255);

        this.pixmap.setColor(new Color(0.07843138F, 0.04705882F,
        0.1098039F, 1.0F));
        this.pixmap.drawPixel(9, 254);
        this.pixmap.drawPixel(11, 254);
        this.pixmap.drawPixel(10, 255);
        this.pixmap.drawPixel(10, 253);
        this.pixmap.setColor(new Color(0.2039216F, 0.3960784F,
        0.1411765F, 1.0F));
        this.pixmap.drawPixel(10, 254);

        this.minimap = new Texture(this.pixmap);

        this.minimapRegion = new TextureRegion(this.minimap);
        this.player = new TextureRegion(this.minimap, 0, 252, 3, 4);
        this.otherPlayer = new TextureRegion(this.minimap, 3, 252, 3, 4);
        this.darkFrame = new TextureRegion(this.minimap, 6, 255, 1, 1);
        this.lightFrame = new TextureRegion(this.minimap, 7, 255, 1, 1);
        this.brownFrame = new TextureRegion(this.minimap, 8, 255, 1, 1);
        this.monster = new TextureRegion(this.minimap, 9, 253, 3, 3);

        this.minimapWidth = (m.width > 60 ? 60 : m.width);
        this.minimapHeight = (m.height > 45 ? 45 : m.height);
        mapOffsetX = 400 - this.minimapWidth - 12;
        mapOffsetY = 225 - this.minimapHeight - 12;
    }

    public void update(Field f) {
        if (f.cameraManager != null) {
            this.camOffsetX = f.cameraManager.offsetX;
            this.camOffsetY = f.cameraManager.offsetY;
        }

        if (f.worldMap.opacity == 0.0F) {
            if ((f.inputs.pressing[18]) || (f.inputs.rightAxisX > 0.3D)) {
                checkForCrosshairAdd(f);
                if (this.crosshairX < this.minimapWidth - 1) {
                    if (Device.id != 0) {
                        f.inputs.rightAxisX = 1.0F;
                    }
                    this.crosshairX += f.inputs.rightAxisX;
                    this.crosshair.x += 16.0F * f.inputs.rightAxisX;
                }
            }

            if ((f.inputs.pressing[17]) || (f.inputs.rightAxisX < -0.3D)) {
                checkForCrosshairAdd(f);
                if (this.crosshairX > 0.0F) {
                    if (Device.id != 0) {
                        f.inputs.rightAxisX = -1.0F;
                    }
                    this.crosshairX += f.inputs.rightAxisX;
                    this.crosshair.x += 16.0F * f.inputs.rightAxisX;
                }
            }
            if ((f.inputs.pressing[15]) || (f.inputs.rightAxisY < -0.3D)) {
                checkForCrosshairAdd(f);
                if (this.crosshairY < this.minimapHeight - 2) {
                    if (Device.id != 0) {
                        f.inputs.rightAxisY = -1.0F;
                    }
                    this.crosshairY -= f.inputs.rightAxisY;
                    this.crosshair.y -= 16.0F * f.inputs.rightAxisY;
                }
            }
            if ((f.inputs.pressing[16]) || (f.inputs.rightAxisY > 0.3D)) {
                checkForCrosshairAdd(f);
                if (this.crosshairY > 1.0F) {
                    if (Device.id != 0) {
                        f.inputs.rightAxisY = 1.0F;
                    }
                    this.crosshairY -= f.inputs.rightAxisY;
                    this.crosshair.y -= 16.0F * f.inputs.rightAxisY;
                }
            }
        }
        f.inputs.rightAxisX = 0.0F;
        f.inputs.rightAxisY = 0.0F;
        if (this.crosshairActive) {
            checkForCrosshairRemove(f.player);
        } else {
            this.crosshair.x = f.player.bounds.x;
            this.crosshair.y = f.player.bounds.y;
        }

        if (this.crosshairActive) {
            this.crosshairX = ((int) ((this.crosshair.x + this.crosshair.width / 2.0F) / 16.0F) - 1 - this.minimapCameraX);
            this.crosshairY = ((int) ((this.crosshair.y + this.crosshair.height / 2.0F) / 16.0F) - 1 + this.minimapHeight - f.map.height + this.minimapCameraY);
        }

        this.minimapCameraX = ((int) ((this.crosshair.x + this.crosshair.width / 2.0F) / 16.0F) - 30);
        this.minimapCameraY = (f.map.height - (int) ((this.crosshair.y + this.crosshair.height / 2.0F) / 16.0F) - 22);

        if (this.minimapCameraX < 0) {
            this.minimapCameraX = 0;
        }
        if (this.minimapCameraX + this.minimapWidth > f.map.width) {
            this.minimapCameraX = (f.map.width - this.minimapWidth);
        }

        if (this.minimapCameraY < 0) {
            this.minimapCameraY = 0;
        }
        if (this.minimapCameraY + this.minimapHeight > f.map.height) {
            this.minimapCameraY = (f.map.height - this.minimapHeight);
        }

        f.player.minimapX = ((int) ((f.player.bounds.x + f.player.bounds.width / 2.0F) / 16.0F) - 1 - this.minimapCameraX);
        f.player.minimapY = ((int) ((f.player.bounds.y + f.player.bounds.height / 2.0F) / 16.0F) - 1 + this.minimapHeight - f.map.height + this.minimapCameraY);

        for (Player p : f.players) {
            p.minimapX = ((int) ((p.desiredBounds.x + p.desiredBounds.width / 2.0F) / 16.0F) - 1 - this.minimapCameraX);
            p.minimapY = ((int) ((p.desiredBounds.y + p.desiredBounds.height / 2.0F) / 16.0F) - 1 + this.minimapHeight - f.map.height + this.minimapCameraY);
        }

        if ((f.map.containsEnemies) && (f.map.collisions.enemies != null)) {
            for (int i = 0; i < f.map.collisions.enemies.length; i++) {
                if ((f.map.collisions.enemies[i] != null) && (f.map.collisions.enemies[i].HP.intValue() > 0) && (f.map.collisions.enemies[i].visible)) {
                    f.map.collisions.enemies[i].minimapX = ((int) ((f.map.collisions.enemies[i].desiredBounds.x + f.map.collisions.enemies[i].desiredBounds.width / 2.0F) / 16.0F) - 1 - this.minimapCameraX);
                    f.map.collisions.enemies[i].minimapY = ((int) ((f.map.collisions.enemies[i].desiredBounds.y + f.map.collisions.enemies[i].desiredBounds.height / 2.0F) / 16.0F) - 1 + this.minimapHeight - f.map.height + this.minimapCameraY);
                }
            }
        }

        this.minimapRegion.setRegion(this.minimapCameraX, this.minimapCameraY, this.minimapWidth,
        this.minimapHeight);
    }

    private void checkForCrosshairRemove(Player p) {
        if (this.crosshairRemoveTimer > 1.5D) {
            if (this.crosshairRemoveTimer > 2.1D) {
                this.crosshairActive = false;
            } else {
                interpolate(0.1F, p);
                this.crosshairRemoveTimer += Gdx.graphics.getDeltaTime();
            }
        } else {
            this.crosshairRemoveTimer += Gdx.graphics.getDeltaTime();
        }
    }

    public void interpolate(float amount, Player p) {
        if ((this.crosshair != null) && (p != null)) {
            this.crosshair.x += (p.bounds.x - this.crosshair.x) * amount;
            this.crosshair.y += (p.bounds.y - this.crosshair.y) * amount;
        }
    }

    private void checkForCrosshairAdd(Field f) {
        if (!this.crosshairActive) {
            this.crosshair.x = f.player.bounds.x;
            this.crosshair.y = f.player.bounds.y;
            this.crosshairX = ((int) ((this.crosshair.x + this.crosshair.width / 2.0F) / 16.0F) - 1 - this.minimapCameraX);
            this.crosshairY = ((int) ((this.crosshair.y + this.crosshair.height / 2.0F) / 16.0F) - 1 + this.minimapHeight - f.map.height + this.minimapCameraY);
        }
        this.crosshairRemoveTimer = 0.0F;
        this.crosshairActive = true;
    }

    public void render(Field f, SpriteBatch batch) {
        if ((this.minimapRegion != null) && (!net.alcuria.online.client.GlobalFlags.flags[3])) {
            batch.draw(this.minimapRegion, this.camOffsetX + mapOffsetX, this.camOffsetY + mapOffsetY);

            if (!Config.PvpEnabled) {
                for (int i = 0; i < f.players.size; i++) {
                    if ((((Player) f.players.get(i)).currentMap.equals(f.player.currentMap)) && (((Player) f.players.get(i)).minimapX >= -1) && (((Player) f.players.get(i)).minimapX < this.minimapWidth) && (((Player) f.players.get(i)).minimapY >= -1) && (((Player) f.players.get(i)).minimapY < this.minimapHeight)) {
                        batch.draw(this.otherPlayer, this.camOffsetX + mapOffsetX + ((Player) f.players.get(i)).minimapX, this.camOffsetY + mapOffsetY + ((Player) f.players.get(i)).minimapY);
                    }
                }
            }

            if ((f.map.containsEnemies) && (f.map.collisions.enemies != null)) {
                for (int i = 0; i < f.map.collisions.enemies.length; i++) {
                    if ((f.map.collisions.enemies[i] != null) && (f.map.collisions.enemies[i].visible) && (f.map.collisions.enemies[i].minimapX >= -1) && (f.map.collisions.enemies[i].minimapX < this.minimapWidth) && (f.map.collisions.enemies[i].minimapY >= -1) && (f.map.collisions.enemies[i].minimapY < this.minimapHeight)) {
                        batch.draw(this.monster, this.camOffsetX + mapOffsetX + f.map.collisions.enemies[i].minimapX, this.camOffsetY + mapOffsetY + f.map.collisions.enemies[i].minimapY);
                    }
                }
            }

            if ((f.player.minimapX >= -1) && (f.player.minimapX < this.minimapWidth) && (f.player.minimapY >= -1) && (f.player.minimapY < this.minimapHeight - 1)) {
                batch.draw(this.player, this.camOffsetX + mapOffsetX + f.player.minimapX,
                this.camOffsetY + mapOffsetY + f.player.minimapY);
            }
            if (this.crosshairActive) {
                batch.draw(this.lightFrame,
                this.camOffsetX + mapOffsetX + this.crosshairX - 2.0F, this.camOffsetY + mapOffsetY + this.crosshairY, 5.0F, 1.0F);
                batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX + this.crosshairX, this.camOffsetY + mapOffsetY + this.crosshairY - 2.0F, 1.0F, 5.0F);

                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX - 2.0F, this.camOffsetY + mapOffsetY + this.crosshairY - 1.0F, 2.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX + 1.0F, this.camOffsetY + mapOffsetY + this.crosshairY - 1.0F, 2.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX - 2.0F, this.camOffsetY + mapOffsetY + this.crosshairY + 1.0F, 2.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX + 1.0F, this.camOffsetY + mapOffsetY + this.crosshairY + 1.0F, 2.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX - 3.0F, this.camOffsetY + mapOffsetY + this.crosshairY, 1.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX + 3.0F, this.camOffsetY + mapOffsetY + this.crosshairY, 1.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX, this.camOffsetY + mapOffsetY + this.crosshairY + 3.0F, 1.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX, this.camOffsetY + mapOffsetY + this.crosshairY - 3.0F, 1.0F, 1.0F);

                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX - 1.0F, this.camOffsetY + mapOffsetY + this.crosshairY + 2.0F, 1.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX + 1.0F, this.camOffsetY + mapOffsetY + this.crosshairY + 2.0F, 1.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX - 1.0F, this.camOffsetY + mapOffsetY + this.crosshairY - 2.0F, 1.0F, 1.0F);
                batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.crosshairX + 1.0F, this.camOffsetY + mapOffsetY + this.crosshairY - 2.0F, 1.0F, 1.0F);

                this.nameOffsetY = -2;

                if ((f.player.plateBG != null) && (f.nameplate != null) && (f.player.hpFront != null)) {
                    for (int i = 0; i < f.players.size; i++) {
                        if ((((Player) f.players.get(i)).currentMap.equals(f.player.currentMap)) && (((Player) f.players.get(i)).fullyInitialized)) {
                            if (((Player) f.players.get(i)).desiredBounds.overlaps(this.crosshair)) {
                                f.nameplate.draw(batch, ((Player) f.players.get(i)).name, this.camOffsetX + mapOffsetX, this.camOffsetY + mapOffsetY + this.nameOffsetY);
                                this.nameOffsetY -= 8;
                            }
                        }
                    }
                }
            }

            batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX - 1, this.camOffsetY + mapOffsetY, 1.0F, this.minimapHeight);
            batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX + this.minimapWidth, this.camOffsetY + mapOffsetY, 1.0F, this.minimapHeight);
            batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX, this.camOffsetY + mapOffsetY - 1, this.minimapWidth, 1.0F);
            batch.draw(this.brownFrame, this.camOffsetX + mapOffsetX, this.camOffsetY + mapOffsetY + this.minimapHeight, this.minimapWidth, 1.0F);

            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX - 2, this.camOffsetY + mapOffsetY, 1.0F, this.minimapHeight);
            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX + this.minimapWidth + 2, this.camOffsetY + mapOffsetY, 1.0F, this.minimapHeight);

            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX + this.minimapWidth + 1, this.camOffsetY + mapOffsetY - 1, 1.0F, 1.0F);
            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX + this.minimapWidth + 1, this.camOffsetY + mapOffsetY + this.minimapHeight, 1.0F, 1.0F);
            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX + this.minimapWidth, this.camOffsetY + mapOffsetY + this.minimapHeight + 1, 1.0F, 1.0F);
            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX + this.minimapWidth, this.camOffsetY + mapOffsetY - 2, 1.0F, 1.0F);

            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX - 1, this.camOffsetY + mapOffsetY - 1, 1.0F, 1.0F);
            batch.draw(this.darkFrame, this.camOffsetX + mapOffsetX - 1, this.camOffsetY + mapOffsetY + this.minimapHeight, 1.0F, 1.0F);

            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX - 3, this.camOffsetY + mapOffsetY, 1.0F, this.minimapHeight);
            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX + this.minimapWidth + 1, this.camOffsetY + mapOffsetY, 1.0F, this.minimapHeight);

            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX - 2, this.camOffsetY + mapOffsetY - 1, 1.0F, 1.0F);
            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX - 2, this.camOffsetY + mapOffsetY + this.minimapHeight, 1.0F, 1.0F);

            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX - 1, this.camOffsetY + mapOffsetY - 2, this.minimapWidth + 1, 1.0F);
            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX - 1, this.camOffsetY + mapOffsetY + this.minimapHeight + 1, this.minimapWidth + 1, 1.0F);

            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX + this.minimapWidth, this.camOffsetY + mapOffsetY - 1, 1.0F, 1.0F);
            batch.draw(this.lightFrame, this.camOffsetX + mapOffsetX + this.minimapWidth, this.camOffsetY + mapOffsetY + this.minimapHeight, 1.0F, 1.0F);
        }
    }
}
