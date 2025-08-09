# CPU-Scheduler

# CPU Scheduling Algorithms Visualizer

A **Java GUI-based project** that simulates and visualizes multiple CPU scheduling algorithms.  
This project was developed as part of an **Operating Systems course** assignment to help demonstrate how different CPU schedulers operate and affect process execution order and waiting time.

## 🧠 Features

- Graphical User Interface built with Java Swing
- Visual and step-by-step simulation of:
  - **FCFS (First-Come First-Served)**
  - **SJF (Shortest Job First)**
  - **Round Robin**
  - **Non-Preemptive Priority**
  - **Preemptive Priority**
- Displays key metrics like:
  - Average waiting time
  - Turnaround time
  - CPU utilization

## 📌 Technologies Used

- Java
- Swing (for GUI)

## 📷 Demo Screenshots
 - **Enter Processes**
 - Select Scheduling Algo
<img width="584" height="461" src="images/Screenshot 2025-08-09 162020.png" alt="Project Screenshot" />
 - Put required values
<img width="584" height="461" src="images/Screenshot 2025-08-09 162043.png" alt="Project Screenshot" />
 - Check the result
<img width="584" height="461" src="images/Screenshot 2025-08-09 162054.png" alt="Project Screenshot" />  


## 📁 Project Structure

```

CPU-Scheduler/
├── .idea/
├── images/
├── src/
│   └── schedulermain/
│       ├── FCFS.java
│       ├── SJF.java
│       ├── RoundRobin.java
│       ├── NonPreemptivePriority.java
│       ├── PreemptivePriority.java
│       ├── SchedulerGUI.java
│       └── SchedulerMain.java
└── README.md

````

## 📚 How to Run

1. Clone the repository:

   git clone https://github.com/sujoy146/CPU-Scheduler.git
   cd CPU-Scheduling-Algorithms


2. Compile and run the application:

   * Using an IDE (like **IntelliJ**)
   * Or from terminal:


     javac src/schedulermain/*.java
     java schedulermain.SchedulerMain


> Make sure you have Java 8+ installed.

## 🧑‍💻 Author

**Sujoy**

