package com.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockSymbolMain {

    public static void main(String[] args) {
        // 加載 Spring 配置文件
        ApplicationContext ctx = new ClassPathXmlApplicationContext("/WEB-INF/resources/beans.xml");

        // 獲取 JdbcTemplate 實例
        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);

        // 定義 SQL 插入語句
        String insertQuery = "INSERT INTO StockAnalysisSystem.Symbol (stockSymbol) VALUES (?)";

     // 定义要插入的数据
        String[] stockSymbols = {"3481", "00929", "00637L","2353","2409","2609","00919","00680L","2303","2891","0056","00878","2330","006208","00922","1216","3209","2603","2356","0050"};

        // 执行批量 SQL 插入操作
        jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, stockSymbols[i]);
            }

            @Override
            public int getBatchSize() {
                return stockSymbols.length;
            }
        });

        System.out.println("批量插入完成！");
    }
}
