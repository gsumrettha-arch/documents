
import java.util.Scanner;

class FreelancerProject {
    int id;
    String freelancerName;
    String projectName;
    double payment;
    String status;
    boolean paid;

    FreelancerProject(int id, String freelancerName,
                      String projectName, double payment) {
        this.id = id;
        this.freelancerName = freelancerName;
        this.projectName = projectName;
        this.payment = payment;
        this.status = "Pending";
        this.paid = false;
    }

    void display() {
        System.out.println("\nProject ID      : " + id);
        System.out.println("Freelancer Name : " + freelancerName);
        System.out.println("Project Name    : " + projectName);
        System.out.println("Payment Amount  : Rs. " + payment);
        System.out.println("Project Status  : " + status);
        System.out.println("Payment Status  : "
                + (paid ? "Paid" : "Not Paid"));
        System.out.println("------------------------------");
    }
}

public class FreelancerProjectManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FreelancerProject[] projects = new FreelancerProject[100];
        int count = 0;
        int choice;

        do {
            System.out.println("\n=== FREELANCER PROJECT MANAGEMENT ===");
            System.out.println("1. Add Project");
            System.out.println("2. View All Projects");
            System.out.println("3. Update Project Status");
            System.out.println("4. Track Payment");
            System.out.println("5. View Payment Summary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    if (count == projects.length) {
                        System.out.println("Project list is full!");
                        break;
                    }

                    System.out.print("Enter Project ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Freelancer Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Project Name: ");
                    String project = sc.nextLine();

                    System.out.print("Enter Payment Amount: ");
                    double amount = sc.nextDouble();
                    sc.nextLine();

                    if (amount < 0) {
                        System.out.println("Invalid payment amount!");
                        break;
                    }

                    projects[count++] = new FreelancerProject(
                            id, name, project, amount);

                    System.out.println("Project added successfully!");
                    break;

                case 2:
                    if (count == 0) {
                        System.out.println("No projects available.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            projects[i].display();
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Project ID: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    boolean found = false;

                    for (int i = 0; i < count; i++) {
                        if (projects[i].id == updateId) {
                            System.out.println(
                                    "1. Pending");
                            System.out.println(
                                    "2. In Progress");
                            System.out.println(
                                    "3. Completed");
                            System.out.print("Choose status: ");

                            int s = sc.nextInt();
                            sc.nextLine();

                            if (s == 1) {
                                projects[i].status = "Pending";
                            } else if (s == 2) {
                                projects[i].status = "In Progress";
                            } else if (s == 3) {
                                projects[i].status = "Completed";
                            } else {
                                System.out.println("Invalid status!");
                                found = true;
                                break;
                            }

                            System.out.println("Status updated!");
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Project not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter Project ID: ");
                    int payId = sc.nextInt();
                    sc.nextLine();

                    found = false;

                    for (int i = 0; i < count; i++) {
                        if (projects[i].id == payId) {
                            if (projects[i].paid) {
                                System.out.println(
                                        "Payment already completed.");
                            } else {
                                System.out.println("Amount: Rs. "
                                        + projects[i].payment);
                                System.out.print(
                                        "Confirm payment? (1 = Yes, 0 = No): ");

                                int confirm = sc.nextInt();
                                sc.nextLine();

                                if (confirm == 1) {
                                    projects[i].paid = true;
                                    System.out.println(
                                            "Payment marked as paid!");
                                } else {
                                    System.out.println(
                                            "Payment not updated.");
                                }
                            }

                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("Project not found.");
                    }
                    break;

                case 5:
                    double total = 0;
                    double paidAmount = 0;
                    double pendingAmount = 0;

                    for (int i = 0; i < count; i++) {
                        total += projects[i].payment;

                        if (projects[i].paid) {
                            paidAmount += projects[i].payment;
                        } else {
                            pendingAmount += projects[i].payment;
                        }
                    }

                    System.out.println("\n=== PAYMENT SUMMARY ===");
                    System.out.println(
                            "Total Project Amount: Rs. " + total);
                    System.out.println(
                            "Paid Amount         : Rs. " + paidAmount);
                    System.out.println(
                            "Pending Amount      : Rs. " + pendingAmount);
                    break;

                case 6:
                    System.out.println(
                            "Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}
