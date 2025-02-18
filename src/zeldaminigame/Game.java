package zeldaminigame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import zeldaminigame.entities.Enemy;
import zeldaminigame.entities.Player;
import zeldaminigame.resources.Spritesheet;
import zeldaminigame.world.World;

public class Game extends Canvas implements Runnable, KeyListener {
    public static int WIDTH = 640, HEIGHT = 480;

    public static Player player;

    public static int SCALE = 1;

    public World world;

    public List<Enemy> enemies = new ArrayList<Enemy>();

    public Game() {
        this.addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        new Spritesheet();

        world = new World();

        player = new Player(32, 32);

        enemies.add(new Enemy(32, 32));
    }

    public static void main(String[] args) {
        Game game = new Game();
        JFrame jf = new JFrame();

        jf.add(game);
        jf.setTitle("Mini Zelda");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setResizable(false);
        jf.setVisible(true);

        new Thread(game).start();
    }

    // Método responsável pela lógica do jogo
    // (checar colisões, posições, inputs etc.)
    public void tick() {
        // Atualiza o estado do jogador com base nas entradas do teclado
        player.tick();

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
        }
    }

    // Método responsável pela renderização gráfica do jogo
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();

        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        world.render(g);

        player.render(g);

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).render(g);
        }

        bs.show();
    }

    @Override
    public void run() {
        while (true) {
            // System.out.println("Chamando game looping.");
            tick();
            render();

            try {
                // Limitando a 60 FPS
                Thread.sleep(1000 / 60);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.isShooting = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_Z) {
            player.isShooting = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}