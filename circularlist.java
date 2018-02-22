import java.util.*;
class circularlist{
	static class Node {
		int data;
		Node next;

		public Node(int value){
			data = value;
		}
	}
	static int temp = 0;
	static int length = 0,save;
	static Node head = null, last = null;
	public void addAtFront(Node node){
		node.next = head;
		head = node;
		length+=1;
		circular();
	}
	public Node searchNode(int value){
		Node ptr = head;
		int i = 0;
		while(i<length && ptr.next.data != value){
			ptr = ptr.next;
			i++;
		}
		return ptr;
	}
	public void insertNode(int info, int value){
		Node ptr = searchNode(value);
		Node newNode = new Node(info);
		if(ptr == last)
		{
			addAtEnd(newNode);
			circular();
		}
		else{
			newNode.next = ptr.next.next;
			ptr.next.next = newNode;
		}
		length+=1;
	}
	public void addAtEnd(Node node){
		if(head == null)
			head = node;
		else{
				Node ptr = head;
				while(ptr.next != null)
					ptr = ptr.next;
				ptr.next = node;
		}
		length+=1;
	}
	public void delNode(int value){
		Node ptr = searchNode(value);
		ptr.next = ptr.next.next;
		length-=1;
	}
	public void insertPos(int pos , int value){
			Node ptr = Position(pos);
			Node newNode = new Node(value);
			newNode.next = ptr.next;
			ptr.next = newNode;
			length+=1;
	}
	public void deletePos(int pos){
			Node ptr = Position(pos);
			ptr.next = ptr.next.next;
			length-=1;
	}
	public void removeFront(){
		head = head.next;
		length-=1;
		circular();
	}
	public Node Position(int pos){
		Node ptr = head;
		int i = 0;
		while ( i < pos-2 && ptr!=null){
			ptr = ptr.next;
			i++;
		}
		return ptr;
	}
	public void reversePrint(Node ptr){
		if (ptr == last)
			return;
		reversePrint(ptr.next);
		if(temp == 0){
			System.out.print(ptr.data);
			temp = 1 ;
		}
		else
		System.out.print(" -> "+ptr.data);
	}
	public void circular(){
		Node ptr = Position(length);
		last = ptr.next;
		last.next = head;

	}
	public Node reverse(Node ptr){
		Node current = ptr;
		Node prev = null;
		Node next = null;
		while(current != last){
			next = current.next;
			current.next = prev;
			prev = current;
			current= next;
		}
		ptr = prev;
		return ptr;
	}
	public void printList(){
		Node ptr = head;
		int i =0;
		while(i<length){
			System.out.print( ptr.data +" -> ");
			ptr = ptr.next;
			i++;
		}
		System.out.println(last.next.data+" .");
	}
	public static void main(String args[]){
		circularlist L = new circularlist();
		int i,j,num,k;
		System.out.print("Give Data to Store in a Link List.\nPress -1 for end of input\n");
		Scanner sc = new Scanner(System.in);
		while(true){
			num = sc.nextInt();
			if(num == -1){
				break;
			}
			L.addAtEnd(new Node(num));
		}
		L.circular();
		System.out.println("Given list is :-");
		L.printList();
		while(true){
			System.out.println("Choose The Operation You Want to perform on the Linked List.");
			if (length == 0){
				System.out.println("The list is empty. You cannot perform any operation\n");
				break;
			}
			System.out.println("\b\b\b\n1. Insert\t\t 2.Delete\n\n3.Reverse List \t\t 4.Reverse Print\n\n5.Print \t\t 6.Get Size of list \n\n7.Data at any Position \t\t 8.Exit");
			num  = sc.nextInt();
			if(num == 8)
				break;
			else if(num == 7){
				System.out.print("Give the position : ");
				System.out.println("Data at the  given position is "+L.Position(sc.nextInt()).data);
			}
			else if(num == 6){
			System.out.println("Length of list is "+length);	
			}
			else if (num == 5)
				L.printList();
			else if (num == 4){
				L.reversePrint(head);
				System.out.print(" .");
			}
			else if (num == 3){
				head = L.reverse(head);
				System.out.println("The list is reversed Successfully.\nThe reversed list is shown");
				Node ptr = head;
				while(ptr.next != null){
				System.out.print( ptr.data +" -> ");
				ptr = ptr.next;
				}
				System.out.println(ptr.data+" .");
			}
			else if (num == 2){
				System.out.println("How you want to delete.\n1.Delete by Position.\n2.Delete any Specific Value From list. ");
				i = sc.nextInt();
				if(i==1){
					System.out.print("Give the position of Node: ");
					j = sc.nextInt();
					if(j==1)
						L.removeFront();
					else
						L.deletePos(j);
				}
				else{
					System.out.print("Give the value in the list you want to delete: ");
					j = sc.nextInt();
					if(j == head.data)
						L.removeFront();
					else
						L.delNode(j);
				}
				System.out.println("The item is deleted Succesfully. You can check it by printing the list.");
			}
			else if(num==1){
				System.out.println("How you want to insert.\n1.Insert at Specific Position.\n2.Insert after any Specific Value in list. ");
				i = sc.nextInt();
				if(i==1){
					System.out.print("Give the position for Node : ");
					j = sc.nextInt();
					System.out.print("Give the data for New Node : ");
					k = sc.nextInt();
					if(j == 1)
					{
						Node newNode = new Node(k);
						L.addAtFront(newNode);
					}
					else
					L.insertPos(j,k);
				}
				else{
					System.out.print("Give the value in the list after which you want to insert : ");
					j = sc.nextInt();
					System.out.print("Give the data for Node : ");
					k = sc.nextInt();
					L.insertNode(k,j);
				}
				System.out.println("The item is inserted Succesfully. You can check it by printing the list.");
			}
		}
	}
}