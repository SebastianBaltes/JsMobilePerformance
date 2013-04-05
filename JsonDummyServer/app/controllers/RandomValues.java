package controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class RandomValues {
	
	private Random rnd;
	private final static char[] characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜabcdefghijklmnopqrstuvwxyzäöü0123456789".toCharArray();
	private final static String[] words = new String[] {
		"Earth","is","the","third","planet","from","the","Sun,","and","the","densest","and","fifth-largest","of","the","eight","planets","in","the","Solar","System.","It","is","also","the","largest","of","the","Solar","System's","four","terrestrial","planets.","It","is","sometimes","referred","to","as","the","world","or","the","Blue","Planet.[22]","Earth","formed","approximately","4.54","billion","years","ago,","and","life","appeared","on","its","surface","within","one","billion","years.[23]","Earth's","biosphere","then","significantly","altered","the","atmospheric","and","other","basic","physical","conditions,","which","enabled","the","proliferation","of","organisms","as","well","as","the","formation","of","the","ozone","layer,","which","together","with","Earth's","magnetic","field","blocked","harmful","solar","radiation,","and","permitted","formerly","ocean-confined","life","to","move","safely","to","land.[24]","The","physical","properties","of","the","Earth,","as","well","as","its","geological","history","and","orbit,","have","allowed","life","to","persist.","Estimates","on","how","much","longer","the","planet","will","be","able","to","continue","to","support","life","range","from","500","million","years","(myr),","to","as","long","as","2.3","billion","years","(byr).[25][26][27]","Earth's","lithosphere","is","divided","into","several","rigid","segments,","or","tectonic","plates,","that","migrate","across","the","surface","over","periods","of","many","millions","of","years.","About","71%","of","the","surface","is","covered","by","salt","water","oceans,","with","the","remainder","consisting","of","continents","and","islands","which","together","have","many","lakes","and","other","sources","of","water","that","contribute","to","the","hydrosphere.","Earth's","poles","are","mostly","covered","with","ice","that","is","the","solid","ice","of","the","Antarctic","ice","sheet","and","the","sea","ice","that","is","the","polar","ice","packs.","The","planet's","interior","remains","active,","with","a","solid","iron","inner","core,","a","liquid","outer","core","that","generates","the","magnetic","field,","and","a","thick","layer","of","relatively","solid","mantle.","Earth","gravitationally","interacts","with","other","objects","in","space,","especially","the","Sun","and","the","Moon.","During","one","orbit","around","the","Sun,","the","Earth","rotates","about","its","own","axis","366.26","times,","creating","365.26","solar","days,","or","one","sidereal","year.[note","7]","The","Earth's","axis","of","rotation","is","tilted","23.4°","away","from","the","perpendicular","of","its","orbital","plane,","producing","seasonal","variations","on","the","planet's","surface","with","a","period","of","one","tropical","year","(365.24","solar","days).[28]","The","Moon","is","Earth's","only","natural","satellite.","It","began","orbiting","the","Earth","about","4.53","billion","years","ago","(bya).","The","Moon's","gravitational","interaction","with","Earth","stimulates","ocean","tides,","stabilizes","the","axial","tilt,","and","gradually","slows","the","planet's","rotation.","The","planet","is","home","to","millions","of","species,","including","humans.[29]","Both","the","mineral","resources","of","the","planet","and","the","products","of","the","biosphere","contribute","resources","that","are","used","to","support","a","global","human","population.[30]","These","inhabitants","are","grouped","into","about","200","independent","sovereign","states,","which","interact","through","diplomacy,","travel,","trade,","and","military","action.","Human","cultures","have","developed","many","views","of","the","planet,","including",
	};
	
	public RandomValues(int seed) {
		rnd = new Random(seed);
	}
	
	public Long nextLong() {
		return rnd.nextLong();
	}

	public String nextString(int length) {
		char[] b = new char[length];
		for (int i = 0; i < length; i++) {
			b[i]=characters[rnd.nextInt(characters.length)];
		}
		return new String(b);
	}

	public Date nextDate() {
		return new Date(rnd.nextLong());
	}

	public Double nextDouble() {
		return rnd.nextDouble();
	}

	public Boolean nextBoolean() {
		return rnd.nextBoolean();
	}

	public String nextTextWord() {
		return words[rnd.nextInt(words.length)];
	}

	public <T> T next(List<T> list) {
		return list.get(rnd.nextInt(list.size()));
	}

}
