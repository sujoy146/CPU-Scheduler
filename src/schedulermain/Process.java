package schedulermain;

class Process implements Comparable<Process> {
    int processId;
    int arrivalTime;
    int burstTime;
    int priority;

    public Process(int processId, int arrivalTime, int burstTime, int priority) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
    }

    @Override
    public int compareTo(Process other) {
        // Higher priority processes come first
        return Integer.compare(other.priority, this.priority);
    }
}
