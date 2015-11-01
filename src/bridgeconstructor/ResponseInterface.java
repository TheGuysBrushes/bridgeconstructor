package bridgeconstructor;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author Florian
 */
public class ResponseInterface extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String title = "Bridge Constructor - Response";
	
	List<Bridge> listBridge;
	List<Material> listMaterial;
	
    private String[] imageFileNames = { "bridge_arc.png", "bridge_beam.png", "bridge_hanging.png", "bridge_shroud.png"};
    private String path = "./ressources/";
	
    private ImageIcon icon;
    
	// Panel
	private JPanel main_panel;
		private JPanel up_panel;
		private JScrollPane scroll_panel;
			private JPanel list_panel;
		private JPanel bottom_panel;
	// Label
	private JLabel order;
	// Composition de la description d'un pont
	private JPanel bridge_panel;
		private JLabel image;
		private JLabel type;
		private JLabel height;
		private JLabel width;	// MIN - MAX Width
		private JLabel length;
		private JLabel price;
	// Matériau
	private JLabel matter;
	
    /**
     *
     */
    public ResponseInterface() {
		super(title);
		
		listBridge = new ArrayList<Bridge>();
		listMaterial = new ArrayList<Material>();
		
		buildComposants();
		buildInterface();
		buildEvents();
		
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
        //get local graphics environment to get maximum window bounds
        Rectangle screenSize= GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.setLocation((int)(screenSize.getWidth() - this.getWidth()) / 2,
						 	(int)(screenSize.getHeight() - this.getHeight()) / 2);
		this.setVisible(true);
	}
	
    /**
     *
     * @param LB
     */
    public ResponseInterface(List<Bridge> LB, List<Material> LM) {
		super(title);
		
		listBridge = LB;
		listMaterial = LM;
		
		for(Bridge B : listBridge) System.out.println(B);
		for(Material M : listMaterial) System.out.println(M);
		
		buildComposants();
		buildInterface();
		buildEvents();
		
		this.setResizable(false);
		this.pack();
        //get local graphics environment to get maximum window bounds
        Rectangle screenSize= GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        this.setLocation((int)(screenSize.getWidth() - this.getWidth()) / 2,
						 	(int)(screenSize.getHeight() - this.getHeight()) / 2);
	}

	private void buildComposants() {
		Border raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		
		// Panel
		main_panel = new JPanel(new BorderLayout());
			//main_panel.setLayout(new BoxLayout(main_panel, BoxLayout.Y_AXIS));
			up_panel = new JPanel();
			scroll_panel = new JScrollPane();
				list_panel = new JPanel();
					list_panel.setLayout(new BoxLayout(list_panel, BoxLayout.X_AXIS));
			bottom_panel = new JPanel();
		// Label
		if(listBridge.size() > 1)
			order = new JLabel("Les ponts suggérés : ");
		else order = new JLabel("Le pont suggéré : ");
		up_panel.add(order);
		// Bridge
		for(Bridge B : listBridge) {
			// Construction
			bridge_panel = new JPanel();
				bridge_panel.setBorder(raisedetched);
			bridge_panel.setLayout(new BoxLayout(bridge_panel, BoxLayout.Y_AXIS));
				icon = createImageIcon(getPath(B.getType()));
				image = new JLabel(icon);
				        image.setVerticalTextPosition(JLabel.BOTTOM);
				        image.setHorizontalTextPosition(JLabel.CENTER);
				        image.setHorizontalAlignment(JLabel.CENTER);
				        image.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				type = new JLabel(B.getStringType());
					type.setAlignmentX(Component.CENTER_ALIGNMENT);
				height = new JLabel("Hauteur : " + B.getHeight() + "m");
				width = new JLabel("Largeur : " + B.getMinWidth() + " - " + B.getMaxWidth() + "m");	// MIN - MAX Width
				length = new JLabel("Longueur : " + B.getLength() + "m");
				price = new JLabel("Prix : " + B.getPrice());
			// Affichage
			bridge_panel.add(image);
			bridge_panel.add(type);
			bridge_panel.add(height);
			bridge_panel.add(width);
			bridge_panel.add(length);
			bridge_panel.add(price);
			
			list_panel.add(bridge_panel);
		}
		scroll_panel.setViewportView(list_panel);
		String s;
		if(listMaterial.size() > 1)
			s = "Matériaux envisagés : ";
		else s = "Matériau envisagé : "; 
		for(Material M : listMaterial)
			s += M;
		matter = new JLabel(s);
		bottom_panel.add(matter);
	}

	private void buildInterface() {
		main_panel.add(up_panel, BorderLayout.NORTH);
		main_panel.add(scroll_panel, BorderLayout.CENTER);
		main_panel.add(bottom_panel, BorderLayout.SOUTH);
		
		this.setContentPane(main_panel);
	}
	
	private void buildEvents() {
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private String getPath(TypeBridge type) {
		switch(type) {
			case arc :
				return path+imageFileNames[0];
			case beam :
				return path+imageFileNames[1];
			case hanging :
				return path+imageFileNames[2];
			case shroud : 
				return path+imageFileNames[3];
			default :
				return null;
		}
	}
	
	private ImageIcon createImageIcon(String path) {
	    if (path != null) {
	        return new ImageIcon(path);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}
}
