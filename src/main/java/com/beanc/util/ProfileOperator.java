package com.beanc.util;

import java.io.*;
import java.util.List;

public class ProfileOperator {
    private static final String userHome = System.getProperty("user.home");
    public static String defaultProfilePath = userHome + "\\.candice\\profiles.dai";

    private String path;
    //重构点
    public ProfileOperator(String path) {
        this.path = path;
    }

    //表单布局形式
    public String getTableOverlooking() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getTableOverlooking();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setTableOverlooking(String tableOverlooking) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setTableOverlooking(tableOverlooking);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //日志显示
    public String getLogOverlooking() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getLogOverlooking();
        } catch (Exception e) {
            return null;
        }
    }
    public void setLogOverlooking(String logOverlooking) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setLogOverlooking(logOverlooking);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    //消费体活和罚除体活的单值

    public Double getRewardValue() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getRewardValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Double getPunishValue() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getPunishValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setRewardValue(Double rewardValue) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setRewardValue(rewardValue);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void setPunishValue(Double punishValue) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setPunishValue(punishValue);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    //主题配置
    public String getTheme() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getTheme();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setTheme(String theme) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setTheme(theme);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    //管理员密码

    public String getPassword() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getPassword();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setPassword(String password) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setPassword(password);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    //两个操作员密码

    public String getMember1password() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getMember1password();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String getMember2password() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getMember2password();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setMember1password(String member1password) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setMember1password(member1password);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public void setMember2password(String member2password) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setMember2password(member2password);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getDorm() {
        try {
            File fileGetting = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileGetting));
            Profiles profiles = (Profiles) ois.readObject();

            return profiles.getDorm();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void setDorm(List<String> dorm) {
        try {
            File fileDeserialize = new File(path);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileDeserialize));
            Profiles profiles = (Profiles) ois.readObject();

            profiles.setDorm(dorm);

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileDeserialize));
            oos.writeObject(profiles);
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //重构结束


    public ProfileOperator(String pathname,
                           String tableOverlooking,
                           String logOverlooking,
                           Double rewardValue,
                           Double punishValue,
                           String theme,
                           String password,
                           String member1password,
                           String member2password,
                           List<String> dorm) {
        Profiles profiles = new Profiles(tableOverlooking,
                logOverlooking,
                rewardValue,
                punishValue,
                theme,
                password,
                member1password,
                member2password,
                dorm);

        File fileSetting = new File(pathname);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileSetting))) {
            oos.writeObject(profiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
