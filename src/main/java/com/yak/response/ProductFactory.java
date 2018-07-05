package com.yak.response;

import com.yak.response.impl.Milk;
import com.yak.response.impl.Wool;

public class ProductFactory {

	public static Product getProduct(ProductType product) {
		switch (product) {
		case MILK:
			return new Milk();
		case WOOL:
			return new Wool();
		default:
			return null;
	}
}
}
