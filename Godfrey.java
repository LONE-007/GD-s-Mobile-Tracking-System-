import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;

public class Godfrey extends Frame implements ActionListener {
    private static final String API_KEY = "0bef6614413443725df6f1a8c65b8825"; // API key

    private List<String> trackedMobiles = new ArrayList<>();
    private List<String> lostMobiles = new ArrayList<>();
    private Random random = new Random();

    private Label label;
    private TextField inputField;
    private TextArea outputArea;
    private Button trackButton, reportLostButton, displayButton, exitButton;

    public Godfrey() {
        // Frame setup
        setTitle("Mobile Tracking System");
        setSize(500, 400);
        setLayout(new FlowLayout());
        setResizable(false);

        // UI Components
        label = new Label("Enter Mobile Number:");
        inputField = new TextField(20);
        outputArea = new TextArea(15, 40);
        trackButton = new Button("Track Mobile Location");
        reportLostButton = new Button("Report Lost Mobile");
        displayButton = new Button("Display Stored Data");
        exitButton = new Button("Exit");

        // Adding components to the frame
        add(label);
        add(inputField);
        add(trackButton);
        add(reportLostButton);
        add(displayButton);
        add(exitButton);
        add(outputArea);

        // Event Listeners
        trackButton.addActionListener(this);
        reportLostButton.addActionListener(this);
        displayButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Frame close behavior with confirmation
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String mobileNumber = inputField.getText().trim();

        if (action.equals("Track Mobile Location")) {
            if (mobileNumber.isEmpty() || !mobileNumber.matches("\\d{10}")) {
                outputArea.setText("Please enter a valid 10-digit mobile number to track.");
                return;
            }

            // Simulate API key validation
            if (validateApiKey(API_KEY)) {
                double latitude = 10 + (random.nextDouble() * (20 - 10)); // Random latitude
                double longitude = 70 + (random.nextDouble() * (80 - 70)); // Random longitude
                trackedMobiles.add(mobileNumber);
                outputArea.setText("Tracking mobile number: " + mobileNumber + "\n");
                outputArea.append(String.format("Location: Latitude %.4f, Longitude %.4f\n", latitude, longitude));
            } else {
                outputArea.setText("Failed to authenticate with the tracking service. Please check the API key.");
            }
        } else if (action.equals("Report Lost Mobile")) {
            if (mobileNumber.isEmpty() || !mobileNumber.matches("\\d{10}")) {
                outputArea.setText("Please enter a valid 10-digit mobile number to report as lost.");
                return;
            }
            lostMobiles.add(mobileNumber);
            outputArea.setText("Lost mobile report for number " + mobileNumber + " has been registered.\n");
            outputArea.append("Please contact your service provider for further support.");
        } else if (action.equals("Display Stored Data")) {
            outputArea.setText("==== Stored Data ====\n");
            outputArea.append("Tracked Mobile Numbers:\n");
            if (trackedMobiles.isEmpty()) {
                outputArea.append("No mobile numbers have been tracked yet.\n");
            } else {
                trackedMobiles.forEach(num -> outputArea.append(num + "\n"));
            }
            outputArea.append("\nLost Mobile Reports:\n");
            if (lostMobiles.isEmpty()) {
                outputArea.append("No lost mobile reports have been filed yet.\n");
            } else {
                lostMobiles.forEach(num -> outputArea.append(num + "\n"));
            }
        } else if (action.equals("Exit")) {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Confirm Exit",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                dispose();
            }
        }
    }

    private boolean validateApiKey(String apiKey) {
        // Simulating an API key validation process
        return apiKey.equals("0bef6614413443725df6f1a8c65b8825");
    }

    public static void main(String[] args) {
        new Godfrey();
    }
}
