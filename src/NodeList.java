public class NodeList {

        protected LargeDepositor data;
        protected NodeList next = null;


        NodeList(LargeDepositor data) {
            this.data = data;
        }

        public LargeDepositor getData() {
            // return data stored in this node
            return data;
        }




    public NodeList getNext() {
            // get next node
            return next;
        }

        public void setNext(NodeList next) {
            this.next = next;
        }
    }


