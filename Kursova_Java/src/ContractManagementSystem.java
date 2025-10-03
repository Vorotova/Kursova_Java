import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.io.*;
import javax.swing.*;

public class ContractManagementSystem {
    private final List<SalesEngineer> engineers;
    private final List<Customer> customers;
    private final List<SupplyContract> contracts;
    private final DataRepository repository;
    private final ContractFactory contractFactory;

    public ContractManagementSystem() {
        this(new FileDataRepository(), new ContractFactory());
    }

    public ContractManagementSystem(DataRepository repository, ContractFactory contractFactory) {
        this.engineers = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.contracts = new ArrayList<>();
        this.repository = repository;
        this.contractFactory = contractFactory;
    }

    public void addSalesEngineer(SalesEngineer engineer) {
        engineers.add(engineer);
    }

    public List<SalesEngineer> getSalesEngineers() { return Collections.unmodifiableList(engineers); }

    public void addCustomer(Customer customer) { customers.add(customer); }

    public void addContract(SupplyContract contract) { contracts.add(contract); }

    public SupplyContract createContract(String productType, int quantity, String deliveryTerm, double cost, int contractId) {
        return contractFactory.create(productType, quantity, deliveryTerm, cost, contractId);
    }

    public List<SupplyContract> getContracts() { return Collections.unmodifiableList(contracts); }

    public List<Customer> getCustomers() { return Collections.unmodifiableList(customers); }

    public boolean removeContractById(int contractId) {
        return contracts.removeIf(c -> c.getContractId() == contractId);
    }

    public boolean removeCustomerByName(String fullName) {
        return customers.removeIf(c -> c.getFullName().equalsIgnoreCase(fullName));
    }

    public boolean removeEngineerByName(String fullName) {
        return engineers.removeIf(e -> e.getFullName().equalsIgnoreCase(fullName));
    }

    public double getAverageProductQuantity() {
        if (contracts.isEmpty()) {
            return 0;
        }
        int totalQuantity = 0;
        for (SupplyContract contract : contracts) {
            totalQuantity += contract.getQuantity();
        }
        return (double) totalQuantity / contracts.size();
    }

    public SupplyContract getMaxCostContract() {
        return contracts.stream()
                .max(Comparator.comparingDouble(SupplyContract::getCost))
                .orElse(null);
    }

    public SupplyContract getMaxDeliveryTermContract() {
        return contracts.stream()
                .max(Comparator.comparingInt(SupplyContract::getDeliveryTermInDays))
                .orElse(null);
    }



    public boolean isContractIdUnique(int contractId) {
        return contracts.stream().noneMatch(contract -> contract.getContractId() == contractId);
    }

    public boolean isCustomerContractIdUnique(int contractId) {
        return customers.stream().noneMatch(customer -> customer.getContractId() == contractId);
    }

    public boolean isEngineerUnique(String fullName, String enterpriseName) {
        return engineers.stream().noneMatch(engineer ->
                engineer.getFullName().equalsIgnoreCase(fullName) &&
                        engineer.getEnterpriseName().equalsIgnoreCase(enterpriseName)
        );
    }

    public void saveData(JFrame parent) {
        if (contracts.isEmpty() && customers.isEmpty() && engineers.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "Контракти, замовники та інженери відсутні. Немає даних для збереження.", "Попередження", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            repository.saveContracts(contracts, parent);
            repository.saveCustomers(customers, parent);
            repository.saveEngineers(engineers, parent);
            JOptionPane.showMessageDialog(parent, "Дані успішно збережені!", "Успіх", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(parent, "Помилка під час збереження даних: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadData(JFrame parent) {
        try {
            repository.loadContracts(contracts);
            repository.loadCustomers(customers);
            repository.loadEngineers(engineers);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(parent, "Файли або папка src не знайдені. Будь ласка, спершу збережіть дані.", "Попередження", JOptionPane.WARNING_MESSAGE);
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(parent, "Помилка під час завантаження даних: " + e.getMessage(), "Помилка", JOptionPane.ERROR_MESSAGE);
        }
    }
}
