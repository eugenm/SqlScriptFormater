/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.other;

import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.ExpressionVisitor;
import net.sf.jsqlparser.parser.CCJSqlParser;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.SetStatement;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.StatementVisitor;
import net.sf.jsqlparser.statement.Statements;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.AlterView;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.drop.Drop;
import net.sf.jsqlparser.statement.execute.Execute;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.merge.Merge;
import net.sf.jsqlparser.statement.replace.Replace;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SelectBody;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.truncate.Truncate;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.SelectUtils;
import net.sf.jsqlparser.util.TablesNamesFinder;
import net.sf.jsqlparser.util.deparser.ExpressionDeParser;
import net.sf.jsqlparser.util.deparser.SelectDeParser;
import net.sf.jsqlparser.util.deparser.StatementDeParser;

/**
 *
 * @author Eugen
 */
public class SqlParserTest {

    public static String SQL = getSQLString();

    StringReader stringReader = new StringReader(SQL);
    CCJSqlParser parser = new CCJSqlParser(stringReader);

    private static String getSQLString() {
        return "CREATE OR REPLACE VIEW ELAN_MENUES_DE_VIEW AS\n"
                + "SELECT    c_programm, m.c_menue_parent, m.c_menue_code, m.i_menue_sortierung, m.c_menue_typ, 'D' c_sprache_lut,\n"
                + "      (SELECT t.c_menue_text FROM elan_menues_texte t WHERE t.c_programm = m.c_programm AND t.c_sprache_lut = 'D'\n"
                + "        AND t.c_menue_code = m.c_menue_code) c_menue_text,\n"
                + "            (SELECT t.dec_menue_wert FROM elan_menues_texte t WHERE t.c_programm = m.c_programm AND t.c_sprache_lut = 'D'\n"
                + "        AND t.c_menue_code = m.c_menue_code) dec_menue_wert,\n"
                + "      m.c_objekt, m.c_aktion, m.c_lizenz_id\n"
                + "  FROM    elan_menues m\n"
                + "  START WITH   c_menue_parent = 'ROOT'\n"
                + "  CONNECT BY   m.c_menue_parent = prior m.c_menue_code\n"
                + "  ORDER SIBLINGS BY i_menue_sortierung;";
    }

    public static void main(String[] args) {

        try {
            Statement stmt = CCJSqlParserUtil.parse(SQL);

            System.out.println("Original Parsed Statement:\n" + stmt.toString());

            // apply RemoveVisitor
            String columnPart = "_lut";
            RemoveColumnVisitor visitor = new RemoveColumnVisitor(columnPart);
            stmt.accept(visitor);

            // build Statement
            StringBuilder buffer = new StringBuilder();
            StatementDeParser deParser = new StatementDeParser(buffer);
            stmt.accept(deParser);

            System.out.println("After RemoveVisitor applied:\n" + buffer.toString());

//            if (stmt instanceof CreateView) {
//                System.out.println("true");
//            } else {
//                System.out.println("false");
//            }
//            String name = createstmt.getView().getName();
//
//            boolean orReplace = createstmt.isOrReplace();
//            boolean materialized = createstmt.isMaterialized();
//
//            SelectBody selectBody = createstmt.getSelectBody();
//            String o_toString = selectBody.toString();
//
//            SelectVisitor selectVisitor = new SelectDeParser();
//            ExpressionDeParser expressionDeParser = new ExpressionDeParser();
//            selectBody.accept(selectVisitor);
//
//            System.out.println(orReplace + " " + materialized + " " + name + " " + o_toString);
        } catch (JSQLParserException ex) {
            Logger.getLogger(SqlParserTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
