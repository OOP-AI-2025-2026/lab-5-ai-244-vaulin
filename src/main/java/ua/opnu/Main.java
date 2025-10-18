package ua.opnu;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Іваненко", "Марія", 40);
        Student s1 = new Student("Петренко", "Олег", 19, "КН-23", "ST12345");
        Student s2 = new Student("Коваленко", "Ірина", 20, "ІТ-24", "ST67890");
        Lecturer l1 = new Lecturer("Гриценко", "Світлана", 45, "Комп’ютерних наук", 25000.0);
        Lecturer l2 = new Lecturer("Сидоренко", "Віктор", 50, "Математики", 28000.0);

        Person[] people = {p1, s1, s2, l1, l2};

        for (Person person : people) {
            System.out.println(person.toString());
        }
    }
}
