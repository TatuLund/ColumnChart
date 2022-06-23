package org.vaadin.addons.tatu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.vaadin.addons.tatu.ColumnChart.ColumnChartAxis;
import org.vaadin.addons.tatu.ColumnChart.ColumnChartValue;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.AlignItems;
import com.vaadin.flow.theme.lumo.LumoUtility.Display;
import com.vaadin.flow.theme.lumo.LumoUtility.JustifyContent;

@Route("")
@CssImport("./styles.css")
@NpmPackage(value = "lumo-css-framework", version = "^4.0.10")
public class View extends TabSheet implements AppShellConfigurator {

    public View() {
    	setSizeFull();
    	addThemeVariants(TabSheetVariant.LUMO_CENTERED);

        ColumnChart columns1 = new ColumnChart();
        columns1.setValues(getValues());
        columns1.setWidth(350);
        columns1.setHeight(600);
        columns1.setAxis(ColumnChartAxis.RIGHT);

        ColumnChart columns2 = new ColumnChart();
        columns2.setNumbers(Arrays.asList(5, 33.33, 3.14, 25.5, 5, 12, -2.2323, 23.2, 1, 4, 5.2321, 7, 0.5, -11.5, 22.321212, -7.12, 9.1, 17));
        columns2.setWidth(500);
        columns2.setHeight(300);
        columns2.setColored(true);
        columns2.setAxis(ColumnChartAxis.LEFT);

        ColumnChart columns3 = new ColumnChart();
        columns3.setNumbers(Arrays.asList(5, 11.33, 5, 12, -2.2323, 23.2, 1, 4, 5.2321, 7, 0.5, -11.5, -22.321212, -7.12, 9.1));
        columns3.setWidth(250);
        columns3.setHeight(100);
        columns3.setColor("red");
        columns3.setAxis(ColumnChartAxis.LEFT);

        addTab("/w Captions",wrapit(columns1));
        addTab("Colored",wrapit(columns2));
        addTab("Small",wrapit(columns3));
    }

	private Div wrapit(ColumnChart columns) {
		Div div1 = new Div(columns);
        div1.setSizeFull();
        div1.addClassNames(AlignItems.CENTER,Display.FLEX,JustifyContent.CENTER);
		return div1;
	}

    private List<ColumnChartValue> getValues() {
        List<ColumnChartValue> values = new ArrayList<ColumnChartValue>();
        ColumnChartValue v0 = new ColumnChartValue(5.11,"Mercedes");
        ColumnChartValue v1 = new ColumnChartValue(-2.15,"BMW");
        ColumnChartValue v2 = new ColumnChartValue(11.23,"Toyota");
        ColumnChartValue v3 = new ColumnChartValue(7.12,"Ford");
        ColumnChartValue v4 = new ColumnChartValue(-1.12,"Kia");
        ColumnChartValue v5 = new ColumnChartValue(-4.31,"Seat");
        ColumnChartValue v6 = new ColumnChartValue(0.51,"SAAB");
        ColumnChartValue v7 = new ColumnChartValue(-3.25,"Volvo");
        ColumnChartValue v8 = new ColumnChartValue(8.25,"Honda");        
        ColumnChartValue v9 = new ColumnChartValue(10.11,"Opel");
        values.add(v0);
        values.add(v1);
        values.add(v2);
        values.add(v3);
        values.add(v4);
        values.add(v5);
        values.add(v6);
        values.add(v7);
        values.add(v8);
        values.add(v9);
        return values;
    }

}
