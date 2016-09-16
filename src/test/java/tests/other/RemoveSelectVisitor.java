/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests.other;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.statement.select.Distinct;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectExpressionItem;
import net.sf.jsqlparser.statement.select.SelectItem;
import net.sf.jsqlparser.statement.select.SelectVisitor;
import net.sf.jsqlparser.statement.select.SetOperationList;
import net.sf.jsqlparser.statement.select.WithItem;

/**
 *
 * @author Eugen
 */
class RemoveSelectVisitor implements SelectVisitor {

    RemoveCheck columnCheck;

    public RemoveSelectVisitor(RemoveCheck columnCheck) {
        this.columnCheck = columnCheck;
    }

    @Override
    public void visit(PlainSelect plainSelect) {
        List<Expression> groupByColumnReferences = plainSelect.getGroupByColumnReferences();
        if (groupByColumnReferences != null) {
            for (Expression groupByColumnReference : groupByColumnReferences) {
                RemoveExpressionVisitor expressionVisitor = new RemoveExpressionVisitor(columnCheck);
                groupByColumnReference.accept(expressionVisitor);
            }
        }

        List<SelectItem> selectItems = plainSelect.getSelectItems();
        if (selectItems != null) {
            Iterator<SelectItem> iterator = selectItems.iterator();
            while (iterator.hasNext()) {
                SelectItem selectItem = iterator.next();
                RemoveSelectItemVisitor itemVisitor = new RemoveSelectItemVisitor(columnCheck);
                selectItem.accept(itemVisitor);
                if (selectItem instanceof SelectExpressionItem) {
                    SelectExpressionItem expressionItem = (SelectExpressionItem) selectItem;
                    Alias alias = expressionItem.getAlias();
                    Expression expression = expressionItem.getExpression();
                    if (alias == null || expression == null) {
                        iterator.remove();
                    }
                }
            }
        }
        plainSelect.setSelectItems(selectItems);

        Distinct distinct = plainSelect.getDistinct();
        if (distinct != null) {
            List<SelectItem> onSelectItems = distinct.getOnSelectItems();
            if (onSelectItems != null) {
                for (SelectItem onSelectItem : onSelectItems) {
                    RemoveSelectItemVisitor itemVisitor = new RemoveSelectItemVisitor(columnCheck);
                    onSelectItem.accept(itemVisitor);
                }
            }
        }

//        Table forUpdateTable = plainSelect.getForUpdateTable();
//        if (forUpdateTable != null) {
//            forUpdateTable.accept(this);
//        }
//
//        List<OrderByElement> orderByElements = plainSelect.getOrderByElements();
//        if (orderByElements != null) {
//            for (OrderByElement orderByElement : orderByElements) {
//                orderByElement.accept(this);
//            }
//        }
        Expression where = plainSelect.getWhere();
        if (where != null) {
            RemoveExpressionVisitor expressionVisitor = new RemoveExpressionVisitor(columnCheck);
            where.accept(expressionVisitor);
        }
    }

    @Override
    public void visit(SetOperationList setOpList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void visit(WithItem withItem) {
        List<SelectItem> withItemList = withItem.getWithItemList();
        if (withItemList != null) {
            for (SelectItem selectItem : withItemList) {
                RemoveSelectItemVisitor itemVisitor = new RemoveSelectItemVisitor(columnCheck);
                selectItem.accept(itemVisitor);
            }
        }
        withItem.getSelectBody().accept(this);
    }

}
