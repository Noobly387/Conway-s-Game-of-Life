import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class MainFrame extends JFrame implements KeyListener, MouseListener {

    private final int CELL_SIZE = 15;

    private final Color CELL_COLOR = new Color (0x7289da);
    private final Color BG_COLOR = new Color (0x23272a);
    private final Color GRID_COLOR = new Color (0x333333);

    public Simulation sim;

    private int genNum;

    public MainFrame (int height, int width) {
        this.sim = new Simulation (width, height);
        this.genNum = 1;

        setSize(width * CELL_SIZE, height * CELL_SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(BG_COLOR);
        setTitle("Conway's Game of Life");
        setVisible(true);

        addKeyListener(this);
        addMouseListener(this);

        repaint();
    }

    public void nextGen() {
        this.sim.advance();
        this.genNum += 1;
    }

    public int getSimHeight () {
        return this.sim.height;
    }

    public int getSimWidth () {
        return this.sim.width;
    }

    public void changeStatus (int x, int y) {
        if (this.sim.board[y][x] == 1)
            this.sim.setDead(x, y);
        else
            this.sim.setAlive(x, y);
    }

    public int getGenNum () {
        return this.genNum;
    }

    public void paint (Graphics g) {
        for (int y = 0; y < this.sim.board.length; y++) {
            for (int x = 0; x < this.sim.board[0].length; x++) {
                int xPaint = x * CELL_SIZE;
                int yPaint = y * CELL_SIZE;

                g.setColor((this.sim.board[y][x] == 1) ? CELL_COLOR : BG_COLOR);
                g.fillRect(xPaint, yPaint, CELL_SIZE, CELL_SIZE);

                g.setColor(GRID_COLOR);
                g.drawRect(xPaint, yPaint, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    public static void main (String[] args) {
        System.out.println("Welcome to Conway's Game of Life!");
        System.out.println("");

        MainFrame frame = new MainFrame(75,150);
        frame.setVisible(true);
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                nextGen();
                repaint();
        }
    }


    public void mousePressed(MouseEvent e) {
        int x = getCoordinate(e.getX(), 'x');
        int y = getCoordinate(e.getY(), 'y');
        System.out.println(e.getX() + " " + e.getY());
        changeStatus(x, y);
        repaint();
    }
    private int getCoordinate (int n, char axis) {
        int pos = n / CELL_SIZE;
        if (pos <= ((axis == 'x') ? getSimWidth() : getSimHeight())) {
            return pos;
        } return -1;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) { }
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }
}
