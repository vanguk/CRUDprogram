package repository;

import java.io.*;
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

    /**
     * Constructor
     *
     * @param filePath  full path to the file
     * @param modelType only class which the implements {@link FileModel} and {@link Serializable}
     */
    public FileStorageManager(String filePath, Class<T> modelType) {
        this.filePath = filePath;
        this.modelType = modelType;
        this.file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Object put Map<Integer, T ></> serializable in file {@link #file}
     *
     * @param object
     */

    public void saveObject(T object) {
        Map<Integer, T> map = readAll();
        if (map != null) {
            map.put(object.getId(), object);
            saveAll(map);
        } else {
            map = new HashMap<>();
            map.put(object.getId(), object);
            saveAll(map);
        }
    }

    /**
     * Save all objects in {@link #file}
     * @param objects
     */

    public void saveAll(Map<Integer, T> objects) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(objects);
            oos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialization object from {@link #file}
     *
     * @param idObject
     * @return object<T>
     */

    public T readObject(int idObject) {
        return readAll().get(idObject);
    }

    /**
     * @return All objects from file
     */

    public Map<Integer, T> readAll() {
        Map<Integer, T> allObjects = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            allObjects = (Map<Integer, T>) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allObjects;
    }

    /**
     * Remove object from {@link #file}
     *
     * @param id
     */

    public void deleteBy(Integer id) {
        Map<Integer, T> map = readAll();
        map.remove(id);
        saveAll(map);
    }

    /**
     * This method use @Override {@link #toString()} at Object
     *
     * @return
     */

    public List<String> showAllObjects() {
        List<String> list = new ArrayList<>();
        Map<Integer, T> allObject = readAll();
        for (Map.Entry<Integer, T> entry : allObject.entrySet()) {
            list.add(entry.getValue().toString());
        }
        return list;
    }

    public boolean checkObject(int objectId){
        return readAll().containsKey(objectId);
    }
}
