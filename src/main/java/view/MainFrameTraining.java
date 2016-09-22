package view;

import model.app_db.factory.DefaultData;
import model.roles.Gymnastic;
import view.view_components.components.*;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

import static java.awt.FlowLayout.TRAILING;

/**
 * MainFrameTraining
 */
public class MainFrameTraining extends MainFrame {
    private final JTabbedPane pane = new JTabbedPane();

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static int sizeWidth = 800;
    public static int sizeHeight = 600;
    public int locationX = (screenSize.width - sizeWidth) / 2;
    public int locationY = (screenSize.height - sizeHeight) / 2;

    List<Gymnastic> defaultGymnastics = new DefaultData().createListOfDefaultGymnastics();

    public MainFrameTraining() throws HeadlessException {

        setLayout(new BorderLayout());
        setTitle("Sport Application");

        setBounds(locationX, locationY, sizeWidth, sizeHeight);

        setBounds(locationX, locationY, sizeWidth, sizeHeight);


        addComponents(getContentPane());
        getRootPane().setBackground(Color.orange);

        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);


    }

    private void addComponents(Container contentPane) {
        contentPane.setLayout(new BorderLayout());

        Font font = new Font("Tamoha", Font.BOLD, 16);
//        MenuLookDemo md = new MenuLookDemo();
//        JMenuBar menuBar = md.createMenuBar();
//        menuBar.setBackground(Color.ORANGE.brighter());
//        menuBar.setFont(font);
        MainFrameMenu mfm = new MainFrameMenu();
        JMenuBar menuBar = mfm.createMenuBar();
        menuBar.setBackground(Color.ORANGE.brighter());


        Font fontButtons = new Font("Verdana", Font.PLAIN, 8);

        JButton scs = new VerticalButton("Button 1", false);
        scs.setFont(font);
        scs.setPreferredSize(new Dimension(25, 100));

        JButton scs2 = new VerticalButton("Button 2", false);
        scs2.setFont(font);
        scs.setPreferredSize(new Dimension(25, 100));

        JButton scs3 = new VerticalButton("Button 3", false);
        scs3.setFont(font);
        scs3.setPreferredSize(new Dimension(25, 50));

        JPanel panel = new JPanel(new GridLayout(0, 1));
        //ставим размеры панели
        panel.setPreferredSize(new Dimension(20, 50));
        panel.add(scs);
        panel.add(scs2);
//
       /* *//**
         * Работает на 1 вкладку
         *//*

        JTabbedPane pane = new JTabbedPane();
        final int tabNumber = 2;
        pane.removeAll();
        pane.add("Tab1", new JLabel("Tab1"));
        pane.setTabComponentAt(0,
                new ButtonTabComponent(pane));

        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);*/
//
        //внутренняя панель для MainFrameTraining
        JPanel innerTrainingPanelForCenter = new JPanel(new BorderLayout());
        //вернхние кнопки логаунт и тд тут же будет окно поиска
        JPanel innerTrainingPanelNorthPanel = new JPanel(new BorderLayout());
        JButton logout = new JButton("LogOut");
        innerTrainingPanelNorthPanel.add(logout, BorderLayout.EAST);

        //создание элемента вкладок для упражнений
        JTabbedPane pane = new JTabbedPane();

//        for (int i = 0; i < defaultGymnastics.size(); i++) {
//            String title = "Вкладка " + i;
//            pane.add(title, new JLabel(title));//это поле самой вкладки в виде new JLabel
//            pane.setTabComponentAt(i,
//                    new ButtonTabComponent(pane));
//        }
        int i = 0;
        for (Gymnastic gym : defaultGymnastics) {
            String title = gym.getName();
            pane.add(title, panelForGymCreate(gym));//это поле самой вкладки в виде new JLabel
            pane.setTabComponentAt(i,
                    new ButtonTabComponent(pane));
            i++;
        }
        //  pane.add("+", new JLabel());
        // pane.add("JPanel", new JPanel());
        //
        innerTrainingPanelForCenter.add(innerTrainingPanelNorthPanel, BorderLayout.NORTH);
        innerTrainingPanelForCenter.add(pane, BorderLayout.CENTER);

        //заполенине самого MainFrameTraining
        contentPane.setBackground(Color.ORANGE.darker());
        contentPane.add(menuBar, BorderLayout.NORTH);
        contentPane.add(panel, BorderLayout.LINE_START);
        contentPane.add(innerTrainingPanelForCenter, BorderLayout.CENTER);

    }

    public JPanel panelForGymCreate(Gymnastic gym) {
        JPanel panelForThisGym = new JPanel(new BorderLayout());

        panelForThisGym.add(new JLabel("Тут будет некоторое описание самого упражнения или описание использования данного окна"), BorderLayout.NORTH);
        //панель записи текущего результата на текущей тренеровке
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder(gym.getName())));
        /**
         *  Kепим таблицу
         * заполняем заголовки табилцы
         */

        JTable curentGymnastycResaltsTable = createjTableHandelsAndData(3);
        //выделять только 1 ячейку
        curentGymnastycResaltsTable.setCellSelectionEnabled(true);
        //размер таблицы
        curentGymnastycResaltsTable.setPreferredSize(new Dimension(200, 50));
        curentGymnastycResaltsTable.setFillsViewportHeight(true);
        //scrollPaneForCurrentResTable для отображения таблицы с именами колонок
        JScrollPane scrollPaneForCurrentResTable = new JScrollPane(curentGymnastycResaltsTable);
        //размер скрола
        scrollPaneForCurrentResTable.setPreferredSize(new Dimension(200, 40));

        /**
         * лепим считчер подходов
         */
        JLabel chooseRepeatLabel = new JLabel("Количество подходов: ");
        JRadioButton setBy3 = new JRadioButton("3 подхода");
        JRadioButton setBy5 = new JRadioButton("5 подходов");
        JRadioButton setBy7 = new JRadioButton("7 подходов");

        ButtonGroup selectNumberOfGymnasticSets = new ButtonGroup();
        selectNumberOfGymnasticSets.add(setBy3);
        selectNumberOfGymnasticSets.add(setBy5);
        selectNumberOfGymnasticSets.add(setBy7);

        JPanel chooseRepeatOfSetPanel = new JPanel(new FlowLayout(SwingConstants.HORIZONTAL));
        // chooseRepeatOfSetPanel.setBounds(60, 80, 550, 45);

        JButton setDefaultRepeatOfSets = new JButton("Установить по умолчанию(3)");
        setDefaultRepeatOfSets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tablePanel.setVisible(false);
                JPanel tablePanel2 = panelForGymCreate(gym);
//                JPanel tablePanel2 = new JPanel(new BorderLayout());
//                tablePanel2.setBorder(new CompoundBorder(new EmptyBorder(12, 12, 12, 12), new TitledBorder(gym.getName())));
//                /**
//                 *  Kепим таблицу
//                 * заполняем заголовки табилцы
//                 */
//
//                JTable curentGymnastycResaltsTable = createjTableHandelsAndData(3);
//                //выделять только 1 ячейку
//                curentGymnastycResaltsTable.setCellSelectionEnabled(true);
//                //размер таблицы
//                curentGymnastycResaltsTable.setPreferredSize(new Dimension(200, 50));
//                curentGymnastycResaltsTable.setFillsViewportHeight(true);
//                //scrollPaneForCurrentResTable для отображения таблицы с именами колонок
//                JScrollPane scrollPaneForCurrentResTable2 = new JScrollPane(curentGymnastycResaltsTable);
//                //размер скрола
//                scrollPaneForCurrentResTable2.setPreferredSize(new Dimension(200, 40));
//                //заполняем панель добавления резальтатов текущей тренировки
//                tablePanel2.add(new JLabel("ЗАПОЛНИТЕ РЕЗУЛЬТАТЫ ТЕКУЩЕЙ ТРЕНИРОВКИ"), BorderLayout.NORTH);
//                tablePanel2.add(scrollPaneForCurrentResTable2, BorderLayout.CENTER);
//                tablePanel2.add(chooseRepeatOfSetPanel, BorderLayout.SOUTH);
//                tablePanel2.setVisible(true);
                panelForThisGym.add(tablePanel2, BorderLayout.NORTH);
                panelForThisGym.revalidate();
            }
        });
        setDefaultRepeatOfSets.hide();

        JButton confirmSetButton = new JButton("Сменить количество подходов");
        chooseRepeatOfSetPanel.add(confirmSetButton);
        confirmSetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String radioButtonText = getSelectedButtonText(selectNumberOfGymnasticSets);
                if (radioButtonText == null) {
                    JOptionPane.showMessageDialog(new JFrame(), "пожалуйста выберете количество подходов");
                    return;
                }

                switch (radioButtonText) {


                    case "5 подходов":
                        setBy3.hide();
                        setBy7.hide();
                        confirmSetButton.hide();
                        setDefaultRepeatOfSets.show();

                        scrollPaneForCurrentResTable.setVisible(false);
                        JTable curentGymnastycResaltsTable5 = createjTableHandelsAndData(5);
                        curentGymnastycResaltsTable5.setCellSelectionEnabled(true);
                        curentGymnastycResaltsTable5.setPreferredSize(new Dimension(200, 50));
                        curentGymnastycResaltsTable5.setFillsViewportHeight(true);
                        JScrollPane scrollPaneForCurrentResTable5 = new JScrollPane(curentGymnastycResaltsTable5);
                        scrollPaneForCurrentResTable5.setPreferredSize(new Dimension(200, 40));

                        tablePanel.add(scrollPaneForCurrentResTable5, BorderLayout.CENTER);
                        tablePanel.revalidate();
                        tablePanel.setVisible(true);
                        break;
                    case "3 подхода":

                        setBy5.hide();
                        setBy7.hide();
                        confirmSetButton.hide();
                        setDefaultRepeatOfSets.show();

                        scrollPaneForCurrentResTable.setVisible(false);
                        JTable curentGymnastycResaltsTable3 = createjTableHandelsAndData(3);
                        curentGymnastycResaltsTable3.setCellSelectionEnabled(true);
                        curentGymnastycResaltsTable3.setPreferredSize(new Dimension(200, 50));
                        curentGymnastycResaltsTable3.setFillsViewportHeight(true);
                        JScrollPane scrollPaneForCurrentResTable3 = new JScrollPane(curentGymnastycResaltsTable3);
                        scrollPaneForCurrentResTable3.setPreferredSize(new Dimension(200, 40));

                        tablePanel.add(scrollPaneForCurrentResTable3, BorderLayout.CENTER);
                        tablePanel.revalidate();
                        tablePanel.setVisible(true);
                        break;
                    case "7 подходов":
                        setBy3.hide();
                        setBy5.hide();
                        confirmSetButton.hide();
                        setDefaultRepeatOfSets.show();

                        scrollPaneForCurrentResTable.setVisible(false);
                        JTable curentGymnastycResaltsTable7 = createjTableHandelsAndData(7);
                        curentGymnastycResaltsTable7.setCellSelectionEnabled(true);
                        curentGymnastycResaltsTable7.setPreferredSize(new Dimension(200, 50));
                        curentGymnastycResaltsTable7.setFillsViewportHeight(true);
                        JScrollPane scrollPaneForCurrentResTable7 = new JScrollPane(curentGymnastycResaltsTable7);
                        scrollPaneForCurrentResTable7.setPreferredSize(new Dimension(200, 40));

                        tablePanel.add(scrollPaneForCurrentResTable7, BorderLayout.CENTER);
                        tablePanel.revalidate();
                        tablePanel.setVisible(true);
                        break;
                }

            }
        });


        chooseRepeatOfSetPanel.add(chooseRepeatLabel);
        chooseRepeatOfSetPanel.add(setBy3);
        chooseRepeatOfSetPanel.add(setBy5);
        chooseRepeatOfSetPanel.add(setBy7);
        chooseRepeatOfSetPanel.add(confirmSetButton);
        chooseRepeatOfSetPanel.add(setDefaultRepeatOfSets);


        //заполняем панель добавления резальтатов текущей тренировки
        tablePanel.add(new JLabel("ЗАПОЛНИТЕ РЕЗУЛЬТАТЫ ТЕКУЩЕЙ ТРЕНИРОВКИ"), BorderLayout.NORTH);
        tablePanel.add(scrollPaneForCurrentResTable, BorderLayout.CENTER);
        tablePanel.add(chooseRepeatOfSetPanel, BorderLayout.SOUTH);

        panelForThisGym.add(tablePanel, BorderLayout.NORTH);
        panelForThisGym.add(new JPanel(), BorderLayout.SOUTH);

        return panelForThisGym;
    }

    private JTable createjTableHandelsAndData(int chooseNumberOfRepats) {
        String[] columnHeads = new String[chooseNumberOfRepats + 1];
        for (int i = 0; i < (chooseNumberOfRepats + 1); i++) {
            columnHeads[i] = "Подход " + (i + 1);
        }
        columnHeads[columnHeads.length - 1] = "Всего";

        //заполняем пустотой чтобы не было нуль эксепшена
        Object[][] data = new Object[1][chooseNumberOfRepats + 1];
        for (int i = 0; i < (chooseNumberOfRepats + 1); i++) {
            data[0][i] = "";

        }

        return new JTable(data, columnHeads);
    }

    private String getSelectedButtonText(ButtonGroup buttonGroup) {
        for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                return button.getText();
            }
        }

        return null;
    }

    public void runTest() {
        int tabNumber = 5;
        pane.removeAll();
        for (int i = 0; i < tabNumber; i++) {
            String title = "Tab " + i;
            pane.add(title, new JLabel(title));
            initTabComponent(i);
        }

        pane.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initTabComponent(int i) {
        pane.setTabComponentAt(i,
                new ButtonTabComponent(pane));
    }

}
