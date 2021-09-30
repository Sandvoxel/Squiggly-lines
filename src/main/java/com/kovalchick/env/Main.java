package com.kovalchick.env;

import processing.core.PApplet;
import processing.core.PVector;

import java.util.ArrayList;

public class Main extends PApplet {
    static Main sketch = new Main();

    private static final int NUMBER_OF_TENTACLES = 5;



    public static void main(String[] args){
        String[] processArgs = {"MySketch"};
        PApplet.runSketch(processArgs,sketch);
    }

    @Override
    public void settings() {
        size(1000, 1000);
    }

    ArrayList<Tentacle> tentacles;

    PVector pos;
    PVector vel;
    PVector gravity;

    public void setup() {
        pos = new PVector(0, 0);
        vel = new PVector(2, 1.3f);
        gravity = new PVector(0, 0.1f);
        vel.mult(3);

        tentacles = new ArrayList<Tentacle>();

        float da = TWO_PI/NUMBER_OF_TENTACLES;
        for (float a = 0; a < TWO_PI; a += da) {
            float x = width/2 + cos(a) * 300;
            float y = height/2 + sin(a) * 300;
            tentacles.add(new Tentacle(x, y));
        }
    }

    public void draw() {
        background(51);
        noFill();
        ellipse(width/2, height/2, 400, 400);
        for (Tentacle t : tentacles) {
            if (mousePressed){
                t.update(new PVector(mouseX,mouseY));
            }else {
                t.update(pos);
            }
            t.show(sketch);
        }


        pos.add(vel);
        vel.add(gravity);
        noStroke();
        fill(100, 255, 0);
        ellipse(pos.x, pos.y, 32, 32);

        if (pos.x > width || pos.x < 0) {
            vel.x *= -1;
        }

        if (pos.y > height) {
            pos.y = height;
            vel.y *= -1;
            vel.mult(0.95f);
        }
    }

}
