package com.arthurfalcao.ifal;

import robocode.*;
import robocode.Robot;
import robocode.util.Utils;

import java.awt.*;

public class Zatopek extends Robot {

    private Double dist = 50.0;

    public void run() {
        setColors(Color.BLUE, Color.RED, Color.GRAY, Color.GREEN, Color.WHITE);

        while (true) {
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(100);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getDistance() < 200) {
            turnGunRight(e.getBearing());
            fire(2);
        } else if (e.getDistance() < 50 && getEnergy() > 50) {
            fire(3);
        } else {
            fire(1);
        }
    }

    @Override
    public void onHitRobot(HitRobotEvent e) {
        double turnGunAmt = Utils.normalRelativeAngleDegrees(e.getBearing() + getHeading() - getGunHeading());
        turnGunRight(turnGunAmt);
        fire(3);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        turnRight(Utils.normalRelativeAngleDegrees(90 - (getHeading() - e.getHeading())));
        ahead(dist);
        dist *= -1;
        scan();
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        turnLeft(180);
        back(20);
    }

    @Override
    public void onWin(WinEvent e) {
        turnRight(36000);
    }

    @Override
    public void onDeath(DeathEvent e) {
        System.out.println(getName() + " morreu!");
        System.out.println("Quantidade de inimigos ainda vivos: " + getOthers());
    }
}
