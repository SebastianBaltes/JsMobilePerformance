package de.objectcode.perftests.utils;

import java.util.Date;
import java.util.List;

public class RandomValues {

    /**
     * a very bad PRGN, but ok for performance tests 
     */
    public class CustomRandom {
        
        int seed;
        
        public CustomRandom(int seed) {
            this.seed = seed;
        }
        
        public int next() {
            seed = (seed * 9301 + 49297) % 23328;
            return seed;
        }

        public float nextFloat(float maxExclusive) {
            return next() * maxExclusive / 23328f;
        }

        public double nextDouble(double maxExclusive) {
            return next() * maxExclusive / 23328d;
        }

        public int nextInt(int maxExclusive) {
            return next() * maxExclusive / 23328;
        }

    }
    
    
	private CustomRandom rnd = new CustomRandom(1);
	private final static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxy0123456789";

	private final static String[] words = new String[] {
		"Earth","is","the","third","planet","from","the","Sun,","and","the","densest","and","fifth-largest","of","the","eight","planets","in","the","Solar","System.","It","is","also","the","largest","of","the","Solar","System's","four","terrestrial","planets.","It","is","sometimes","referred","to","as","the","world","or","the","Blue","Planet.[22]","Earth","formed","approximately","4.54","billion","years","ago,","and","life","appeared","on","its","surface","within","one","billion","years.[23]","Earth's","biosphere","then","significantly","altered","the","atmospheric","and","other","basic","physical","conditions,","which","enabled","the","proliferation","of","organisms","as","well","as","the","formation","of","the","ozone","layer,","which","together","with","Earth's","magnetic","field","blocked","harmful","solar","radiation,","and","permitted","formerly","ocean-confined","life","to","move","safely","to","land.[24]","The","physical","properties","of","the","Earth,","as","well","as","its","geological","history","and","orbit,","have","allowed","life","to","persist.","Estimates","on","how","much","longer","the","planet","will","be","able","to","continue","to","support","life","range","from","500","million","years","(myr),","to","as","long","as","2.3","billion","years","(byr).[25][26][27]","Earth's","lithosphere","is","divided","into","several","rigid","segments,","or","tectonic","plates,","that","migrate","across","the","surface","over","periods","of","many","millions","of","years.","About","71%","of","the","surface","is","covered","by","salt","water","oceans,","with","the","remainder","consisting","of","continents","and","islands","which","together","have","many","lakes","and","other","sources","of","water","that","contribute","to","the","hydrosphere.","Earth's","poles","are","mostly","covered","with","ice","that","is","the","solid","ice","of","the","Antarctic","ice","sheet","and","the","sea","ice","that","is","the","polar","ice","packs.","The","planet's","interior","remains","active,","with","a","solid","iron","inner","core,","a","liquid","outer","core","that","generates","the","magnetic","field,","and","a","thick","layer","of","relatively","solid","mantle.","Earth","gravitationally","interacts","with","other","objects","in","space,","especially","the","Sun","and","the","Moon.","During","one","orbit","around","the","Sun,","the","Earth","rotates","about","its","own","axis","366.26","times,","creating","365.26","solar","days,","or","one","sidereal","year.[note","7]","The","Earth's","axis","of","rotation","is","tilted","23.4°","away","from","the","perpendicular","of","its","orbital","plane,","producing","seasonal","variations","on","the","planet's","surface","with","a","period","of","one","tropical","year","(365.24","solar","days).[28]","The","Moon","is","Earth's","only","natural","satellite.","It","began","orbiting","the","Earth","about","4.53","billion","years","ago","(bya).","The","Moon's","gravitational","interaction","with","Earth","stimulates","ocean","tides,","stabilizes","the","axial","tilt,","and","gradually","slows","the","planet's","rotation.","The","planet","is","home","to","millions","of","species,","including","humans.[29]","Both","the","mineral","resources","of","the","planet","and","the","products","of","the","biosphere","contribute","resources","that","are","used","to","support","a","global","human","population.[30]","These","inhabitants","are","grouped","into","about","200","independent","sovereign","states,","which","interact","through","diplomacy,","travel,","trade,","and","military","action.","Human","cultures","have","developed","many","views","of","the","planet,","including",
	};
	
	public long nextLong() {
	    // there is no long type in js, so just stay with int
	    return this.rnd.next();
	}

	public int nextInt() {
        return this.rnd.next();
	}
	
    public double nextDouble() {
        return this.rnd.nextDouble(18446744073709552000.0);
    }

    public boolean nextBoolean() {
        return this.rnd.nextInt(2)==1;
    }

	public Date nextDate() {
		return new Date(this.nextLong());
	}

	public <T> T next(List<T> list) {
		return list.get(this.rnd.nextInt(list.size()));
	}

	public String nextTextWord() {
        return this.words[this.rnd.nextInt(this.words.length)];
	}
	
    public String nextString(int length) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < length; i++) {
            b.append(this.characters.charAt(this.rnd.nextInt(this.characters.length())));
        }
        return b.toString();
    }

    public String nextTextWithWords(int countWords) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < countWords; i++) {
            b.append(nextTextWord());
            b.append(" ");
        }
        return b.toString();
    }

}
