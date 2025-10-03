import javax.swing.*;
import java.io.*;
import java.util.List;

class FileDataRepository implements DataRepository {

    private final File baseDir = new File("src/data");

    public FileDataRepository() {
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }
    }

    @Override
    public void saveContracts(List<SupplyContract> contracts, JFrame parent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(baseDir, "contracts.txt")))) {
            for (SupplyContract contract : contracts) {
                writer.write("ID: " + contract.getContractId());
                writer.newLine();
                writer.write("Тип продукту: " + contract.getProductType());
                writer.newLine();
                writer.write("Кількість: " + contract.getQuantity());
                writer.newLine();
                writer.write("Термін поставки: " + contract.getDeliveryTerm());
                writer.newLine();
                writer.write("Вартість: " + contract.getCost());
                writer.newLine();
                writer.newLine();
            }
        }
    }

    @Override
    public void saveCustomers(List<Customer> customers, JFrame parent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(baseDir, "customers.txt")))) {
            for (Customer customer : customers) {
                writer.write("ID контракту: " + customer.getContractId());
                writer.newLine();
                writer.write("Підприємство: " + customer.getEnterpriseName());
                writer.newLine();
                writer.write("Замовник: " + customer.getFullName());
                writer.newLine();
                writer.write("Адреса: " + customer.getAddress());
                writer.newLine();
                writer.write("Телефон: " + customer.getPhoneNumber());
                writer.newLine();
                writer.newLine();
            }
        }
    }

    @Override
    public void saveEngineers(List<SalesEngineer> engineers, JFrame parent) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(baseDir, "sales_engineers.txt")))) {
            for (SalesEngineer engineer : engineers) {
                writer.write("Підприємство: " + engineer.getEnterpriseName());
                writer.newLine();
                writer.write("Інженер: " + engineer.getFullName());
                writer.newLine();
                writer.write("Адреса: " + engineer.getAddress());
                writer.newLine();
                writer.write("Телефон: " + engineer.getPhoneNumber());
                writer.newLine();
                writer.write("Стаж роботи: " + engineer.getWorkExperience() + " років");
                writer.newLine();
                writer.newLine();
            }
        }
    }

    @Override
    public void loadContracts(List<SupplyContract> contracts) throws IOException {
        File file = new File(baseDir, "contracts.txt");
        if (!file.exists()) { return; }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID: ")) {
                    int contractId = Integer.parseInt(line.substring(4).trim());
                    String productType = reader.readLine().substring(13).trim();
                    int quantity = Integer.parseInt(reader.readLine().substring(10).trim());
                    String deliveryTerm = reader.readLine().substring(16).trim();
                    double cost = Double.parseDouble(reader.readLine().substring(9).trim());
                    contracts.add(new SupplyContract(productType, quantity, deliveryTerm, cost, contractId));
                    reader.readLine();
                }
            }
        }
    }

    @Override
    public void loadCustomers(List<Customer> customers) throws IOException {
        File file = new File(baseDir, "customers.txt");
        if (!file.exists()) { return; }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("ID контракту: ")) {
                    int contractId = Integer.parseInt(line.substring(14).trim());
                    String enterpriseName = reader.readLine().substring(13).trim();
                    String fullName = reader.readLine().substring(9).trim();
                    String address = reader.readLine().substring(8).trim();
                    String phoneNumber = reader.readLine().substring(9).trim();
                    customers.add(new Customer(enterpriseName, fullName, address, phoneNumber, contractId));
                    reader.readLine();
                }
            }
        }
    }

    @Override
    public void loadEngineers(List<SalesEngineer> engineers) throws IOException {
        File file = new File(baseDir, "sales_engineers.txt");
        if (!file.exists()) { return; }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Підприємство: ")) {
                    String enterpriseName = line.substring(14).trim();
                    String fullName = reader.readLine().substring(9).trim();
                    String address = reader.readLine().substring(8).trim();
                    String phoneNumber = reader.readLine().substring(9).trim();
                    int workExperience = Integer.parseInt(reader.readLine().substring(13).trim().replace(" років", ""));
                    engineers.add(new SalesEngineer(enterpriseName, fullName, address, phoneNumber, workExperience));
                    reader.readLine();
                }
            }
        }
    }
}


