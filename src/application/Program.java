/*
 * Fazer um programa para ler os dados (nome, email e salário)
de funcionários a partir de um arquivo em formato .csv.
    Em seguida mostrar, em ordem alfabética, o email dos
funcionários cujo salário seja superior a um dado valor
fornecido pelo usuário. 
    Mostrar também a soma dos salários dos funcionários cujo
nome começa com a letra 'M'.
 */
package application;

import entities.Employee;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author Elvis
 */
public class Program {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter full file path: ");
        String path = sc.nextLine();
        
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<Employee> list = new ArrayList<>();
            
            String line = br.readLine();
            while (line != null) {
                String[] fields = line.split(",");
                list.add(new Employee(fields[0], fields[1], Double.parseDouble(fields[2])));
                line = br.readLine();
            }
            System.out.println("Enter salary: ");
            Double sal = sc.nextDouble();
            
            System.out.println("Email of people whose salary is more than " + String.format("%.2f", sal) + ":");
            
            List<String> emails = list.stream()
                    .filter(e -> e.getSalary() > sal)
                    .map(e -> e.getEmail())
                    .sorted()
                    .collect(Collectors.toList());
            
            emails.forEach(System.out :: println);
            
            double sum = list.stream()
                    .filter(e -> e.getName().charAt(0) == 'M')
                    .map(e -> e.getSalary())
                    .reduce(0.0, (x,y) -> x + y);
            
            System.out.print("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sum));
            
        }catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        sc.close();
    }
}
