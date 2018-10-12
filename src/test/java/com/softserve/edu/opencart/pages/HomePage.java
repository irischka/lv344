package com.softserve.edu.opencart.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


import com.softserve.edu.opencart.data.Currencies;

public class HomePage extends AHeadComponent {
	final static Logger LOG = Logger.getLogger(HomePage.class);
	private ProductsListComponent productsListComponent;
	
	public HomePage(WebDriver driver) {
		super(driver);
		initProductsListComponent();
	}
	
	private void initProductsListComponent() {
		productsListComponent = new  ProductsListComponent(driver);
	}
	
	// PageObject
	
	// productsListComponent
	public ProductsListComponent getProductsListComponent() {
		return productsListComponent;
	}
	
	// Business Logic

	public double getProductPriceAmountByPartialName(String partialProductName) {
		return getProductsListComponent()
				.getProductComponentByPartialName(partialProductName)
				.getPriceAmount();
	}

	public HomePage chooseCurrency(Currencies currency) {
        clickCurrencyByPartialName(currency.toString());
        return new HomePage(driver); 
    }

	public HomeMessagePage putToCartProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToCartProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

	public HomeMessagePage putToWishProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToWishProductByPartialName(partialProductName);
        return new HomeMessagePage(driver); 
    }

	public HomeMessagePage putToCompareProductByPartialName(String partialProductName) {
		getProductsListComponent()
			.addToCompareProductByPartialName(partialProductName);
		LOG.debug(String.format("Proudct %s added to compare list", partialProductName));
        return new HomeMessagePage(driver); 
    }

}
