package repository;

import model.Customer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileStorageManager<T extends FileModel> {

    String filePath;
    File file;
    //PrintStream printStream;
    FileWriter fileWriter;


    Class<T> modelType;

    public FileStorageManager(String filePath, Class<T> modelType) {
        this.filePath = filePath;
        this.modelType = modelType;
        this.file = new File(filePath);

        try {
            //this.printStream = new PrintStream(file);
            this.fileWriter = new FileWriter(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveObject(T object) {
        Map<Integer, T> map = readAll();
        if (map != null) {
            map.put(object.getId(), object);
            serializableAll(map);
        } else {
            map = new HashMap<>();
            map.put(object.getId(), object);
            serializableAll(map);
        }
    }

    public Map<Integer, T> readAll() {
        Map<Integer, T> allObjects = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            allObjects = (Map<Integer, T>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allObjects;
    }

    public void serializableAll(Map<Integer, T> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(objects);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T readBy(Integer id) {
        return readAll().get(id);
    }

    public void deleteBy(Integer id) {
        Map<Integer, T> map = (Map<Integer, T>) readAll();
        map.remove(id);
        serializableAll(map);
    }


    public T deSerializationFromFile(int idObject) {
        Map<Integer, T> allObjects = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            allObjects = (Map<Integer, T>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allObjects.get(idObject);
    }

    public List<String> showAllCustomer() {
        List<String> list = new ArrayList<>();
        Map<Integer, T> allObject = readAll();
        for (Map.Entry<Integer, T> entry : allObject.entrySet()) {
            list.add(entry.getValue().toString());
        }
        return list;
    }
}
