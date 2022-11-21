
class Node {
    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        this.next = null;
    }
}

class Queue {
    Node head, tail;

    //construct queue
    public Queue() {
        this.head = this.tail = null;
    }

    void Enqueue(String data) {
        Node temp = new Node(data);

        // If empty, add node as head/tail
        if (this.tail == null) this.head = this.tail = temp;
        else { // enqueue to list
            this.tail.next = temp;
            this.tail = temp;
        }
    }

    String Dequeue() {
        // check for null
        if (this.head == null) return null;
        else {
            Node temp = this.head;
            this.head = this.tail.next;

            if (this.head == null) this.tail = null;

            return temp.data;
        }
    }
}

public class sortit {
    public static void main(String[] args) {
    }
}
