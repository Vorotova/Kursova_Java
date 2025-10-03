public class SupplyContract {
    private String productType;
    private int quantity;
    private String deliveryTerm;
    private double cost;
    private int contractId;

    private static int contractCounter = 0;

    public SupplyContract(String productType, int quantity, String deliveryTerm, double cost, int contractId) {
        this.productType = productType;
        this.quantity = quantity;
        this.deliveryTerm = deliveryTerm;
        this.cost = cost;
        this.contractId = contractId;
        contractCounter++;
    }

    public String getProductType() {
        return productType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getDeliveryTerm() {
        return deliveryTerm;
    }

    public double getCost() {
        return cost;
    }

    public int getContractId() {
        return contractId;
    }

    public int getDeliveryTermInDays() {
        try {
            String[] parts = deliveryTerm.split(" ");
            int value = Integer.parseInt(parts[0].trim());
            String unit = parts.length > 1 ? parts[1] : "днів";
            DeliveryUnitStrategy strategy = DeliveryUnitResolver.resolve(unit);
            return strategy.toDays(value);
        } catch (Exception e) {
            System.err.println("Помилка обробки терміну поставки: " + deliveryTerm);
            return 0;
        }
    }

    public static int getContractCounter() {
        return contractCounter;
    }

    @Override
    public String toString() {
        return "ID: " + contractId + ", Тип продукту: " + productType + ", Кількість: " + quantity +
                ", Термін поставки: " + deliveryTerm + ", Вартість: " + cost;
    }
}

