import static javax.swing.JOptionPane.*;

public class DoubleLinkedList {
    private Node head = null;
    private Node tail = null;
    private int listSize = 0;

    public void addFirst(int value){
        head = new Node(value, head, null);

        if (tail == null) tail = head;
        else head.next.prev = head;
        ++listSize;
    }

    public void addBack(int value){

        Node newNode = new Node(value, null, tail);
        if (tail != null) tail.next = newNode;
        else head = newNode;
        tail = newNode;
        ++listSize;
    }

    public Node removeNode(Node n){
        if (n.prev != null) n.prev.next = n.next;
        else head = n.next;

        if (n.next != null) n.next.prev = n.prev;
        else  tail = n.prev;

        n.next = null;
        n.prev = null;

        --listSize;

        return n;
    }

    public Node getHead() {
        return head;
    }

    public int getListSize() {
        return listSize;
    }

    public void setHead(Node head) {
        this.head = head;
    }
}

class Node{
    int data;
    Node next;
    Node prev;

    public Node(int d, Node n, Node p){
        data = d;
        next = n;
        prev = p;
    }

    public int getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrev() {
        return prev;
    }
}

class Iterator{
    private Node place;

    public Iterator(DoubleLinkedList l){
        place = l.getHead();
    }

    public boolean end(){
        return place == null;
    }

    public int findData(){
        if (!end()) return place.getData();
        else return (int)Double.NaN;
    }

    public void next(){
        if (!end()) place = place.getNext();
    }

    public Node getPlace() {
        return place;
    }
}

class Operations {
    int place;
    DoubleLinkedList greatest;
    DoubleLinkedList smallest;

    public void addition(DoubleLinkedList l, Node a, int b){
        a.data = a.data + b;

        if (a.data > 9) {
            if (a.getPrev() == null)
            {
                l.addFirst(0);
            }
            a.data = a.data - 10;
            addition(l, a.getPrev(), 1);
        }
    }

    public void subtract(DoubleLinkedList l, Node a, int b){
        a.data = a.data - b;

        if (a.data < 0) {

            a.data = a.data + 10;
            subtract(l, a.getPrev(), 1);

            if (l.getHead().getData() == 0){
                l.removeNode(l.getHead());
                l.setHead(a);
            }
        }
    }

    public void subOrAddHelper(int subOrAd, DoubleLinkedList l1, DoubleLinkedList l2){
        findLargest(l1, l2);
        Iterator iter = new Iterator(greatest);
        Iterator iter2 = new Iterator(smallest);

        if (subOrAd == 1){
            while (!(iter.end() || iter2.end())){
                subtract(greatest, iter.getPlace(), iter2.findData());
                iter.next();
                iter2.next();
            }
        }else if (subOrAd == 2){
            while (!(iter.end() || iter2.end())){
                addition(greatest, iter.getPlace(), iter2.findData());
                iter.next();
                iter2.next();
            }
        }
    }

    public void findLargest(DoubleLinkedList l1,DoubleLinkedList l2){
        Iterator iter = new Iterator(l1);
        Iterator iter2 = new Iterator(l2);

        if (l1.getListSize() > l2.getListSize()) {
            place = l1.getListSize() - l2.getListSize();
            greatest = l1;
            smallest = l2;
        }else if(l1.getListSize() < l2.getListSize()) {
            place = l2.getListSize() - l1.getListSize();
            greatest = l2;
            smallest = l1;
        } else if (l1.getListSize() == l2.getListSize()){
            while (greatest == null && smallest == null){
                if (iter.findData() > iter2.findData()){
                    greatest = l1;
                    smallest = l2;
                } else if (iter.findData() < iter2.findData()) {
                    greatest = l2;
                    smallest = l1;
                }
                iter.next();
                iter2.next();
            }
        } else {
            greatest = l1;
            smallest = l2;
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList l1 = new DoubleLinkedList();
        DoubleLinkedList l2 = new DoubleLinkedList();
        Operations op = new Operations();

        String[] num = showInputDialog("velg et tall").split("");
        int[] tab = new int[num.length];
        for(int i = 0;i < tab.length;i++){
            tab[i] = Integer.parseInt(num[i]);
        }

        String[] num2 = showInputDialog("velg et tall").split("");
        int[] tab2 = new int[num2.length];
        for(int i = 0;i < tab2.length;i++){
            tab2[i] = Integer.parseInt(num2[i]);
        }

        for (int j : tab) {
            l1.addBack(j);
        }
        for (int i : tab2) {
            l2.addBack(i);
        }

        String actionRead = showInputDialog("skriv 1 for å subtrahere, skriv 2 for å addere");
        int action = Integer.parseInt(actionRead);

        if (action == 1){
            op.subOrAddHelper(1, l1, l2);
        } else if (action == 2){
            op.subOrAddHelper(2, l1, l2);
        }

        Iterator iter = new Iterator(op.greatest);

        while (!iter.end()){
            System.out.println(iter.findData());
            iter.next();
        }
    }
}
