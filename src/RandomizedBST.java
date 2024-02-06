import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class RandomizedBST implements TaxEvasionInterface {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------MENU---------------");
        System.out.println("1.Insert Depositor \n2.Insert Depositor from a file \n3.Update savings\n4.Find Depositor by AFM");
        System.out.println("5.Find Depositor by Last Name \n6.Remove Depositor by AFM \n7.Get Mean Savings \n8.Get the top large depositors");
        System.out.println("9.Print depositors \n10. Exit");
        System.out.println("-----------------------------------");
        System.out.println("Enter an option: ");
        int op = scanner.nextInt();

        RandomizedBST obj = new RandomizedBST();        // an object so i can use the non static methods in the static method main
        while (op >= 1 && op < 10) {        // the menu
            if (op == 1) {
                System.out.println("Enter AFM : ");
                int AFM = scanner.nextInt();
                System.out.println("Enter First Name : ");
                String firstName = scanner.next();
                System.out.println("Enter Last Name : ");
                String lastName = scanner.next();
                System.out.println("Enter savings : ");
                double savings = scanner.nextDouble();
                System.out.println("Enter taxed Income : ");
                double taxedIncome = scanner.nextDouble();
                LargeDepositor item = new LargeDepositor(AFM, firstName, lastName, savings, taxedIncome);
                obj.insert(item);
            } else if (op == 2) {
                System.out.println("Give the file Name : ");
                String fileName = scanner.next();
                obj.load(fileName);
            } else if (op == 3) {
                System.out.println("Enter the AFM : ");
                int AFM = scanner.nextInt();
                if(obj.FoundMain(AFM)) {
                    System.out.println("Enter the new savings : ");
                    double savings = scanner.nextDouble();
                    obj.updateSavings(AFM, savings);
                }else{
                    System.out.println("the Depositor not found");
                }
            } else if (op == 4) {
                System.out.println("Enter AFM : ");
                int AFM = scanner.nextInt();
                obj.searchByAFM(AFM);
            } else if (op == 5) {
                System.out.println("Enter last name : ");
                String lastName = scanner.next();
                obj.searchByLastName(lastName);
            }else if (op == 6) {
            System.out.println("Enter AFM : ");
            int AFM = scanner.nextInt();
            obj.remove(AFM);
        } else if (op == 7) {
            System.out.println("Mean Savings: " + obj.getMeanSavings());

        } else if (op == 8) {
            System.out.println("Enter the number of depositors you would like to see  : ");
            int k = scanner.nextInt();
            obj.printTopLargeDepositors(k);

        } else if (op == 9) {
            System.out.println("Printing all depositors sorted by AFM : ");
            obj.printByAFM();
        }else
                System.out.println("Exit");

            System.out.println("choose an option : ");
            op = scanner.nextInt();
        }
    }

    private boolean FoundMain(int key){     // a method to use in the main method so we can see ig the depositor in option 3 existing before entering new savings
        if (root == null)
            return false;
        if (key == root.item.key())
            return true;
        if (less(key, root.item.key()))
            return Found(key, root.left);
        else
            return Found(key, root.right);


    }


    private class TreeNode {        // the treeNode class

        LargeDepositor item;
        TreeNode left;
        TreeNode right;
        int N;

        TreeNode(LargeDepositor item) {
            this.item = item;
            this.left = null;
            this.right = null;
            this.N = 1;
        }
    }

    private TreeNode root;      // the root of the tree

    private TreeNode insertAsRoot(LargeDepositor item, TreeNode h) {
        if (h == null) return new TreeNode(item);
        if (Math.random() * (h.N + 1) < 1.0)
            return insertAsRoot(item, h);
        if (less(item.key(), h.item.key()))     // if the AFM of the given item is smaller than the AFM of the node, we are going to the left subtree to insert the depositor
            h.left = insertAsRoot(item, h.left);
        else
            h.right = insertAsRoot(item, h.right);  // if the AFM of the given item is bigger than the AFM of the node, we are going to the right subtree to insert the depositor
        h.N++;      // the number of nodes increases by 1
        return h;      // the node that th depositor entered
    }


    private boolean less(int i, int j) {        // the less method to check the AFM
        return i < j;
    }


    private TreeNode FoundNode(int key, TreeNode h) {   // a method that founds the node with a specific key (this method returns the node )
        if (h == null)
            return null;
        if (key == h.item.key())
            return h;
        if (less(key, h.item.key()))
            return FoundNode(key, h.left);
        else
            return FoundNode(key, h.right);
    }

    private boolean Found(int key, TreeNode h) {    // a method that founds the node with a specific key (this method returns true or false )
        if (h == null)
            return false;
        if (key == h.item.key())
            return true;
        if (less(key, h.item.key()))
            return Found(key, h.left);
        else
            return Found(key, h.right);

    }
    
    @Override
    public void insert(LargeDepositor item) {
        if (Found(item.key(), root)) { // if the root has the same key with the item
            System.out.println("There is already a Depositor with this AFM");
        } else {
            root = insertAsRoot(item, root);    // The method insert to the tree the given depositor
        }
    }

    @Override
    public void load(String fileName) {
        String line = "";
        //int count = 0;
        File file = new File("C:\\Users\\tsiok\\OneDrive\\Desktop\\project3\\src\\" + fileName );

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while ((line = br.readLine()) != null) {
                //count ++;
                String[] temp = line.split("\\s+");
                int id= Integer.parseInt(temp[0]);
                String FirstName = temp[1];
                String LastName =  temp[2];
                double Savings = Double.parseDouble(temp[3]);
                double TaxedIncome = Double.parseDouble(temp[4]);
                LargeDepositor dep = new LargeDepositor(id,FirstName,LastName,Savings,TaxedIncome);
                insert(dep);
            }
            br.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    @Override
    public void updateSavings(int AFM, double savings) {        // it updates the savings to the depositor with the given AFM
        TreeNode n = FoundNode(AFM, root);      // n is equal to the node that has the given AFM
        if (n != null) {
            System.out.println("Old savings: " + n.item.getSavings());
            n.item.setSavings(savings);
            System.out.println("Updated savings: " + n.item.getSavings());
        }
    }

    public LargeDepositor searchAFM2(TreeNode h, int key) {     // this method is searching an AFM in the tree with a given node and returns the depositor with this AFM
        if (h == null) {
            return null;
        }
        if (h.item.key() == key) {
            return h.item;
        }
        if (less(key, h.item.key())) {      // if the AFM is not equal to the given key then search in the subtrees
            return searchAFM2(h.left, key);
        } else {
            return searchAFM2(h.right, key);

        }
    }

    @Override
    public LargeDepositor searchByAFM(int AFM) {
        LargeDepositor depositor = searchAFM2(root, AFM);   // the specific node contains the depositor with the given AFM and the method prints all the details abut the specific depositor
        if (depositor == null) {
            System.out.println("there is not such AFM in the list");
            return null;
        } else {
            System.out.println("First name: " + depositor.getFirstName() + "\nLast name: " + depositor.getLastName() + "\nSavings: " + depositor.getSavings() + "\nTaxed Income: " + depositor.getTaxedIncome());
            return depositor;
        }
    }

    public void searchLN(TreeNode h, String LN) {       // this method search a specific node that contains the given last name and insert the depositor with the given last name in the list
        if (h != null) {
            if (LN.equals(h.item.getLastName()))
                list.insertAtFront(h.item);
            searchLN(h.left, LN);
            searchLN(h.right, LN);
        }

    }


    List list = new List();

    @Override
    public List searchByLastName(String last_name) {
        list.empty();       // the list every time the user calls the method has to be empty so that when the user calls the method more than 1 time it will print only the requested depositors
        searchLN(root, last_name);
        NodeList current = list.getFirstNode();     // this initialize the list node
        if (current == null) {
            System.out.println("there wasn't any depositor with this name");
            return null;
        }
        while (current != null) {
            LargeDepositor currentDepositor = current.getData();    // the current's depositor data
            System.out.println("AFM: " + currentDepositor.getAFM() + "\nFirst name: " + currentDepositor.getFirstName() + "\nLast name: " + currentDepositor.getLastName() + "\nSavings: " + currentDepositor.getSavings() + "\nTaxed Income: " + currentDepositor.getTaxedIncome() + "\n");
            current = current.getNext();    // the next node of the list
        }

        return list;
    }

    private TreeNode removeAFM(TreeNode h,int AFM ){        // this method removes the node that contains a depositor from the tree with the specific AFM
        if(h == null){
            return null;
        }
        if(less(AFM,h.item.key())){
            h.left = removeAFM(h.left , AFM);
        }
        if(less(h.item.key(), AFM)){
            h.right = removeAFM(h.right , AFM);
        }
        if(h.item.key() == AFM){
            h = join(h.left , h.right);
        }
        return h;
    }

    private TreeNode join(TreeNode a, TreeNode b){      // this method merge the two trees into one tree with randomised method, because we removed a node from the tree and we would like to restore the tree

        if(a == null){
            return b;
        }
        if(b == null){
            return a;
        }
        int N = a.N + b.N;
        if(Math.random() * N <1.0*a.N){
            a.right = join(a.right , b);
            return a;
        }
        else{
            b.left = join(a,b.left);
            return b;
        }
    }

    @Override
    public void remove(int AFM){    // this method removes the depositor with the given AFM
        if(Found(AFM,root)){
            root = removeAFM(root,AFM);
            System.out.println("Removed succeed!!!!");
        }
        else{
            System.out.println("there wasn't any depositor with this AFM... Remove Failed...");
        }

    }

    private int count(TreeNode h){  // this method count the nodes of the tree
        if(h == null)
            return 0;
        else
            return 1 + count(h.left) + count(h.right);
    }

    private double MeanSavings(TreeNode h){     // this method calculates the mean savings of a specific node
        if(h == null){
            return 0.0;
        }
        LargeDepositor d = h.item;
        double left = MeanSavings(h.left);      // all the mean savings of the subtrees
        double right = MeanSavings(h.right);

        double total = (d.getSavings() + left + right ) / (count(h)); // we add the savings of the given depositor + the savings of all the depositors from the left and right and divided by the count of he nodes until the given node
        return total;
    }

    @Override
    public double getMeanSavings(){ // this method calculates all the depositors mean savings
       return MeanSavings(root);
    }

    private String toStringHelp(TreeNode h){    // this method print sorted by AFM  the details of the depositor from the given node and after
        if(h == null)
           return "";
        String s = toStringHelp(h.left);
        s+= h.item.toString() + "\n";
        s+= toStringHelp(h.right);
        return s;
    }
    PQ pq = new PQ();   // an item for the priority queue
    @Override
    public void printTopLargeDepositors(int k){     // this method prints the top k largest depositors
        List top = new List();      // a list with the top depositors
        addDep();       // add all depositors of the tree in the priority queue
        if(pq.size() < k ){
            System.out.println("the isn't that number of depositors in the list ");
        }else {
            System.out.println("The top " + k + " depositors are : ");
            for (int i = 0; i < k; i++) {
                LargeDepositor ld = pq.getmax();
                top.insertAtFront(ld);      // inserts in the list the current large depositor
            }
            NodeList curr = top.getFirstNode();
            while (curr != null) {
                LargeDepositor lds = curr.getData();
                System.out.println(lds);        // this prints the top k depositors in the list
                curr = curr.getNext();
            }
        }
    }

    private void addDep(){
        addDepHelp(root);       // adds all depositors from the tree in the priority queue
    }

    private void addDepHelp(TreeNode node){
        if(node!= null){
            addDepHelp(node.left);      // it adds  the depositor in the given node and the subtrees  in the priority queue
            pq.insert(node.item);
            addDepHelp(node.right);

        }
    }

    @Override
    public void printByAFM(){       // this method prints sorted by AFM all the depositors
        System.out.println(toStringHelp(root));
    }

}












