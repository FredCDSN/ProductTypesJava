package Application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.ImportedProduct;
import entities.Product;
import entities.UsedProduct;

public class Program {

    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        List<Product> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.print("Entre com o numero de produtos: ");
        int n = sc.nextInt();
        
        for (int i = 1; i <= n; i++) {
            System.out.println("Dados do produto #" + i + ":");
            System.out.print("Comum, usado, importado (c/u/i)? ");
            char type = sc.next().charAt(0);
            System.out.print("Nome: ");
            sc.nextLine();
            String name = sc.nextLine();
            System.out.print("Preco: ");
            double price = sc.nextDouble();
            
            if (type == 'c') {
                list.add(new Product(name, price));
            } 
            else if (type == 'u') {
                System.out.print("Data de fabricacao (DD/MM/YYYY): ");
                sc.nextLine(); 
                String dateStr = sc.nextLine();
                Date manufactureDate = sdf.parse(dateStr);
                list.add(new UsedProduct(name, price, manufactureDate));
            } 
            else {
                System.out.print("Taxa de importacao: ");
                double customsFee = sc.nextDouble();
                list.add(new ImportedProduct(name, price, customsFee));
            }
        }

        System.out.println();
        System.out.println("ETIQUETAS DE PRECO:");
        for (Product prod : list) {
            System.out.println(prod.priceTag());
        }
        
        sc.close();
    }
}