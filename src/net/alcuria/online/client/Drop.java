package net.alcuria.online.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import net.alcuria.online.client.screens.Field;
import net.alcuria.online.utilities.SecureInt;

/**
 * Imported from game source
 */
public class Drop extends Actor {
    public static final int DROP_MONEY = 1;
    private Particle dropParticle;
    public Item droppedItem;
    public int particleID = 0;
    public int value = 0;

    public String attribute = "";

    protected float timeOnGround = 0.0F;
    public static final float consumeTime = 0.3F;
    public static final float removeTime = 30.0F;
    public static final float MONEY_DROP_RATE = 0.8F;
    public static final float COMMON_DROP_RATE = 0.03F;
    public static final float GEM_DROP_RATE = 0.01F;
    public static final float RARE_DROP_RATE = 0.005F;
    public static final float QUEST_DROP_RATE = 0.15F;
    public boolean isNetworked = false;

    public boolean randomized = false;

    public Drop(String filename, int x, int y, int width, int height, Field f) {
        super(filename, x, y, width, height, f);

        this.bounceDecay = 0.4F;
        this.dropParticle = new Particle(filename, x, y, width, height, 4, 5,
        false, f.assets);

        this.dropParticle.loop = true;
        this.dropParticle.playAnimation = false;
        this.maxHP = new SecureInt(1);
        this.bounds.width = 4.0F;
        this.bounds.height = 4.0F;

        this.visible = false;
        this.bounds.x = -30.0F;
        this.bounds.y = -30.0F;
    }

    public void update(Map map) {
        if (this.visible) {
            this.dropParticle.update(this.bounds.x - 4.0F, this.bounds.y, true);
            if (this.onGround) {
                this.dropParticle.rotate = false;
            } else {
                this.dropParticle.rotate = true;
                if (this.xVel > 0.0F) {
                    this.dropParticle.clockwise = true;
                } else {
                    this.dropParticle.clockwise = false;
                }

            }

            if ((this.bounds.x < 5.0F) && (this.xVel < 0.0F)) {
                this.xVel *= -1.0F;
            } else if ((this.bounds.x > map.width * 16 - 5) && (this.xVel > 0.0F)) {
                this.xVel *= -1.0F;
            }

            if ((this.f.player.isAfflictedWith(29)) && (this.timeOnGround > 0.55F) && (this.particleID == 0) && (distanceToPlayer() < this.f.player.effects.severity[29] * 40)) {
                this.onGround = false;
                moveDrop();
            }
            super.update(map);

            if (this.timeOnGround < 30.0F) {
                this.timeOnGround += Gdx.graphics.getDeltaTime();
            } else {
                this.visible = false;
            }
        }
    }

    private int distanceToPlayer() {
        return (int) (Math.abs(this.bounds.x - this.f.player.bounds.x) + Math.abs(this.bounds.y - this.f.player.bounds.y));
    }

    private void moveDrop() {
        float amount = 0.02F;
        float constant = 3.0F; // CE - 2.0F -> 3.0F
        if ((this.bounds != null) && (this.desiredBounds != null)) {
            this.bounds.x += (this.f.player.bounds.x - this.bounds.x) * amount;
            if (this.bounds.x < this.f.player.bounds.x - constant) { // CE - 3.0F -> constant
                this.bounds.x += constant;
            } else if (this.bounds.x > this.f.player.bounds.x + constant) { // CE - 3.0F -> constant
                this.bounds.x -= constant;
            }
            this.bounds.y += (this.f.player.bounds.y - this.bounds.y) * amount;
            if (this.bounds.y < this.f.player.bounds.y - constant) { // CE - 3.0F -> constant
                this.bounds.y += constant;
            } else if (this.bounds.y > this.f.player.bounds.y + constant) { // CE - 3.0F -> constant
                this.bounds.y -= constant;
            }
        }
        setAllSensors(this.bounds.x, this.bounds.y);
    }

    public void render(SpriteBatch batch) {
        if (this.visible) {
            this.dropParticle.render(batch);
            if (Config.debugMode) {
                for (int i = 0; i < this.sensorY.length; i++) {
                    batch.draw(this.debugPoint, this.bounds.x, this.bounds.y);
                    batch.draw(this.debugPoint, this.bounds.x + this.bounds.width, this.bounds.y);
                    batch.draw(this.debugPoint, this.bounds.x, this.bounds.y + this.bounds.height);
                    batch.draw(this.debugPoint, this.bounds.x + this.bounds.width, this.bounds.y
                    + this.bounds.height);
                }
            }
        }
    }

    public void dispose() {
        this.dropParticle.dispose();
    }

    public void start(Monster m, Player p) {
        this.underwater = m.underwater;
        if (this.underwater) {
            this.curGravity = 0.15F;
            this.curTerminalYVel = -3.0F;
        } else {
            this.curGravity = 0.3F;
            this.curTerminalYVel = -10.0F;
        }

        this.timeOnGround = 0.0F;
        this.visible = true;
        this.dropParticle.row = this.particleID;
        this.bounds.x = (m.bounds.x + m.bounds.width / 2.0F);
        this.bounds.y = (m.bounds.y + m.bounds.height / 2.0F + 5.0F);
        this.yVel = ((float) (Math.random() * 3.0D + 3.0D));
        this.xVel = ((float) (Math.random() * 6.0D - 3.0D));

        this.onGround = false;
        this.dropParticle.start(this.bounds.x, this.bounds.y, false);
        this.dropParticle.playAnimation = true;
    }

    public void start(Actor a) {
        this.underwater = a.underwater;
        this.timeOnGround = 0.0F;
        this.visible = true;
        this.dropParticle.row = this.particleID;
        this.bounds.y = (a.desiredBounds.y + 12.0F);
        this.yVel = 4.0F;
        this.facingLeft = a.facingLeft;
        if ((a instanceof NPC)) {
            if (a.facingLeft) {
                this.xVel = 2.0F;
                this.bounds.x = (a.desiredBounds.x + a.desiredBounds.width + 1.0F);
            } else {
                this.xVel = -2.0F;
                this.bounds.x = (a.desiredBounds.x - 1.0F);
            }
        } else if (a.facingLeft) {
            this.xVel = -4.0F;
            this.bounds.x = (a.desiredBounds.x - 1.0F);
        } else {
            this.xVel = 4.0F;
            this.bounds.x = (a.desiredBounds.x + a.desiredBounds.width + 1.0F);
        }

        if (this.randomized) {
            this.xVel = ((float) (Math.random() * 4.0D - 2.0D));
            this.randomized = false;
        }

        this.onGround = false;
        this.dropParticle.start(this.bounds.x, this.bounds.y, false);
        this.dropParticle.playAnimation = true;
    }

    public void collect(ItemManager inventory) {
        remove();

        if (this.particleID == 0) {
            this.f.hud.timeSinceNotice = 0.0F;
            inventory.money += this.value;
            Statistics.add(7, this.value);
        } else if (this.particleID == 50) {
            inventory.shards += 1L;
            this.f.hud.timeSinceNoticeShards = 0.0F;
            Statistics.increment(6);
        } else {
            this.droppedItem = new Item(this.value);
            this.droppedItem.attributes = new String(this.attribute);

            if (this.droppedItem.isEquippable()) {
                String[] attrs = {"w", "h"};

                for (String attr : attrs) {
                    int rnd = MathUtils.random(1, 100);
                    if (rnd > 75) {
                        this.droppedItem.setAttribute(attr, 3);
                        setDimensionAttr(attr, 3);
                    } else if (rnd > 45) {
                        this.droppedItem.setAttribute(attr, 2);
                        setDimensionAttr(attr, 2);
                    } else {
                        this.droppedItem.setAttribute(attr, 1);
                        setDimensionAttr(attr, 1);
                    }

                }

                if ((this.droppedItem.id == 57) || (this.droppedItem.id == 1023)) {
                    this.droppedItem.setAttribute("w", 3);
                    this.droppedItem.setAttribute("h", 3);
                }

                for (int i = 0; i < this.droppedItem.width * this.droppedItem.height; i++) {
                    this.droppedItem.setAttribute(Integer.toString(i), 0);
                }
            }

            this.droppedItem.applyAttributes();
            inventory.addItem(this.droppedItem);
            if (Config.notifItem) {
                NotificationList.add(this.droppedItem.name + " Acquired!");
            }
            this.droppedItem = null;
        }
    }

    private void setDimensionAttr(String attr, int i) {
        if (attr.equals("w")) {
            this.droppedItem.width = i;
        } else {
            this.droppedItem.height = i;
        }
    }

    public void remove() {
        this.visible = false;
        this.dropParticle.playAnimation = false;
        this.underwater = false;
        this.bounds.x = -30.0F;
        this.bounds.y = -30.0F;
    }
}
