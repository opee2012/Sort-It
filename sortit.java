//Jacob Borth
//borthj2@mailbox.winthrop.edu

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    void enqueue(String data) {
        Node temp = new Node(data);

        // If empty, add node as head/tail
        if (this.tail == null) this.head = this.tail = temp;
        else { // enqueue to list
            this.tail.next = temp;
            this.tail = temp;
        }
    }

    String dequeue() {
        // check for null
        if (this.head == null) return null;
        else {
            Node temp = this.head;
            this.head = this.head.next;

            if (this.head == null) this.tail = null;

            return temp.data;
        }
    }
}

public class sortit {
        public static void main(String[] args) {
        String temp;
        Queue list0 = new Queue();
        Queue list1 = new Queue();

        for (int i = 0; i < args.length; i++) {
            if (args[i].compareTo("-") != 0) {
                try {
                    File file = new File(args[i]);
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        temp = scanner.nextLine();
                        appendToList(temp, list0, list1, list0);
                    }
                    scanner.close();
                } catch (FileNotFoundException e) {
                    System.out.println("Error: Could not find input file.");
                    System.exit(1);
                }
            } else {
                Scanner input = new Scanner(System.in);
                do {
                    System.out.print("Enter a line to be sorted: ");
                    temp = input.nextLine();
                    appendToList(temp, list0, list1, list0);
                } while (temp.length() != 0);
                input.close();
            }
        }

        while (list0.head != null && list1.head != null) {
            // temp value to check if list is exhausted
            list0.enqueue(null);
            list1.enqueue(null);

            while (list0.head.data != null && list1.head.data != null) {
                if (list0.head.data.compareTo(list1.head.data) > 0) {
                    temp = list1.dequeue();
                    appendToList(temp, list0, list1, list0);
                } else {
                    temp = list0.dequeue();
                    appendToList(temp, list0, list1, list0);
                }
            }

            if (list0.head.data == null) { // list0 exhausted
                list0.dequeue();
                while (list1.head.data != null) {
                    temp = list1.dequeue();
                    appendToList(temp, list0, list1, list0);
                }
                list1.dequeue();
            } else { // list1 exhausted
                list1.dequeue();
                while (list0.head.data != null) {
                    temp = list0.dequeue();
                    appendToList(temp, list0, list1, list0);
                }
                list0.dequeue();
            }
        }

        while (list0.head != null) System.out.println(list0.dequeue());
        while (list1.head != null) System.out.println(list1.dequeue());
    }

    private static void appendToList(String object, Queue list_0, Queue list_1, Queue current_list) {
        if (current_list.tail == null || current_list.tail.data == null) {
            current_list.enqueue(object);
            return;
        }
        if (current_list.tail.data.compareTo(object) > 0) {
            if (current_list == list_0) current_list = list_1;
            else current_list = list_0;

            current_list.enqueue(object);
            return;
        }
        current_list.enqueue(object);
    }
}
