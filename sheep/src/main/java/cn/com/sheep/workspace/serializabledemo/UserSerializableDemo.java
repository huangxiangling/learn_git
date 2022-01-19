package cn.com.sheep.workspace.serializabledemo;


import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Date;

public class UserSerializableDemo {

    public static void main(String[] args) {

        User user = new User();
        user.setName("chenhao");
        user.setGender("male");
        user.setAge(23);
        user.setBirthday(new Date());
        System.out.println(user.toString());

        // Write obj to File
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("tempFile"));
            oos.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(oos);
        }

        // Read obj from File
        File file = new File("tempFile");
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            User newUser = (User) ois.readObject();
            System.out.println(newUser.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            IOUtils.closeQuietly(ois);
            try {
                FileUtils.forceDelete(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
