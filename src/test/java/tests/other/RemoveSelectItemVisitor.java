/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.other;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.statement.select.AllColumns;
import net.sf.jsqlparser.statement.select.AllTableColumns;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItemVisitor;

/**
 *
 * @author Eugen
 */
class RemoveSelectItemVisitor implements SelectItemVisitor {

    RemoveCheck columnCheck;

    RemoveSelectItemVisitor(RemoveCheck columnCheck) {
        this.columnCheck = columnCheck;
    }

    @Override
    public void visit(AllColumns allColumns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(AllTableColumns allTableColumns) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(SelectExpressionItem selectExpressionItem) {
        Alias alias = selectExpressionItem.getAlias();
        Expression expression = selectExpressionItem.getExpression();
        if (alias != null) {
            String name = alias.getName();
            if (columnCheck.check(name)) {
                selectExpressionItem.setAlias(null);
            }
        }
        if (expression != null) {
            if (expression instanceof StringValue) {
                StringValue value = (StringValue) expression;
                if (columnCheck.check(value.getValue())) {
                    selectExpressionItem.setExpression(null);
                }
            } else {
                RemoveExpressionVisitor expressionVisitor = new RemoveExpressionVisitor(columnCheck);
                expression.accept(expressionVisitor);
            }
        }
    }

}
