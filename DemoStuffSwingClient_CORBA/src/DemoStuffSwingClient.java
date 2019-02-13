/* File: DemoStuffSwingClient.java
* Author: Stanley Pieda
* Date: March 2018
* CORBA Connectivity based on earlier work provided by Todd Kelley
* (2016) Personal Communication
*/
import business.StuffFacadeRemote;
import entity.Stuff;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ScrollPaneConstants;

/**
* @author Stanley Pieda
*/
public class DemoStuffSwingClient extends JFrame {
    private final StuffFacadeRemote remoteStuff;
    private JTextField recordNumber = new JTextField();
    private JTextField omega = new JTextField();
    private JTextField Theta = new JTextField();
    private JTextField DeltaJTextField = new JTextField();
    private JTextField LambdaJTextField = new JTextField();


    private JTextArea stuffViewJTextArea = new JTextArea(10, 0); // 10 rows, 0 columns
    public DemoStuffSwingClient() {
        buildGUI();
        remoteStuff = getRemoteSession();
    }
    
    private StuffFacadeRemote getRemoteSession() {
        StuffFacadeRemote session = null;
        // CORBA properties and values and lookup taken after earlier work provided by
        // Todd Kelley (2016) Personal Communication
        System.setProperty("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        System.setProperty("org.omg.CORBA.ORBInitialPort", "3700"); 
        try {
            JOptionPane.showMessageDialog(this, "Trying for a session...");
            InitialContext ic = new InitialContext();
            session = (StuffFacadeRemote)
            ic.lookup("java:global/DemoStuff/DemoStuff-ejb/StuffFacade");
            JOptionPane.showMessageDialog(this, "Got a session :) ");
            } catch (NamingException e) {
            JOptionPane.showMessageDialog(this, "Problem. \n Cause: \n" + e.getMessage());
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Problem. \n Cause: \n" + e.getMessage());
            }
            return session;
        }
        
        private void buildGUI() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Swing Client Demo - CORBA");
            this.setExtendedState(this.MAXIMIZED_BOTH);
            GridLayout westGridLayout = new GridLayout(6, 1); // rows, columns
            westGridLayout.setHgap(10);
            westGridLayout.setVgap(10);
            JPanel dataEntryWestJPanel = new JPanel(westGridLayout);
            dataEntryWestJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 0));
            GridLayout dataEntryGridLayout = new GridLayout(6, 1); // rows, columns
            dataEntryGridLayout.setHgap(10);
            dataEntryGridLayout.setVgap(10);
            JPanel dataEntryCenterJPanel = new JPanel(dataEntryGridLayout);
            dataEntryCenterJPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 10));
            FlowLayout buttonLayout = new FlowLayout(FlowLayout.RIGHT);
            buttonLayout.setHgap(10);
            JPanel dataEntryButtonJPanel = new JPanel(buttonLayout);
            
            JPanel dataEntryJPanel = new JPanel(new BorderLayout());
            JPanel dataViewJPanel = new JPanel(new GridLayout(1, 1));
            dataViewJPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel stuffJLabel = new JLabel("RecordNumber");
            JLabel moreStuffJLabel = new JLabel("omega");
            JLabel TunaLabel = new JLabel("Theta");
            JLabel DeltaLabel = new JLabel("Delta");
            JLabel LambdaLabel = new JLabel("Lambda");



            JButton addJButton = new JButton("Add Tuna");
            JButton viewAllJButton = new JButton("View All Tuna");
            
            stuffJLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));//top,left,bottom,right
            moreStuffJLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            TunaLabel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            
            stuffViewJTextArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            addJButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            viewAllJButton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
            
            dataEntryWestJPanel.add(stuffJLabel);
            dataEntryWestJPanel.add(moreStuffJLabel);
            dataEntryWestJPanel.add(TunaLabel);
            dataEntryWestJPanel.add(DeltaLabel);
            dataEntryWestJPanel.add(LambdaLabel);


            dataEntryButtonJPanel.add(addJButton);
            dataEntryButtonJPanel.add(viewAllJButton);
            dataEntryCenterJPanel.add(recordNumber);
            dataEntryCenterJPanel.add(omega);
            dataEntryCenterJPanel.add(Theta);
            dataEntryCenterJPanel.add(DeltaJTextField);
            dataEntryCenterJPanel.add(LambdaJTextField);

            

            
            dataEntryCenterJPanel.add(dataEntryButtonJPanel);
            dataEntryJPanel.add(dataEntryWestJPanel, BorderLayout.WEST);
            dataEntryJPanel.add(dataEntryCenterJPanel, BorderLayout.CENTER);
            JScrollPane scrollPane = new JScrollPane(stuffViewJTextArea);
            scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
            dataViewJPanel.add(scrollPane);
            this.getContentPane().add(dataEntryJPanel, BorderLayout.NORTH);
            this.getContentPane().add(dataViewJPanel, BorderLayout.CENTER);
            
            addJButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        if(remoteStuff != null){
                        Stuff stuff = new Stuff();
                        stuff.setStuff(recordNumber.getText());
                        stuff.setMoreStuff(omega.getText());
                        stuff.setTuna(Theta.getText());
                        stuff.setLambda(LambdaJTextField.getText());
                        stuff.setDelta(DeltaJTextField.getText());
                        

                        remoteStuff.create(stuff);
                    }
                    else{
                        JOptionPane.showMessageDialog(
                        DemoStuffSwingClient.this,
                        "Problem. No remote object available");
                    }   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        DemoStuffSwingClient.this,
                        "Problem Cause: \n" + ex.getMessage());
                }
            }
        });
        viewAllJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(remoteStuff != null){
                    List<Stuff> stuffs = remoteStuff.findAll();
                    stuffViewJTextArea.setText("");
                    for (Stuff s : stuffs) {
                        stuffViewJTextArea.append(
                        String.format("id:%d stuff:%s morestuff:%s Tuna:%s "
                                + "Delta:%s Lambda:%s%n",
                        s.getId(), s.getStuff(), s.getMoreStuff(), s.getTuna(),
                        s.getDelta(),s.getLambda()));
                    }
                    }
                    else{
                        JOptionPane.showMessageDialog(
                        DemoStuffSwingClient.this,
                        "Problem. No remote object available");
                    }
                } catch (Exception ex) {
                        JOptionPane.showMessageDialog(
                            DemoStuffSwingClient.this,
                            "Problem Cause: \n" + ex.getMessage());
                }
            }
        });
        //this.pack();
        this.setSize(600, 400);
        // null causes window to be centered on screen
        // see: stackoverflow.com (). How to set JFrame to appear centered, regardless of monitor resolution? Retrieved from
        // https://stackoverflow.com/questions/2442599/how-to-set-jframe-to-appear-centered-regardless-of-monitor-resolution
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public static void main(String[] args) {
        DemoStuffSwingClient client = new DemoStuffSwingClient();
    }
}
         