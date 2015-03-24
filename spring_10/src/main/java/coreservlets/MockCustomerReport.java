package coreservlets;

public class MockCustomerReport implements CustomerReport {

    private CustomerQuery query;

    public MockCustomerReport(CustomerQuery query) {
        this.query = query;
    }

    @Override
    public String getReport(String customerName) {
        Customer customer = this.query.getCustomerByName(customerName);
        return customer != null ? customer.toString() : null;
    }

}
