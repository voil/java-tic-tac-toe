package src.modules;

import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.util.Map;
import java.lang.Object;
import java.awt.image.*;
import java.util.HashMap;
import javax.imageio.ImageIO;
import java.text.MessageFormat;

import src.game;

/**
 * Class of gameplay scene.
 */
public class gameplay {
  /**
   * @var {String} result
   */
  static String result = "draw";

  /**
   * @var {String} turn
   */
  static String turn = "x";

  /**
   * @var {String[]} tableElements
   */
  static String[] tableElements = {
		"top-left", "top", "top-right",
		"middle-left", "middle", "middle-right",
		"bottom-left", "bottom", "bottom-right",
  };

  /**
   * @var {Map<String, Map<String, Object>>} table
   */
  static Map<String, Map<String, Object>> table = null;

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
   * Public method to get result of play.
   * @public
   * @return {String}
   */
  public static String GetResult() {
    return result;
  }

  /**
   * Public method to set result of play.
   * @public
   * @param {String} type
   */
  public static void SetResult(String type) {
    result = type;
  }

  /**
   * Private method for create table board.
   * @private
   * @param {JPanel} panel
   */
  public static void CreateTableBoard(JPanel panel) {
    table = new HashMap<>();
    GridBagConstraints gridBag = new GridBagConstraints();

    gridBag.gridx = 0;
    gridBag.gridy = 0;

    for (String element : tableElements) {
      Map<String, Object> item = new HashMap<>();

      item.put("taken", false);
      item.put("type", "");

      JButton button = new JButton();
      button.setFocusPainted(false);
      button.setContentAreaFilled(false);
  
      button.addActionListener(e -> {
        Object[] params = new Object[]{turn};
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream input = classLoader.getResourceAsStream(MessageFormat.format("./assets/{0}.png", params));
            BufferedImage image = ImageIO.read(input);
        
            button.setIcon(new ImageIcon(image));
            button.setEnabled(false);

            table.get(element).put("taken", true);
            table.get(element).put("type", turn);

            ChangeTurn();
            CheckIsEndGame();
        }
        catch(IOException error) {
          error.printStackTrace();
        }
      });
      
      panel.add(button, gridBag);

      if ( gridBag.gridy == 2) {
        gridBag.gridy = 0;
        gridBag.gridx += 1;
      } else {
        gridBag.gridy += 1;
      }

      table.put(element, item);
    }
  }

  /**
   * Private method for change turn of player.
   * @private
   */
  private static void ChangeTurn() {
    turn = turn == "x" ? "o" : "x";
  }

  /**
   * Private method for check is game end.
   * @private
   */
  private static void CheckIsEndGame() {
    Integer countTakenElements = 0;
    for (String element : tableElements) {
      CheckIsPlayerWinner(element, "x");
      CheckIsPlayerWinner(element, "o");
      countTakenElements = CheckIsDraw(countTakenElements,  element);
    }
  }

  /**
   * Private method for check is player winner.
   * @private
   * @param {String} element
   * @param {String} typePlayer
   */
  private static void CheckIsPlayerWinner(String element, String typePlayer) {
    Map<String, String[][]> arrayOfSolutions = new HashMap<>();

    arrayOfSolutions.put("top-left", new String[][] {
      {"top-left", "top", "top-right"},
      {"top-left", "middle-left", "bottom-left"},
      {"top-left", "middle", "bottom-right"}
    });
    arrayOfSolutions.put("top", new String[][] {
      {"top", "middle", "bottom"},
    });
    arrayOfSolutions.put("top-right", new String[][] {
      {"top-right", "middle-right", "bottom-right"},
    });
    arrayOfSolutions.put("middle-left", new String[][] {
      {"middle-left", "middle", "middle-right"},
    });
    arrayOfSolutions.put("bottom-left", new String[][] {
      {"bottom-left", "bottom", "bottom-right"},
      {"bottom-left", "middle", "top-right"},
    });


    if(!arrayOfSolutions.containsKey(element)) {
      return;
    }

    for(String[] value: arrayOfSolutions.get(element)) {
      if(table.get(value[0]).get("type") == typePlayer &&
        table.get(value[1]).get("type") == typePlayer &&
        table.get(value[2]).get("type") == typePlayer) {
        SetResult(typePlayer.toUpperCase());
        results.ShowScene();
      }
    }
  }

  /**
   * Private method to check is draw.
   * @private
   * @param {Integer} result
   * @param {String} element
   * @return {Integer}
   */
  private static Integer CheckIsDraw(Integer result, String element) {
    if (table.get(element).get("taken").equals(true) == true) {
      result += 1;
      if (result == 9) {
        SetResult("draw");
        results.ShowScene();
        return result;
      }
    }

    return result;
  }

  /**
   * Private method to create scene panel.
   * @private
   * @return {JPanel}
   */
  private static JPanel CreateScene() {
    JPanel panel = new JPanel();

    GridLayout grid = new GridLayout(3, 3);
    panel.setLayout(grid);

    CreateTableBoard(panel);
    return panel;
  }
}
