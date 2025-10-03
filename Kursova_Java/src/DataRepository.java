import javax.swing.*;
import java.io.IOException;
import java.util.List;

interface DataRepository {
    void saveContracts(List<SupplyContract> contracts, JFrame parent) throws IOException;
    void saveCustomers(List<Customer> customers, JFrame parent) throws IOException;
    void saveEngineers(List<SalesEngineer> engineers, JFrame parent) throws IOException;

    void loadContracts(List<SupplyContract> contracts) throws IOException;
    void loadCustomers(List<Customer> customers) throws IOException;
    void loadEngineers(List<SalesEngineer> engineers) throws IOException;
}


