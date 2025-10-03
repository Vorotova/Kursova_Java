class ContractFactory {
    public SupplyContract create(String productType, int quantity, String deliveryTerm, double cost, int contractId) {
        return new SupplyContract(productType, quantity, deliveryTerm, cost, contractId);
    }
}


