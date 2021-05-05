package Tetris;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TetrisGame extends Frame{

    Brick[][] board = new Brick[20][10];
    BufferedImage backboard;
    int level = 3;
    int speed = 1;
    Point position = new Point(5, 0);
    boolean playing = true;
    int currentBlock = 0;
    //0: line 1: T
    int rotation = 0;

    public TetrisGame() throws InterruptedException, IOException, FontFormatException {
        super(32 * 12, 32 * 20);
        backboard = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics gBoard = backboard.getGraphics();
        Brick wall = new Brick(Color.GRAY);
        Brick back = new Brick(Color.DARK_GRAY.darker());
        for(int i = 0; i < getHeight(); i += 32){
            gBoard.drawImage(wall.sprite, 0, i, this);
            gBoard.drawImage(wall.sprite, getWidth() - 48, i, this);
            for(int j = 32; j < getWidth() - 64; j += 32){
                gBoard.drawImage(back.sprite, j, i, this);
            }
        }
        g.drawImage(backboard, 0, 0, this);

        KeyListener k = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        break;
                    case KeyEvent.VK_LEFT:
                        leftMove();
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightMove();
                        break;
                    case KeyEvent.VK_DOWN:
                        speed = 5;
                        break;
                    case KeyEvent.VK_ESCAPE:
                        playing = false;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_DOWN) speed = 1;

            }
        };
        this.addKeyListener(k);

        while(playing){
            tick();
        }

        Brick fail = new Brick(Color.RED);
        for(int j = getHeight() - 39; j >= 0; j -= 32){
            for(int i = 32; i < getWidth() - 48; i += 32){
                g.drawImage(fail.sprite, i, j, this);
                Thread.sleep(10);
            }
        }
        Font f = Font.createFont(Font.TRUETYPE_FONT, new File("APCSA/GraphicsTest/Minecraft.ttf")).deriveFont(Font.PLAIN, 50);
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(f);
        g.setColor(Color.WHITE);
        g.setFont(f);
        g.drawString("GAME OVER", 115, 60);
    }

    public void rightMove(){
        if(position.x != 9) {
            board[position.y][position.x] = null;
            position.x += 1;
            board[position.y][position.x] = new Brick(Color.BLUE);
            redraw();
        }
    }

    public void leftMove(){
        if(position.x != 0){
            board[position.y][position.x] = null;
            position.x -= 1;
            board[position.y][position.x] = new Brick(Color.BLUE);
            redraw();
        }
    }

    public void tick(){
        if(collisionTest()) {
            board[position.y][position.x] = null;
            position.y += 1;
        } else{
            boolean filled = true;
            for(int i = 0; i < board[0].length; i++){
                if(board[position.y][i] == null){
                    filled = false;
                    break;
                }
            }
            if(filled){
                for(int i = 0; i < board[0].length; i++){
                    board[position.y][i] = null;
                }
            } else{
                board[position.y][position.x] = new Brick(Color.BLUE);
            }
            position = new Point(5, 0);
        }
        if(board[0][5] != null) playing = false;
        board[position.y][position.x] = new Brick(Color.BLUE);
        redraw();
        try {
            Thread.sleep(800 / level / speed);
        } catch (InterruptedException e) {
            System.out.println("uh oh. java did an oopsie");
        }
    }

    public void redraw(){
        g.drawImage(backboard, 0, 0, this);
        for(int i = 0; i < board[0].length; i++){
            for(int j = 0; j < board.length; j++){
                if(board[j][i] != null)
                g.drawImage(board[j][i].sprite, 32 + 32 * i, 32 * j, this);
            }
        }
    }

    private boolean collisionTest(){
        if(position.y == 19) return false;
        switch(currentBlock){
            case 0:
                return board[position.y + 1][position.x] == null;

        }
        return true;
    }

}
