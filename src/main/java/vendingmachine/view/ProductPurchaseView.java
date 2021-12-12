package vendingmachine.view;

import vendingmachine.domain.money.Money;
import vendingmachine.controller.VendingMachineController;

public class ProductPurchaseView extends VendingMachineView {
	public ProductPurchaseView(VendingMachineController controller) {
		super(controller);
	}

	@Override
	public void show() {
		try {
			validatePurchasable();
			purchaseProcess();
			validatePurchasable();
		} catch (IllegalArgumentException e) {
			outputProcessor.printMessage(e.getMessage());
		} catch (IllegalStateException e) {
			hide();
		}
	}

	private void purchaseProcess() {
		Money insertMoney = controller.getInsertMoney();
		outputProcessor.printInsertMoney(insertMoney);
		String productName = inputProcessor.readProductName();
		outputProcessor.printLine();
		controller.purchaseProduct(productName);
	}

	private void validatePurchasable() {
		if (!controller.isPurchasable()) {
			throw new IllegalStateException();
		}
	}
}
