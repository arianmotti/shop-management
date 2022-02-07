package com.company;
import java.util.Collections;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Shop shop;
        HashMap<Integer, Discount> takhfifha = new HashMap<>();

        shop = new Shop("shop");
        String input;
        while (true) {
            input = in.next();
            if (input.equals("add")) {
                input = in.next();
                if (input.equals("customer")) {
                    int id = in.nextInt();
                    String name = in.next();
                    Customer moshtari = new Customer(name, id);
                    shop.addCustomer(moshtari);
                } else if (input.equals("good")) {
                    int shenase = in.nextInt();
                    String esm = in.next();
                    int price = in.nextInt();
                    Good good=shop.getGbyID(shenase);
                    if(!(shop.getGood().contains(good))) {
                        good = new Good(esm, shenase, price);
                        shop.addGood(good);
                    }
                    int tedad = in.nextInt();
                    shop.increamentGood(good, tedad);


                } else if (input.equals("repository")) {
                    int Id = in.nextInt();
                    int capacity = in.nextInt();
                    Repository anbar = new Repository(Id, capacity);
                    shop.addRepository(anbar);
                } else if (input.equals("order")) {
                    int shenaseDarkhast = in.nextInt();
                    int shenaseKaarbar = in.nextInt();

                    for (int i = 0; i < shop.getCustomers().length; i++) {

                        if (shop.getCustomers()[i].getID() == shenaseKaarbar) {
                            Order order = new Order(shenaseDarkhast, shop.getCustomers()[i]);
                            shop.getCustomers()[i].addOrder(order);
                            break;
                        }
                    }


                } else if (input.equals("balance")) {
                    int SM = in.nextInt();
                    int balance = in.nextInt();

                    for (int i = 0; i < shop.getCustomers().length; i++) {
                        if (shop.getCustomers()[i].getID() == SM) {
                            shop.getCustomers()[i].setBalance(shop.getCustomers()[i].getBalance() + balance);
                            break;
                        }

                    }

                } else if (input.contains("item")) {
                    int SDK = in.nextInt();
                    int Sk = in.nextInt();
                    int TK = in.nextInt();
                    Order o = shop.getObyID(SDK);
                    o.addItem(shop.getGbyID(Sk), TK);


                } else if (input.contains("discount")) {
                    int ST = in.nextInt();
                    int DT = in.nextInt();
                    Discount takhfif = new Discount(ST, DT);
                    shop.addDiscount(takhfif);
                    takhfifha.put(takhfif.getID(), takhfif);
                }


            } else if (input.contains("report")) {
                input = in.next();
                if (input.contains("customers")) {
                    for (Customer c : shop.getCustomers()) {
                        System.out.println(c.report());
                    }

                } else if (input.contains("repositories")) {
                    for (int i = 0; i < shop.getRepositories().length; i++)
                        System.out.println(shop.getRepositories()[i].getId() + "," + shop.getRepositories()[i].getCapacity() + "," + shop.getRepositories()[i].getFreeCapacity());

                } else if (input.contains("income")) {
                    System.out.println(shop.getIncome());

                }

            } else if (input.contains("remove")) {
                input = in.next();
                if (input.contains("item")) {
                    int SD = in.nextInt();
                    int SK = in.nextInt();

                    shop.getObyID(SD).removeItem(shop.getGbyID(SK));
                }
            } else if (input.contains("submit")) {
                input = in.next();
                if (input.contains("order")) {
                    int SKH = in.nextInt();
                    boolean zade = false;
                    Order o = shop.getObyID(SKH);

                    //  if (o == null) {
                    //    System.out.println("salam");
                    //}
                    if (o.getMoshtari().getBalance() >= o.calculatePrice()) {
                        // System.out.println("55555");
                        for (Map.Entry<Good, Integer> goodEntry :
                                o.getItems().entrySet()) {
                            // for(Map.Entry<Good,Integer> entry :shop.getLook().entrySet() )
                            //System.out.println(goodEntry.getValue()+"????" +entry.getKey()+"===="+entry.getValue());
                            if (goodEntry.getValue() <= shop.getLook().get(goodEntry.getKey())) {
                                // System.out.println("44444444");
                                zade = true;
                                break;
//                            }
                            }
//


                        }
                    }
                    if (zade) {
                        o.setStatus("submitted");
                        o.getMoshtari().setSubmitted(o);
                        o.getMoshtari().setBalance(o.getMoshtari().getBalance() - o.calculatePrice());
                        shop.setIncome(shop.getIncome() + o.calculatePrice());
                        shop.sortRbyID();
                        for (Map.Entry<Good, Integer> goodEntry :
                                o.getItems().entrySet()) {
                            for (Repository repo :
                                    shop.getRepositories()) {
                                HashMap<Good, Integer> repoItems = repo.getGoods();
                                if (repoItems.containsKey(goodEntry.getKey())) {
                                    if (repoItems.get(goodEntry.getKey()) >= goodEntry.getValue()) {
                                        repoItems.put(goodEntry.getKey(), repoItems.get(goodEntry.getKey()) - goodEntry.getValue());
                                        repo.removeGood(goodEntry.getKey(),goodEntry.getValue());
                                        break;

                                    }
                                }
                            }
                        }
//                        for (int i = 0; i < o.getJens().size(); i++)
//                            for (int j = 0; j < shop.getRepositories().length; j++) {
//                               // System.out.println(i+" "+j+" "+o);
////                                //System.out.println(shop.getRepositories()[i]+"+"+shop.getRepositories()[j]);
////                                if (shop.getRepositories()[j].getGoods().get(o.getJens().get(i)) > o.getItems().get(o.getJens().get(i))) {
////                                    shop.getRepositories()[j].removeGood(o.getJens().get(i), o.getItems().get(o.getJens().get(i)));
////                                    break;
////                                }
//
//                            }
//
                    }
                }
                else if (input.contains("discount")) {
                    int SD = in.nextInt();
                    int ST = in.nextInt();
                    shop.addDiscount(takhfifha.get(ST), shop.getObyID(SD));}

            } else if (input.contains("discount")) {
                int SD = in.nextInt();
                int ST = in.nextInt();
                shop.addDiscount(takhfifha.get(ST), shop.getObyID(SD));


            }
            else if (input.equals("terminate"))

                break;


        }
    }
}
class Customer {
    private String name;
    private int ID;
    private int balance;
    private ArrayList<Order> darkhastha = new ArrayList<Order>();
    private ArrayList<Order> submitted = new ArrayList<Order>();
    private ArrayList<Order> pending = new ArrayList<Order>();
    private Shop shop;

    public void setShop(Shop shop) {
        this.shop = shop;
    }


    public Customer(String name, int ID) {
        this.ID = ID;
        this.name = name;


    }

    public int getID() {
        return this.ID;
    }

    public String getName() {
        return this.name;
    }

    public void setBalance(int amount) {
        this.balance = amount;
    }

    public int getBalance() {
        return balance;
    }

    public void addOrder(Order order) {
        darkhastha.add(order);

    }

    public Order[] getTotalOrders() {
        Order[] To = new Order[darkhastha.size()];
        for (int i = 0; i < darkhastha.size(); i++)
            To[i] = darkhastha.get(i);
        return To;
    }

    public Order[] getPendingOrders() {
        Order[] Po = new Order[pending.size()];
        for (int i = 0; i < pending.size(); i++) {
            Po[i] = pending.get(i);


        }
        return Po;

    }

    public Order[] getSubmittedOrders() {
        Order[] So = new Order[submitted.size()];
        for (int i = 0; i < submitted.size(); i++)
            So[i] = submitted.get(i);
        return So;

    }
    public String report(){
        return this.getID()+","+this.getName()+","+ this.getBalance() + "," +darkhastha.size()+","+submitted.size();
    }

    public void setSubmitted(Order order) {
        submitted.add(order);
    }

    public void submitOder(Order order) {
        submitted.add(order);
        this.balance -= order.calculatePrice();
        order.setStatus("submittesd");

    }

}
class Good {
    private String name;
    private int ID;
    private int price;

    public Good(String name, int ID, int price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setID(int ID) {

        this.ID = ID;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getPrice() {

        return price;
    }

    public int getID() {

        return ID;
    }

    public String getName() {

        return name;
    }
}
class Order {
    private int ID;
    private Customer moshtari;
    private ArrayList<Good>  jens=new ArrayList<>();
    String status = "pending";
    HashMap<Good, Integer> list = new HashMap<Good, Integer>();
    Discount discount;

    public Order(int ID, Customer moshtari) {
        this.ID = ID;
        this.moshtari = moshtari;
    }

    public Customer getMoshtari() {
        return moshtari;
    }

    public int getID() {
        return ID;
    }

    public void setStatus(String status) {
        if (status.equals("pending") || status.equals("submitted"))
            this.status = status;
    }

    public ArrayList<Good> getJens() {
        return jens;
    }

    public String getStatus() {

        return this.status;
    }

    public void addItem(Good good, int amount) {

        if (status.equals("pending")) {
            if (list.containsKey(good)) {
                list.replace(good, list.get(good) + amount);

            } else {
                jens.add(good);
                list.put(good, amount);
            }
        }

    }

    public void removeItem(Good good) {
        if (status.equals("pending")) {
            if (list.containsKey(good)) {
                list.remove(good);
            }
        }

    }

    public HashMap<Good, Integer> getItems() {
        return this.list;
    }

    public void addDiscount(Discount discount) {
        if (this.discount == null) {
            if (this.getStatus().equals("pending")) {
//                discount.setOrder(this);
//                if (discount.setOrder(this)) {
                this.discount = discount;
                calculatePrice();
            }

        }
    }
        /*System.out.println("1111111111111111");
        discount.setOrder(this);
        if (discount.setOrder(this))
            this.discount = discount;*/



    public void setMoshtari(Customer moshtari) {
        this.moshtari = moshtari;
    }

    public int calculatePrice() {
        int sum = 0;
        for (Good key : list.keySet()) {
            sum += key.getPrice() * list.get(key);
        }
        if(discount!=null) {
            sum = (100 - discount.getPercentage()) * sum / 100;
        }
        return sum;
    }


}
class Repository {
    private int id;
    private int capacity;
    private int tedad = 0;
    private HashMap<Good, Integer> mojoodi = new HashMap<Good, Integer>();


    public Repository(int id, int capacity) {
        this.id = id;
        this.capacity = capacity;

    }

    public int getId() {

        return id;
    }

    public int getCapacity() {

        return capacity;
    }


    public int getFreeCapacity() {
        return capacity - tedad;
    }

    public HashMap<Good, Integer> getGoods() {
        return mojoodi;
    }

    public void addGood(Good g, int amount) {
        if(amount<=getFreeCapacity()) {
            if (mojoodi.containsKey(g)) {
                mojoodi.replace(g, mojoodi.get(g) + amount);

            } else {
                mojoodi.put(g, amount);
            }
            tedad += amount;
        }

    }

    public void removeGood(Good g, int amount) {
        if(mojoodi.containsKey(g)){
            mojoodi.replace(g,mojoodi.get(g)-amount);
            tedad-=amount;
        }

    }
}
class Discount {
    private int ID;
    private int persent;
    private Order order = null;
    public boolean usage=false;

    public boolean isUsage() {
        return usage;
    }

    public void setUsage(boolean usage) {
        this.usage = usage;
    }

    public Discount(int ID, int persent) {
        this.ID = ID;
        if (persent >= 0 && persent <= 100)
            this.persent = persent;
    }

    public boolean setOrder(Order order) {
        if (this.order == null) {
            this.order = order;
            usage=true;
            return true;
        } else
            return false;


    }

    public int getID() {
        return ID;
    }

    public int getPersent() {
        return persent;
    }

    public Order getOrder() {
        return order;


    }


    public int getPercentage() {
        return this.persent;
    }
}
class Shop {
    private String name;
    private ArrayList<Customer> moshtariha = new ArrayList<Customer>();
    private ArrayList<Repository> anbarha = new ArrayList<Repository>();
    private ArrayList<Good> kalaha = new ArrayList<Good>();
    private HashMap<Good, Integer> sold = new HashMap<Good, Integer>();
    private ArrayList<Discount> takhfif = new ArrayList<Discount>();
    private HashMap<Good, Integer> look = new HashMap<Good, Integer>();
    private int income = 0;

    public Shop(String name) {
        this.name = name;

    }

    public void addCustomer(Customer c) {
        moshtariha.add(c);

    }

    public Customer[] getCustomers() {
        Customer[] m = new Customer[moshtariha.size()];
        for (int i = 0; i < moshtariha.size(); i++)
            m[i] = this.moshtariha.get(i);


        return m;
    }

    public void addRepository(Repository r) {
        anbarha.add(r);
    }

    public Repository[] getRepositories() {
        Repository r[] = new Repository[anbarha.size()];
        for (int i = 0; i < anbarha.size(); i++)
            r[i] = anbarha.get(i);
        return r;
    }

    public int getIncome() {
        return this.income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void addGood(Good g) {
        kalaha.add(g);
    }

    public HashMap<Good, Integer> getLook() {
        return look;
    }

    public void increamentGood(Good g, int amount) {
        for (int i = 0; i < anbarha.size(); i++)
            for (int j = 0; j < anbarha.size()-1; j++)
                if (anbarha.get(j).getFreeCapacity() > anbarha.get(j + 1).getFreeCapacity()) {
                    Collections.swap(anbarha, j, j + 1);
                }
        for (int i = 0; i < anbarha.size(); i++) {
            if (anbarha.get(i).getFreeCapacity() >= amount) {

                anbarha.get(i).addGood(g, amount);
                if (look.containsKey(g)) {
                    look.put(g, look.get(g) + amount);

                } else {
                    look.put(g, amount);
                }
                break;
            }

        }


    }


    public ArrayList<Good> getGood() {
      /*  Good[] g = new Good[kalaha.size()];
        for (int i = 0; i < kalaha.size(); i++) {
            g[i] = kalaha.get(i);
        }
        return g;
*/
        return kalaha;
    }

    public void addDiscount(Discount d, Order c) {
        d.setOrder(c);
        c.addDiscount(d);
        c.calculatePrice();

    }

    public HashMap<Good, Integer> getItemsSold() {
        return sold;

    }

    public void addDiscount(Discount discount) {

        takhfif.add(discount);


    }

    public Customer getCbyID(int ID) {
        for (Customer c : moshtariha) {
            if (c.getID() == ID) {
                return c;
            }
        }
        return null;
    }

    public Good getGbyID(int ID) {
        for (Good g : kalaha) {
            if (g.getID() == ID) {
                return g;
            }
        }

        return null;
    }

    public Order getObyID(int ID) {
        for (int i = 0; i < moshtariha.size(); i++) {
            for (int j = 0; j < moshtariha.get(i).getTotalOrders().length; j++) {
                // System.out.println(ID + " *** " + moshtariha.get(i).getTotalOrders()[j].getID());
                if (moshtariha.get(i).getTotalOrders()[j].getID() == ID) {
                    return moshtariha.get(i).getTotalOrders()[j];
                }
            }
        }
        return null;
    }
    public void sortRbyID(){
        for (int i = 0; i < anbarha.size(); i++)
            for (int j = 0; j < anbarha.size()-1; j++)
                if (anbarha.get(j).getId() > anbarha.get(j + 1).getId()) {
                    Collections.swap(anbarha, j, j + 1);
                }
    }


}




