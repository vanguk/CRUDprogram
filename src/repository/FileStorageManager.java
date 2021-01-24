package repository;

import model.Customer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class FileStorageManager<T extends FileModel> {

    String filePath;
    File file;
    PrintStream printStream;

    Class<T> modelType;

    public FileStorageManager(String filePath, Class<T> modelType) {
        this.filePath = filePath;
        this.modelType = modelType;
        this.file = new File(filePath);

        try {
            this.printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void saveObject(T object) {
        printStream.println(object.getStringForFile());
    }

    public void saveAllObjects(T[] Objects) {

    }

    public Map<Integer, T> readAll() {
        Map<Integer, T> resultCollection = new HashMap<>();
        try {
            String str = null;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((str = bufferedReader.readLine()) != null) {
                T object = modelType.getDeclaredConstructor(String.class).newInstance(str);
                resultCollection.put(object.getId(), object);
            }
            fileReader.close();
            bufferedReader.close();

        } catch (IOException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return resultCollection;
    }

    public T readBy(Integer id) {
        return readAll().get(id);
    }

    public void deleteBy(Integer id) {
        Map<Integer, T> allObjects = this.readAll();
        allObjects.remove(id);
        printStream.close();
        file.delete();
        try {
            printStream = new PrintStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Integer, T> entry : allObjects.entrySet()) {
            saveObject(entry.getValue());
        }
    }
}
