package src;

import javax.swing.*;

/**
 * Main class for game.
 */
public class game {
  /**
   * @var {String} name
   */
  static String name = "Tic Tac Toe";

  /**
   * @var {JFrame} frame
   */
  static JFrame frame = null;

  /**
   * Public method to create main window instance.
   * @public
   */
  public static void CreateWindow() {
    frame = new JFrame(name);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(212,212);

    frame.setVisible(true);
  }

  /**
   * Public method to get window instance.
   * @public
   */
  public static JFrame GetWindow() {
    return frame;
  }
}