package com.nullcognition.abstractions;// Created by ersin on 22/07/15

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;

public class Plate{

	private ArrayList<Object> things;
	private long position = 0;
	private Plate plateOnMe, plateUnderMe;

	public void giveThingsTo(@Nullable Plate receiver){
		if(receiver != null){ receiver.getThings(things);}
		things.clear();
	}

	private void getThings(ArrayList<Object> thingsToGet){
		Collections.fill(things, thingsToGet); // may be better to use the generic collections.fill
		// if the implementation type changes, then fill will still work the same, not true
		// since all list type collections have addAll,?? find out
	}

	public void getThings(Object... thing){
		Collections.fill(things, thing);
	}

	public synchronized void stackOnTopOf(Plate aPlate){
		// can change this to be recursive, and stack on top of the newly stacked plate if the newly
		// stacked plate is not the top plate, and passing the under plates content up if it is the top
		plateUnderMe = aPlate;
		plateUnderMe.plateOnMe = this;
		position = plateUnderMe.position + 1;
		Plate aPlateOnMe = plateOnMe;
		while(aPlateOnMe != null){
			++aPlateOnMe.position;
			aPlateOnMe = aPlateOnMe.plateOnMe;
		}
		if(!plateUnderMe.things.isEmpty()){ plateUnderMe.giveThingsTo(this); }
	}

	public synchronized void givePlateTo(@Nullable Object plateReceivingObject){
		if(plateOnMe == null){
			if(plateUnderMe == null){
				if(plateReceivingObject != null){
//					plateReceivingObject.getPlate(this);
				}
			}
			else{
				giveThingsTo(plateUnderMe);
				plateUnderMe.plateOnMe = null;
			}
		}
		else{
			Plate aPlateOnMe = plateOnMe;
			while(aPlateOnMe.plateOnMe != null){
				aPlateOnMe = aPlateOnMe.plateOnMe;
				--aPlateOnMe.position;
			}
		}
	}
}
// re -
