package view;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.global.Dungeon;

import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.Color;

public class FrameView extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dungeon dungeon = new Dungeon();
					FrameView frame = new FrameView("Dungeon", dungeon);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrameView(String title, Dungeon dungeon) {
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(500,300);
		setLocationRelativeTo(null);
		setResizable(false);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));
		setContentPane(contentPane);
		
		JPanel infosPanel = new JPanel();
		infosPanel.setBackground(new Color(240, 255, 240));
		contentPane.add(infosPanel);
		infosPanel.setLayout(new BoxLayout(infosPanel, BoxLayout.X_AXIS));
		final JLabel infosLabel = new JLabel(
				"<html><div style='text-align:center;padding:5px'>Welcome to the Dungeon ! This first level has " + dungeon.getTotalRooms() + " rooms to discover !<br /><br />"
				+ "Be careful of trap rooms and don't hesitate to explore every room, you might get a surprise !</div></html>");
		infosPanel.add(infosLabel);
		
		JPanel choicePanel = new JPanel();
		choicePanel.setBackground(new Color(248, 248, 255));
		contentPane.add(choicePanel);
		JButton playButton = new JButton("Play !");
		playButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {	
			}		
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		choicePanel.add(playButton);
		
	}

}
