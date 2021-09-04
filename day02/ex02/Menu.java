package com.school21.day02.ex02;

import java.io.File;
import java.io.IOException;

public class Menu {
    public File currentFolder;
    public Command command;

    public Menu(File folder) {
        this.currentFolder = folder;
        this.command = new Command(folder);
    }

    public void menu(String[] command) throws IOException {
        if (command.length == 1 && command[0].equals("ls")) {
            this.command.exec_ls();
        } else if (command.length == 2 && command[0].equals("cd")) {
            File folderName = this.command.checkIsFolder(command[1]);
            if (folderName != null) {
                this.command.exec_cd(folderName);
                this.currentFolder = folderName;
            }
        } else if (command.length == 3 && command[0].equals("mv")) {
            this.command.exec_mv(command[1], command[2]);
        } else if (command[0].length() == 0)
        {
            System.out.print(this.currentFolder.getCanonicalPath() + " %");
        } else
        {
            System.out.println("Команда не найдена!");
            System.out.println("ls");
            System.out.println("cd directory");
            System.out.println("mv name newname");
            System.out.println("mv name directory");
            System.out.print(this.currentFolder.getCanonicalPath() + " %");
        }
    }

    private class Command {
        public File folder;

        public Command(File file) {
            this.folder = file;
        }

        public long folderSize(File directory) {
            long length = 0;
            File[] files = directory.listFiles();

            if (files != null && files.length > 0) {
                for (File file : directory.listFiles()) {
                    if (file.exists() && file.isFile())
                        length += file.length();
                    else
                        length += folderSize(file);
                }
            }
            return length;
        }

        public void exec_ls() throws IOException {
            for (File file : folder.listFiles()) {
                long size = 0;
                if (file.isDirectory())
                    size = this.folderSize(file);
                else
                    size = file.length();
                System.out.printf("%s %d KB\n", file.getName(), size / 1000);
            }
            System.out.print(this.folder.getCanonicalPath() + " %");
        }

        public void exec_cd(File Folder) {
            if (folder.isDirectory()) {
                this.folder = Folder;
                try {
                    System.out.print(this.folder.getCanonicalPath() + " %");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public File checkIsFolder(String folderName) throws IOException {
            try {
                File newone = new File(this.folder.getCanonicalPath() + "/" + folderName);
                if (!newone.exists()) {
                    System.out.println("Folder not found!");
                    System.out.print(this.folder.getCanonicalPath() + " %");
                    return (null);
                }
                if (!newone.isDirectory()) {
                    System.out.printf("%s is not a folder\n", newone.getName());
                    System.out.print(this.folder.getCanonicalPath() + " %");
                    return (null);
                }
                return (newone);
            } catch (IOException e) {
                e.printStackTrace();
                return (null);
            }
        }

        public void exec_mv(String oldname, String newname) throws IOException {
            try {
                File newpath = new File(folder.getCanonicalPath() + "/" + newname);
                if (newpath.isDirectory()) {
                    File oldName = new File(folder.getCanonicalPath() + "/" + oldname);
                    File newName = new File(newpath.getCanonicalPath() + "/" + oldname);
                    if (newName.exists()) {
                        System.out.printf("File %s already exists!\n", newname);
                    } else if (!oldName.exists()) {
                        System.out.printf("File %s not found!\n", oldname);
                    } else {
                        oldName.renameTo(newName);
                    }
                } else {
                    File oldName = new File(folder.getCanonicalPath() + "/" + oldname);
                    File newName = new File(folder.getCanonicalPath() + "/" + newname);

                    if (newName.exists()) {
                        System.out.printf("File %s already exists!\n", newname);
                    } else if (!oldName.exists()) {
                        System.out.printf("File %s not found!\n", oldname);
                    } else {
                        oldName.renameTo(newName);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print(this.folder.getCanonicalPath() + " %");
        }
    }
}
