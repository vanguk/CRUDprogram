package dao;

public interface CustomersDAO<E> {
    /**
     * Return customer founded by id
     * @param id customer id
     * @return founded customer
     */
    E findById (int id);

    /**
     * Saves new customer
     * @param customer new customer
     */
    void save(E customer);

    /**
     * Updates existed customer by it id
     * @param customer new customer data
     */
    void update(E customer);

    /**
     * Delete existed customer
     * @param customer customer
     */
    void delete(E customer);

    /**
     * Output customer in file
     * @param customer
     */
    void addCustomer(E customer);
}
