import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.util.Date;

public class SIES extends JFrame implements ActionListener {
    
    // Module 1: Encapsulation
    private double balance = 0.0;
    
    JTextField amountField;
    JTextArea display;
    JButton depBtn, withBtn, recBtn;
    
    // Module 3: 1D-Array
    String[] history = new String[100];
    int count = 0;

    public SIES() {
        // --- 1. THE QUICK LOGIN ---
        String pin = JOptionPane.showInputDialog(null, "Enter 4-Digit PIN to access SIES Bank:", "Secure Login", JOptionPane.QUESTION_MESSAGE);
        if (pin == null || !pin.equals("1234")) {
            JOptionPane.showMessageDialog(null, "Access Denied.");
            System.exit(0); // Closes the app if the PIN is wrong
        }

        // --- 2. DARK MODE WINDOW SETUP ---
        setTitle("SIES Secure Bank");
        setSize(400, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(30, 30, 40)); // Dark Charcoal
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers on screen

        JLabel title = new JLabel("Welcome, Shriyaa");
        title.setForeground(new Color(200, 220, 255)); // Ice Blue
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setBounds(100, 15, 200, 30);
        add(title);

        amountField = new JTextField();
        amountField.setBounds(120, 60, 150, 30);
        amountField.setHorizontalAlignment(JTextField.CENTER);
        amountField.setFont(new Font("SansSerif", Font.BOLD, 14));
        add(amountField);

        // --- 3. COLOR-CODED BUTTONS ---
        // Using a custom method below to save us from typing the same code 3 times!
        depBtn = createButton("Deposit", 50, 110, new Color(46, 204, 113));  // Vibrant Green
        withBtn = createButton("Withdraw", 200, 110, new Color(231, 76, 60)); // Vibrant Red
        recBtn = createButton("Download Receipt", 100, 400, new Color(52, 152, 219)); // Bright Blue

        // --- 4. SILVER DISPLAY AREA ---
        display = new JTextArea(" --- Account Statement ---\n Balance: ₹0.0\n\n");
        display.setEditable(false);
        display.setBackground(new Color(245, 245, 250)); // Cool Silver
        
        JScrollPane scroll = new JScrollPane(display);
        scroll.setBounds(40, 160, 300, 220);
        add(scroll);

        setVisible(true);
    }

    // Helper Method to make the code shorter!
    JButton createButton(String text, int x, int y, Color c) {
        JButton b = new JButton(text);
        b.setBounds(x, y, 130, 35);
        b.setBackground(c);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setFont(new Font("SansSerif", Font.BOLD, 12));
        b.addActionListener(this);
        add(b);
        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle Receipt Download (Module 2: File IO)
        if (e.getSource() == recBtn) {
            try {
                FileWriter w = new FileWriter("Receipt.txt");
                w.write(display.getText());
                w.close();
                JOptionPane.showMessageDialog(this, "Receipt Downloaded Successfully!");
            } catch (Exception ex) {}
            return;
        }

        // Module 5: Try-Catch for Transactions
        try {
            double amt = Double.parseDouble(amountField.getText());
            if (amt <= 0) throw new Exception("Enter a valid amount!");

            if (e.getSource() == depBtn) {
                balance += amt;
                logTransaction("Deposited: +₹" + amt);
            } else if (e.getSource() == withBtn) {
                if (amt > balance) throw new Exception("Insufficient Funds!");
                balance -= amt;
                logTransaction("Withdrew:  -₹" + amt);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Numbers only!", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        amountField.setText("");
    }

    // Helper Method to write to the array and screen
    void logTransaction(String msg) {
        // Gets just the HH:MM:SS part of the date
        String time = new Date().toString().substring(11, 19); 
        String record = "[" + time + "] " + msg;
        
        if(count < 100) history[count++] = record; // Save to array
        display.append(record + "\n Current Balance: ₹" + balance + "\n\n");
    }

    public static void main(String[] args) {
        new SIES();
    }
}