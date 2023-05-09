/**
 * MainEZ
 */
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class MainEZ {

    private static Random random = new Random();

    /**
     * TODO: 2. generateEmployee должен создавать различных сотрудников (Worker, Freelancer)
     * @return
     */
    static Employee generateEmployee(){
        String[] names = new String[] { "Анатолий", "Глеб", "Клим", "Мартин", "Лазарь", "Владлен", "Клим", "Панкратий", "Рубен", "Герман", "Владлен", "Сазон", "Гоша"};
        String[] surnames = new String[] { "Григорьев", "Фокин", "Шестаков", "Хохлов", "Шубин", "Бирюков", "Копылов", "Горбунов", "Лыткин", "Соколов", "Краснов", "Мартынов", "Халилов"};

        int salaryFixed = random.nextInt(700, 850);
        int premie = random.nextInt(300, 450);
        int salaryHour = random.nextInt(200, 300);
        int salaryTop = random.nextInt(1000, 1700);
        int indexBalast = random.nextInt(3, 5);
        int indexTop = random.nextInt(30, 70);
        int indexF = random.nextInt(5, 50);
        int experience = random.nextInt(1, 20);

        int typeWorker = random.nextInt(0, 4);
        
        if (typeWorker == 0) {
            return new Worker(names[random.nextInt(13)], surnames[random.nextInt(13)], salaryFixed + premie, experience);
        }
        else if (typeWorker == 1) {
            return new Freelancer(names[random.nextInt(13)], surnames[random.nextInt(13)], salaryHour * indexF);
        }
        else if (typeWorker == 2) {
            return new TopManager(names[random.nextInt(5)], surnames[random.nextInt(5)], salaryTop * indexTop, experience);
        }
        else
            return new Balast(names[random.nextInt(5)], surnames[random.nextInt(5)], salaryHour * indexBalast);
        
        
    }

    public static void main(String[] args) {

        Employee[] employees = new Employee[10];
        for (int i = 0; i < employees.length; i++)
            employees[i] = generateEmployee();

        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new NameComparator());
        System.out.printf("\n*** Отсортированный массив сотрудников по имени***\n\n");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees);
        System.out.printf("\n*** Отсортированный массив сотрудников по зароботку***\n\n");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new SurNameComparator());
        System.out.printf("\n*** Отсортированный массив сотрудников по фамилии***\n\n");
        for (Employee employee : employees){
            System.out.println(employee);
        }

        Arrays.sort(employees, new ExperieceComparator());
        System.out.printf("\n*** Отсортированный массив сотрудников по выслуге***\n\n");
        for (Employee employee : employees){
            System.out.println(employee);
        }
    }

}

class SalaryComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {

        //return o1.calculateSalary() == o2.calculateSalary() ? 0 : (o1.calculateSalary() > o2.calculateSalary() ? 1 : -1);
        return Double.compare( o2.calculateSalary(), o1.calculateSalary());
    }
}

class NameComparator implements Comparator<Employee> {


    @Override
    public int compare(Employee o1, Employee o2) {
        
        int res = o1.name.compareTo(o2.name);
        if (res == 0){
            res = Double.compare( o2.calculateSalary(), o1.calculateSalary());
        }
        return res;
    }
}

class SurNameComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        int res = o1.surName.compareTo(o2.surName);
        if (res == 0){
            res = Double.compare( o2.calculateSalary(), o1.calculateSalary());
        }
        return res;
    }
    
}

class ExperieceComparator implements Comparator<Employee>{

    @Override
    public int compare(Employee o1, Employee o2) {
        
        return Integer.compare(o1.experience, o2.experience);
    }
    
}

abstract class Employee implements Comparable<Employee>{

    protected String name;
    protected String surName;
    protected double salary;
    protected int experience;

    public Employee(String name, String surName, double salary) {
        this.name = name;
        this.surName = surName;
        this.salary = salary;
    }

    public Employee(String name, String surName, double salary, int experience){
        this.name = name;
        this.surName = surName;
        this.salary = salary;
        this.experience = experience;
    }

    public abstract  double calculateSalary();

    @Override
    public String toString() {
        return String.format("Сотрудник: %s %s; Среднемесячная заработная плата: %.2f", name, surName, salary);
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare( o.calculateSalary(), calculateSalary());
    }
}

class Worker extends Employee{

    public Worker(String name, String surName, double salary, int experience) {
        super(name, surName, salary, experience);
    }

    @Override
    public double calculateSalary() {
        return salary ;
    }

    @Override
    public String toString() {
       
        return String.format("%s %s; Рабочий; Среднемесячная заработная плата (фиксированная): %.2f (руб.); выслуга лет: %d", name, surName, salary, experience);
    }
}

/**
 * TODO: 1. Доработать самостоятельно в рамках домашней работы
 */
class Freelancer extends Employee{

    public Freelancer(String name, String surName, double salary) {
        super(name, surName, salary);
    }
    
    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
       
        return String.format("%s %s; Freelancer; , Заработак: %.2f (руб.)", name, surName, salary);
    }
}

class TopManager extends Employee{
    

    public TopManager(String name, String surName, double salary, int experience){
        super(name, surName, salary, experience);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; TopManager, его заработак: %.2f (руб.); выслуга лет: %d", name, surName, salary, experience);
    }


}

class Balast extends Employee{
    public Balast(String name, String surName, double salary){
        super(name, surName, salary);
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Balast, его пособие: %.2f (руб.)", name, surName, salary);
    }
}