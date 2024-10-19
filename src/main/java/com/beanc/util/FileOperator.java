package com.beanc.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Comparator;

public class FileOperator {
    private String sourcePath;
    private String targetPath;
    public FileOperator(String sourcePath, String targetPath) {
        this.sourcePath = sourcePath;
        this.targetPath = targetPath;
    }

    public void rm() {
        Path filePath = Paths.get(sourcePath); // 要删除的文件路径

        try {
            Files.delete(filePath);
            //System.out.println("文件已成功删除: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mv() {
        Path SourcePath = Paths.get(sourcePath); // 源文件路径
        Path TargetPath = Paths.get(targetPath); // 目标路径

        try {
            Files.move(SourcePath, TargetPath, StandardCopyOption.REPLACE_EXISTING);
            //System.out.println("文件已成功移动到: " + targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mv(String newName) {
        Path SourcePath = Paths.get(sourcePath); // 原文件路径
        Path TargetPath = Paths.get(replaceAfterLastSlash(sourcePath, newName)); // 新的文件名，保持在相同目录下

        try {
            Files.move(SourcePath, TargetPath, StandardCopyOption.REPLACE_EXISTING);
            //System.out.println("文件已成功重命名为: " + TargetPath.getFileName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cp() {
        Path SourcePath = Paths.get(sourcePath); // 源文件路径
        Path TargetPath = Paths.get(targetPath); // 目标路径

        try {
            Files.copy(SourcePath, TargetPath, StandardCopyOption.REPLACE_EXISTING);
            //System.out.println("文件已成功复制到: " + targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rm_rf() {
        Path directory = Paths.get(sourcePath);

        try {
            // 使用 Files.walk 递归遍历文件夹，并删除文件和子文件夹
            Files.walk(directory)
                    .sorted(Comparator.reverseOrder()) // 先删除子文件和子文件夹，再删除文件夹本身
                    .forEach(path -> {
                        try {
                            Files.delete(path);
                        } catch (IOException e) {
                            System.err.println("Failed to delete " + path + ": " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String replaceAfterLastSlash(String text, String newText) {
        int lastSlashIndex = text.lastIndexOf('\\'); // 找到最后一个"\"的索引

        // 如果找到"/"，则进行替换
        if (lastSlashIndex != -1) {
            return text.substring(0, lastSlashIndex + 1) + newText; // 保留"\"之前的部分，并添加新文本
        }

        // 如果没有找到"/"，则返回原始文本
        return text;
    }
}
