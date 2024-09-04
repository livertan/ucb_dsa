public class Dessert {
    public int flavor;
    public int price;
    public int numDesserts;
    public Dessert(int x, int y){
        this.flavor=x;
        this.price=y;
        numDesserts++;
    }
    public static void main(String[] args){
        System.out.println("I love dessert!");
    }
    public void printDessert(){
        if(this.flavor!=0){
            System.out.println(this.flavor+" "+this.price+" "+numDesserts);
        }
    }
}
