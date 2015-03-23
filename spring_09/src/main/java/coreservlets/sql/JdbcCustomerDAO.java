package coreservlets.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import coreservlets.Customer;
import coreservlets.CustomerListQuery;
import coreservlets.CustomerQuery;
import coreservlets.CustomerUpdate;

public class JdbcCustomerDAO
        implements CustomerUpdate, CustomerQuery, CustomerListQuery {

    private DataSource dataSource;

    /**
     * Creates a new JdbcCustomerDAO instance.
     *
     * @param dataSource required
     */
    public JdbcCustomerDAO(DataSource dataSource) {
        if (dataSource == null) {
            throw new IllegalArgumentException("Required: dataSource.");
        }
        this.dataSource = dataSource;
    }

    /*
     * @see coreservlets.CustomerUpdate#save(coreservlets.Customer)
     */
    public void save(Customer customer) {
        Connection conn = null;
        boolean autoCommit = false;
        try {
            conn = this.dataSource.getConnection();
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);
            if (!update(customer, conn)) {
                insert(customer, conn);
            }
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException suppressed) {
                }
            }
            throw new CustomerPersistenceException("Error: SQL."
                    + " Error saving customer: " + customer, e);
        } catch (RuntimeException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException suppressed) {
                }
            }
            throw new CustomerPersistenceException("Error: SQL."
                    + " Error saving customer: " + customer, e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(autoCommit);
                    conn.close();
                    conn = null;
                } catch (SQLException suppressed) {
                }
            }
        }
    }

    /**
     * Support method for {@link #save(Customer)}.
     *
     * @param customer required
     * @param conn required
     * @return <tt>true</tt> if a matching and stored customer was found and
     * successfully updated
     */
    private boolean update(Customer customer, Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "update customer set name = ? where id = ?");
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error: SQL."
                    + " Failed to update customer.", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception suppressed) {
                }
            }
        }
    }

    /**
     * Support method for {@link #save(Customer)}.
     *
     * @param customer required
     * @param conn required
     * @return <tt>true</tt> if the argued customer was successfully inserted
     */
    private boolean insert(Customer customer, Connection conn) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(
                    "insert into customer (id, name) values (?, ?)");
            stmt.setString(1, customer.getId());
            stmt.setString(2, customer.getName());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error: SQL."
                    + " Failed to insert customer.", e);
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (Exception suppressed) {
                }
            }
        }
    }

    /*
     * @see coreservlets.CustomerUpdate#deleteById(java.lang.String)
     */
    public void deleteById(String customerId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean autoCommit = false;
        try {
            conn = this.dataSource.getConnection();
            autoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("delete from customer where id = ?");
            stmt.setString(1, customerId);
            stmt.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException suppressed) {
                }
            }
            throw new CustomerPersistenceException("Error: SQL."
                    + " Error deleting customer with ID: " + customerId, e);
        } catch (RuntimeException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException suppressed) {
                }
            }
            throw new CustomerPersistenceException("Error: SQL."
                    + " Error deleting customer with ID: " + customerId, e);
        } finally {
            if (conn != null) {
                try {
                    conn.setAutoCommit(autoCommit);
                    conn.close();
                    conn = null;
                } catch (SQLException suppressed) {
                }
            }
        }
    }

    /*
     * @see coreservlets.CustomerQuery#getCustomerByName(java.lang.String)
     */
    public Customer getCustomerByName(String name) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement(
                    "select id, name from customer where name = ?");
            rslt = stmt.executeQuery();
            return rslt.next()
                    ? new Customer(rslt.getString("id"), rslt.getString("name"))
                    : null;
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error: SQL."
                    + " Error retrieving stored customer with name: " + name, e);
        } finally {
            if (rslt != null) {
                try {
                    rslt.close();
                    rslt = null;
                } catch (SQLException suppressed) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException suppressed) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException suppressed) {
                }
            }
        }
    }

    /*
     * @see coreservlets.CustomerListQuery#getCustomers()
     */
    public List<Customer> getCustomers() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rslt = null;
        try {
            conn = this.dataSource.getConnection();
            stmt = conn.prepareStatement("select id, name from customer");
            rslt = stmt.executeQuery();
            List<Customer> customers = new ArrayList<Customer>();
            while (rslt.next()) {
                Customer customer
                        = new Customer(rslt.getString("id"), rslt.getString("name"));
                customers.add(customer);
            }
            return customers;
        } catch (SQLException e) {
            throw new CustomerPersistenceException("Error: SQL."
                    + " Error retrieving stored customer list.", e);
        } finally {
            if (rslt != null) {
                try {
                    rslt.close();
                    rslt = null;
                } catch (SQLException suppressed) {
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                    stmt = null;
                } catch (SQLException suppressed) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                    conn = null;
                } catch (SQLException suppressed) {
                }
            }
        }
    }

}
