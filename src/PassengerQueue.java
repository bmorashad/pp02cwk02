import java.util.Arrays;

public class PassengerQueue {
    private final Passenger[] queue;
    private int maxStayInQueue;
    private int size;
    private int maxSize;
    private int head;
    private int tail;

    public PassengerQueue(int maxSize) {
        queue = new Passenger[maxSize];
        this.maxSize = maxSize;
    }
    // set logic in simulation
    public void setMaxStayInQueue(int sec) {
        maxStayInQueue = sec;
    }
    public int getMaxStayInQueue() {
        return maxStayInQueue;
    }
    public void enqueue(Passenger p) throws Exception {
       if(!isFull()) {
           queue[tail] = p;
           tail = (tail + 1) % queue.length;
           size++;
       } else {
           throw new Exception("Queue is full");
       }

    }
    public Passenger dequeue() {
       if(!isEmpty()) {
           Passenger removed = queue[head];
//           System.out.println(head);
           head = (head + 1) % queue.length;
//           System.out.println(head);
//           System.out.println(size);
           size--;
           return removed;
       }
       return null;
    }
    public Passenger delete(String fullName, int seatNum) {
        Passenger dltPassenger = null;
        int indexOfSeatNum = findSeat(seatNum);
        if(indexOfSeatNum >= 0) {
            if(queue[indexOfSeatNum].getFullName() == fullName) {
                dltPassenger = queue[indexOfSeatNum];
                for(int j = 0; j < size; j++) {
                    queue[(j+indexOfSeatNum)%maxSize] = queue[(j+indexOfSeatNum+1)%maxSize];
                }
                tail--;
                size--;
            }
        }
//        for(int i = 0; i < size; i++) {
//            // keep an eye, can improve prolly(binary search)
//            int currentPassenger = (i+head)%maxSize;
//            if(queue[currentPassenger].getSeatNum() == seatNum && queue[currentPassenger].getFullName() == fullName) {
//                dltPassenger = queue[currentPassenger];
//                for(int j = 0; j < size; j++) {
//                    queue[(j+currentPassenger)%maxSize] = queue[(j+currentPassenger+1)%maxSize];
//                }
//                tail--;
//                size--;
//                break;
//            }
//        }
        return dltPassenger;
    }
    public boolean isFull() {
        if(size == queue.length) {
            return true;
        }
        return false;
    }
    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }
    public int getSize() {
        return size;
    }
    public int getMaxSize() {
        return maxSize;
    }
    public void display() {
//        System.out.println(size);
        for(int i = 0; i < size; i++) {
            System.out.println(queue[(i+head) % maxSize].getFullName() + " " + queue[(i+head) % maxSize].getSeatNum());
        }
    }

    //helper methods

    // can improve to check only within queue size
    private int findSeat(int seatNum)
    {
        int search_index;
        int from = head;
        int to = tail - 1;
        do {
            if(to > from) {
                search_index = (to - from)  / 2 + from;
            } else {
                search_index = (((maxSize - from) + to)/2 + from) % maxSize;
            }
            if(queue[search_index].getSeatNum() == seatNum) {
                return search_index;
            }
    //            else if(from_index == arr.length - 2) {
    //                System.out.println("im in");
    //                if(arr[search_index+1] == search) {
    //                    System.out.println("index found: " + (search_index+1));
    //                    break;
    //                } else {
    //                    System.out.println("not found");
    //                    break;
    //                }
    //            }
            if (seatNum > queue[search_index].getSeatNum()) {
                from = search_index+1%maxSize;
            } else {
                to = (search_index-1) + maxSize % maxSize;
            }
        } while ((from - to) != 1 && (to - from) != maxSize-1);
        return -1;
    }

}
