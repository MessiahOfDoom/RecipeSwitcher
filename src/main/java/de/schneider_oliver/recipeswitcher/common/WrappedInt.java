package de.schneider_oliver.recipeswitcher.common;

public class WrappedInt extends Number{

	private static final long serialVersionUID = 4650385497574811876L;
	private int minV, maxV, value;
	
	public WrappedInt(int min, int max) {
		this(min, max, 0);
	}
	
	public WrappedInt(int min, int max, int value) {
		minV = min;
		maxV = max;
		this.value = value;
		this.normalizeValue();
	}
	
	public WrappedInt with(int value) {
		return new WrappedInt(minV, maxV, value);
	}
	
	private void normalizeValue(){
		int range = maxV - minV;
		if(range < 0) {
			int i = maxV;
			maxV = minV;
			minV = i;
			range = -range;
		}
		range += 1;
		value = ((value - minV) % range) + minV;
	}
	
	@Override
	public int intValue() {
		return value;
	}

	@Override
	public long longValue() {
		return value;
	}

	@Override
	public float floatValue() {
		return value;
	}

	@Override
	public double doubleValue() {
		return value;
	}

}
