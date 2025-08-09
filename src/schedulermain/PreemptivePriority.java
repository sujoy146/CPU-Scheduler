package schedulermain;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.List;



public class PreemptivePriority {

    public void runScheduler() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();

        ArrayList<Process> processList = new ArrayList<>();
        int[] remainingBurstTime = new int[n];
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter arrival time for Process " + (i + 1) + ": ");
            int arrivalTime = scanner.nextInt();
            System.out.print("Enter burst time for Process " + (i + 1) + ": ");
            int burstTime = scanner.nextInt();
            System.out.print("Enter priority for Process " + (i + 1) + ": ");
            int priority = scanner.nextInt();

            Process process = new Process(i + 1, arrivalTime, burstTime, priority);
            processList.add(process);
            remainingBurstTime[i] = burstTime;
        }

        // Sort by arrival time first
        processList.sort((p1, p2) -> {
            if (p1.arrivalTime != p2.arrivalTime) {
                return Integer.compare(p1.arrivalTime, p2.arrivalTime);
            } else {
                return Integer.compare(p1.priority, p2.priority);
            }
        });

        int currentTime = 0;
        int completed = 0;
        boolean[] isCompleted = new boolean[n];
        PriorityQueue<Process> pq = new PriorityQueue<>();

        while (completed < n) {
            // Add processes that have arrived
            for (Process p : processList) {
                if (p.arrivalTime <= currentTime && !isCompleted[p.processId - 1] && !pq.contains(p)) {
                    pq.add(p);
                }
            }

            if (pq.isEmpty()) {
                currentTime++;
                continue;
            }

            Process currentProcess = pq.poll();
            int pidIndex = currentProcess.processId - 1;

            remainingBurstTime[pidIndex]--;
            currentTime++;

            if (remainingBurstTime[pidIndex] == 0) {
                isCompleted[pidIndex] = true;
                completed++;
                turnaroundTime[pidIndex] = currentTime - currentProcess.arrivalTime;
                waitingTime[pidIndex] = turnaroundTime[pidIndex] - currentProcess.burstTime;
            } else {
                pq.add(currentProcess);
            }
        }

        System.out.println("Preemptive Priority Scheduling:");
        System.out.println("Process\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time");

        for (Process p : processList) {
            int pidIndex = p.processId - 1;
            System.out.println(p.processId + "\t\t" + p.arrivalTime + "\t\t" + p.burstTime +
                    "\t\t" + p.priority + "\t\t" + waitingTime[pidIndex] +
                    "\t\t" + turnaroundTime[pidIndex]);
        }

        double averageWaitingTime = calculateAverage(waitingTime);
        double averageTurnaroundTime = calculateAverage(turnaroundTime);

        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turnaround Time: " + averageTurnaroundTime);
    }

    public String simulate(List<schedulermain.SchedulerGUI.ProcessInput> processInputs) {
        int n = processInputs.size();
        class Proc {
            int processId, arrivalTime, burstTime, priority, remainingTime;
            Proc(int processId, int arrivalTime, int burstTime, int priority) {
                this.processId = processId;
                this.arrivalTime = arrivalTime;
                this.burstTime = burstTime;
                this.priority = priority;
                this.remainingTime = burstTime;
            }
        }
        java.util.List<Proc> processList = new java.util.ArrayList<>();
        for (int i = 0; i < n; i++) {
            schedulermain.SchedulerGUI.ProcessInput pi = processInputs.get(i);
            processList.add(new Proc(i + 1, pi.arrival, pi.burst, pi.priority));
        }
        int[] waitingTime = new int[n];
        int[] turnaroundTime = new int[n];
        int completed = 0, currentTime = 0;
        boolean[] isCompleted = new boolean[n];
        while (completed < n) {
            int idx = -1, minPriority = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                Proc p = processList.get(i);
                if (p.arrivalTime <= currentTime && !isCompleted[i] && p.remainingTime > 0) {
                    if (p.priority < minPriority) {
                        minPriority = p.priority;
                        idx = i;
                    }
                }
            }
            if (idx == -1) {
                currentTime++;
                continue;
            }
            Proc p = processList.get(idx);
            p.remainingTime--;
            currentTime++;
            if (p.remainingTime == 0) {
                isCompleted[idx] = true;
                completed++;
                turnaroundTime[idx] = currentTime - p.arrivalTime;
                waitingTime[idx] = turnaroundTime[idx] - p.burstTime;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Preemptive Priority Scheduling:\n");
        sb.append("Process\tArrival Time\tBurst Time\tPriority\tWaiting Time\tTurnaround Time\n");
        for (int i = 0; i < n; i++) {
            Proc p = processList.get(i);
            sb.append(p.processId).append("\t\t")
                    .append(p.arrivalTime).append("\t\t")
                    .append(p.burstTime).append("\t\t")
                    .append(p.priority).append("\t\t")
                    .append(waitingTime[i]).append("\t\t")
                    .append(turnaroundTime[i]).append("\n");
        }
        double averageWaitingTime = calculateAverage(waitingTime);
        double averageTurnaroundTime = calculateAverage(turnaroundTime);
        sb.append("Average Waiting Time: ").append(averageWaitingTime).append("\n");
        sb.append("Average Turnaround Time: ").append(averageTurnaroundTime).append("\n");
        return sb.toString();
    }

    private double calculateAverage(int[] array) {
        double sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum / array.length;
    }

    public static void main(String[] args) {
        PreemptivePriority ppsScheduler = new PreemptivePriority();
        ppsScheduler.runScheduler();
    }
}
