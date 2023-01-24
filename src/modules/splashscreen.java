package src.modules;
import javax.swing.*;
import java.awt.*;

import src.game;

/**
 * Main class of splacscreen scene.
 */
public class splashscreen {
  
  /**
   * Public method show scene.
   * @public
   */
  public static void ShowScene() {
    game.GetWindow().repaint();
    game.GetWindow().setContentPane(CreateScene());

    game.GetWindow().setVisible(true);
  }

  /**
   * Private method to create start button.
   * @private
   * @return {JButton}
   */
  private static JButton CreateStartButton() {
    JButton button =  new JButton("Start new game");
    button.setSize(150, 50);

    button.addActionListener(e -> {
      gameplay.ShowScene(); 
    });

    return button;
  }

  /**
   * Private method to create scene panel.
   * @private
   * @return {JPanel}
   */
  private static JPanel CreateScene() {
    JPanel panel = new JPanel();

    panel.setLayout(new GridBagLayout());
    panel.add(CreateStartButton());
    return panel;
  }
}
