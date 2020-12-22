package dao;

import model.Customer;

import java.io.*;

public class CustomersDAOImpl implements CustomersDAO<Customer> {
    String filePathCustomers = "src/resources/customers.txt";
    File file = new File(filePathCustomers);
    FileReader fileReader;
    FileWriter fileWriter;
    BufferedReader bufferedReader;
    BufferedWriter bufferedWriter;

    public CustomersDAOImpl() {
        try {
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(file);
            bufferedReader = new BufferedReader(fileReader);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (FileNotFoundException e) {
            System.err.println("(FATAL) File not found");
        } catch (IOException e) {
            System.err.println("Error IO");
        }
    }

    @Override
    public Customer findById(int id) {
        Customer resultCustomer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            while (fis.read() != -1){
                try {
                    Customer customer = (Customer)ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Customer();
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void delete(Customer customer) {

    }

    @Override
    public void addCustomer(Customer customer) {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(customer);
            fos.close();
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
