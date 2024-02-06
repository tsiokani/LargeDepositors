public class List {

        private NodeList head = null;
        private NodeList tail = null;

        public List() {
        }

        public void empty(){    // this makes the list empty
            head = null;
        }
        public NodeList getFirstNode() {    // this method returns the first node
            return head;
        }

        public boolean isEmpty() {  // checks if is empty
            return head == null;
        }

        public void insertAtFront(LargeDepositor data) {    // insert a node at the front of the list
            NodeList n = new NodeList(data);

            if (isEmpty()) {
                head = n;
                tail = n;
            } else {
                n.setNext(head);
                head = n;
            }
        }
}


