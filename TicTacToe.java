import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener {
  private JButton[][] gridButtons;
  private char[][] grid = new char[3][3];
  private char currentPlayer = 'X';

  public TicTacToe() {
    super("Tic Tac Toe");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(3, 3));
    gridButtons = new JButton[3][3];
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        gridButtons[r][c] = new JButton("-");
        add(gridButtons[r][c]);
        gridButtons[r][c].addActionListener(this);
        grid[r][c] = '-';
      }
    }
    setSize(300, 300);
    setVisible(true);
  }

  public void actionPerformed(ActionEvent e) {
    JButton clickedButton = (JButton)e.getSource();
    int row = -1, col = -1;
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (gridButtons[r][c] == clickedButton) {
          row = r;
          col = c;
          break;
        }
      }
    }
    if (row == -1 || col == -1 || grid[row][col] != '-') {
      JOptionPane.showMessageDialog(null, "Invalid move. Please try again.");
      return;
    }
    grid[row][col] = currentPlayer;
    clickedButton.setText(Character.toString(currentPlayer));
    if (checkWin(currentPlayer)) {
      JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " has won!");
      System.exit(0);
    } else if (checkTie()) {
      JOptionPane.showMessageDialog(null, "Tie game!");
      System.exit(0);
    }
    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
  }

  public boolean checkWin(char player) {
    for (int i = 0; i < 3; i++) {
      if (grid[i][0] == player && grid[i][1] == player && grid[i][2] == player) {
        return true;
      }
      if (grid[0][i] == player && grid[1][i] == player && grid[2][i] == player) {
        return true;
      }
    }
    if (grid[0][0] == player && grid[1][1] == player && grid[2][2] == player) {
      return true;
    }
    if (grid[0][2] == player && grid[1][1] == player && grid[2][0] == player) {
      return true;
    }
    return false;
  }

  public boolean checkTie() {
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        if (grid[r][c] == '-') {
          return false;
        }
      }
    }
    return true;
  }

  public static void main(String[] args) {
    new TicTacToe();
  }
}