package com.nullcognition.abstractions.ants;

/**
 * Created by mms on 7/1/16.
 */

public class Ant {

    public void move() {
        // scentInput();
        scentOutput();
    }

    public Scent scent;

    public void scentInput(Scent.ScentType inputScentType, int inputScent) {
        scent.setScentType(inputScentType);
        scent.setScentSignal(inputScent);
    }

    public Scent scentOutput() {
        return scent;
    }
}

/*
* The idea is that when the ant is moving around it is sending a neutral scent for the other ant to follow.
* Ants follow at a spaced distance based on the scent decay period.
*
* If the lead ant finds food, the ant will morph the scent input to match that of the food scent,
 * The ant will back track to the next ant, start spraying positive scent of the new food scent.
 * The receiving ant will back track to the next ant, and communicate the positive new food scent, which
 * is done until the signal goes back to the ant home. Once at come more ants of a specialized type
  * are sent to follow the path and search around the area.
 *
 * When the ant meets the next ant in the line, it will start spraying the scent when they meet and
 * until it goes back to the source. This way you will have the largest intensity of scent at the
  * point of food because each ant will spray the scent for their path, but will then spray over an
  * existing path from the other ant(which it is following). Based on the intensity of the scent,
  * the ant can compute how many ants are in the chain, and how far the food is.
  *
  * Ant can exist in specialized types which is determined by genetic variations: stronger mandibles
  * will be guards, smaller size will be scouts due to quickness and light weight... add more types.
 *
 *
 * Scent following and signaling can be for any type, negative or positive, and will illicit the help
 * of the correct ant type from the ant home.
 *
 * Ants move in depth first when following, and breadth first when exploring. Ants memory needs only
 * be long enough to remember the scent and scent type. While exploring memories of location can be marked
 * and serialized as safe or not, with association to the location.
* */