package org.vaadin.addons.tatu;

import java.util.List;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("column-chart")
public class ColumnChartElement extends TestBenchElement {

    DivElement getColumn(int index) {
        return getColumns().get(index);
    }

    List<DivElement> getColumns() {
        return this.$(DivElement.class).attribute("class", "segment").all();
    }

    DivElement getContainer() {
        return this.$(DivElement.class).attribute("class", "container").first();
    }

    String getWidth() {
        return getContainer().getCssValue("width");
    }

    String getHeight() {
        return getContainer().getCssValue("height");
    }
}
