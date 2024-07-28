import java.util.ArrayList;
import java.util.List;
interface FileSystemComponent {
    void print();
}

class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void print() {
        System.out.println("File: " + name);
    }
}

class Directory implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;

    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    @Override
    public void print() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.print();
        }
    }
}

public class Composite_Pattern{
    public static void main(String[] args) {
        Directory root = new Directory("Root");
        Directory documents = new Directory("Documents");
        Directory pictures = new Directory("Pictures");

        root.addComponent(documents);
        root.addComponent(pictures);

        documents.addComponent(new File("file1.txt"));
        documents.addComponent(new File("file2.txt"));

        pictures.addComponent(new File("image1.jpg"));
        pictures.addComponent(new File("image2.jpg"));

        root.print();
    }
}