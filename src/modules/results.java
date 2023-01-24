package src.modules;

import java.awt.*;
import javax.swing.*;
import java.text.MessageFormat;

import src.game;

/**
 * Class of results scene.
 */
public class results {
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
    JButton button = new JButton("Start new game");

    button.addActionListener(e -> {
      splashscreen.ShowScene(); 
    });

    button.setAlignmentX(Component.CENTER_ALIGNMENT);
    button.setAlignmentY(Component.CENTER_ALIGNMENT);

    return button;
  }

  /**
   * Private method to create result text.
   * @private
   * @return {JLabel}
   */
  private static JLabel CreateTextResult() {
    JLabel label = new JLabel();

    switch(gameplay.GetResult()) {
      case "X":
      case "O":
        Object[] params = new Object[]{gameplay.GetResult()};
        label.setText(MessageFormat.format("The winner is {0}", params));
        break;
      default:
        label.setText("There is a draw");
        break;
    }

    label.setAlignmentX(Component.CENTER_ALIGNMENT);
    label.setAlignmentY(Component.CENTER_ALIGNMENT);
    return label;
  }

  /**
   * Private method to create scene panel.
   * @private
   * @return {JPanel}
   */
  private static JPanel CreateScene() {
    JPanel outer = new JPanel(new BorderLayout());
    JPanel centerPanel = new JPanel();

    centerPanel.setLayout(new GridBagLayout());
    centerPanel.add(CreateTextResult());

    JPanel southPanel = new JPanel(new CardLayout());
    southPanel.add(CreateStartButton());

    outer.add(centerPanel, BorderLayout.CENTER);
    outer.add(southPanel, BorderLayout.SOUTH);

    return outer;
  }
}
