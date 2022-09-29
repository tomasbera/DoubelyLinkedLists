public class BinarySearchTre {
    TreNode root;

    public void setInn(String e) {
        TreNode p = null;

        if (root == null) {
            root = new TreNode(e, null, null, null);
            return;
        }

        TreNode n = root;
        String sml = "";

        while (n != null) {
            p = n;
            sml = n.element;
            if (e.compareToIgnoreCase(sml) <= 0) n = n.leftChild;
            else n = n.rightChild;
        }
        if (e.compareToIgnoreCase(sml) <= 0) p.leftChild = new TreNode(e, p, null, null);
        else p.rightChild = new TreNode(e, p, null, null);
    }

    public int findDepth(TreNode n) {
        int d = -1;
        while (n != null) {
            d++;
            n = n.parent;
        }
        return d;
    }

    private int findHeight(TreNode n) {
        if (n == null) return -1;
        else {
            int lh = findHeight(n.leftChild);
            int rh = findHeight(n.rightChild);
            if (lh >= rh) return lh + 1;
            else return rh + 1;
        }
    }

    public int findHeight() {
        return findHeight(root);
    }

    //unfinished 
    /*
    public void printLevelOrdered(){
        TreNode[] listOfAll = new TreNode[14];
        int i = 0;

        Queue q = new Queue(10);
        q.addInQueue(root);
        while(!q.empty()){
            TreNode tn = q.nextInQueue();
            if (tn != null){
                listOfAll[i] = tn;
                q.addInQueue(tn.leftChild);
                q.addInQueue(tn.rightChild);
            }
            if (listOfAll[i] == null) listOfAll[i] = new TreNode("null", null, null, null);
            i++;
        }
        for (int j = 0; j < listOfAll.length; j++) {
            System.out.println(listOfAll[j].element);
        }
    }

    public void printTree(TreNode n){

    }
}*/


class Queue{
    private final TreNode[] tab;
    private int start = 0;
    private int end = 0;
    private int numb = 0;

    public Queue(int str){
        tab = new TreNode[str];
    }

    public boolean empty(){
        return numb == 0;
    }

    public boolean full(){
        return numb == tab.length;
    }

    public void addInQueue(TreNode e){
        if (full()) return;
        tab[end] = e;
        end = (end + 1)%tab.length;

        ++numb;
    }

    public TreNode nextInQueue(){
        if (!empty()){
            TreNode e = tab[start];
            start = (start + 1)%tab.length;
            --numb;
            return e;
        }
        else return null;
    }
}


class TreNode{
    String element;
    TreNode parent;
    TreNode leftChild;
    TreNode rightChild;

    public TreNode(String e, TreNode p, TreNode lc, TreNode rc) {
        this.element = e;
        this.parent = p;
        this.leftChild = lc;
        this.rightChild = rc;
    }
}


class MainClass{

    public static void main(String[] args) {
        BinarySearchTre bts = new BinarySearchTre();
        String[] listOfWords = new String[]{"hode", "ben", "legg", "albue", "hake", "tå", "arm", "tann"};


        for (String word : listOfWords) {
            bts.setInn(word);
        }

        bts.printLevelOrdered();
    }
}
