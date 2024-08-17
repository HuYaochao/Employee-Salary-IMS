package com.company.util;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;

/**
 *
 * @author hyc
 * Date: 2024/8/15
 * @version 1.0
 */


public class TablePrinter {
    // 使用等宽字体
    private static final Font FONT = new Font("Monospaced", Font.PLAIN, 12);
    // 最大列宽限制
    private static final int MAX_COLUMN_WIDTH = 15;

    public static void printTable(String[] headers, String[][] data) {
        // 计算每列的最大宽度
        int[] columnWidths = calculateColumnWidths(headers, data);

        // 打印表头
        printSeparator(columnWidths);
        printRow(headers, columnWidths);
        printSeparator(columnWidths);

        // 打印数据行
        for (String[] row : data) {
            printRow(row, columnWidths);
        }
        printSeparator(columnWidths);
    }

    private static int[] calculateColumnWidths(String[] headers, String[][] data) {
        int[] columnWidths = new int[headers.length];
        FontRenderContext context = new FontRenderContext(null, true, true);

        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = (int) Math.min(getTextWidth(headers[i], context), MAX_COLUMN_WIDTH);
        }

        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                if (row[i] != null) {
                    int width = (int) Math.min(getTextWidth(row[i], context), MAX_COLUMN_WIDTH);
                    columnWidths[i] = Math.max(columnWidths[i], width);
                }
            }
        }

        return columnWidths;
    }

    private static double getTextWidth(String text, FontRenderContext context) {
        TextLayout layout = new TextLayout(text, FONT, context);
        return layout.getAdvance();
    }

    private static void printRow(String[] row, int[] columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < row.length; i++) {
            String cell = (row[i] == null) ? "" : row[i];
            sb.append("| ").append(String.format("%-" + columnWidths[i] + "s", cell)).append(" ");
        }
        sb.append("|");
        System.out.println(sb.toString());
    }

    private static void printSeparator(int[] columnWidths) {
        StringBuilder sb = new StringBuilder();
        for (int width : columnWidths) {
            sb.append("+").append("-".repeat(width + 1));
        }
        sb.append("+");
        System.out.println(sb.toString());
    }
}





