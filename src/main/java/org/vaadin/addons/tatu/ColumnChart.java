package org.vaadin.addons.tatu;

import java.util.List;
import java.util.Objects;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;

import elemental.json.JsonArray;
import elemental.json.JsonFactory;
import elemental.json.JsonObject;
import elemental.json.impl.JreJsonFactory;

@Tag("column-chart")
@JsModule("./column-chart.ts")
public class ColumnChart extends Component {

    public enum ColumnChartAxis {
        RIGHT("rightaxis"), LEFT("leftaxis"), NONE("");

        String axis;

        ColumnChartAxis(String axis) {
            this.axis = axis;
        }

        @Override
        public String toString() {
            return axis;
        }
    }

    public static class ColumnChartValue {
        private Number number;
        private String caption;

        public ColumnChartValue(Number number, String caption) {
            Objects.requireNonNull(number, "number can't be null");
            Objects.requireNonNull(caption, "caption can't be null");
            this.number = number;
            this.caption = caption;
        }

        public Number getNumber() {
            return number;
        }

        public void setNumber(Number number) {
            Objects.requireNonNull(number, "number can't be null");
            this.number = number;
        }

        public String getCaption() {
            return caption;
        }

        public void setCaption(String caption) {
            Objects.requireNonNull(caption, "caption can't be null");
            this.caption = caption;
        }
    }

    public void setValues(List<ColumnChartValue> values) {
        Objects.requireNonNull(values, "values can't be null");
        JsonFactory factory = new JreJsonFactory();
        JsonArray numbersJson = factory.createArray();
        for (int i = 0; i < values.size(); i++) {
            JsonObject valueJson = factory.createObject();
            valueJson.put("number", values.get(i).getNumber().doubleValue());
            valueJson.put("caption", values.get(i).getCaption() + ":"
                    + values.get(i).getNumber().toString());
            numbersJson.set(i, valueJson);
        }
        getElement().setPropertyJson("values", numbersJson);
    }

    public void setNumbers(List<Number> numbers) {
        Objects.requireNonNull(numbers, "numbers can't be null");
        JsonFactory factory = new JreJsonFactory();
        JsonArray numbersJson = factory.createArray();
        for (int i = 0; i < numbers.size(); i++) {
            JsonObject valueJson = factory.createObject();
            valueJson.put("number", numbers.get(i).doubleValue());
            valueJson.put("caption", numbers.get(i).toString());
            numbersJson.set(i, valueJson);
        }
        getElement().setPropertyJson("values", numbersJson);
    }

    public void setColored(boolean colored) {
        getElement().setProperty("colored", colored);
    }

    public void setLines(boolean lines) {
        getElement().setProperty("lines", lines);
    }

    public void setWidth(int width) {
        getElement().getStyle().set("--column-chart-width", width + "px");
    }

    public void setHeight(int height) {
        getElement().getStyle().set("--column-chart-height", height + "px");
    }

    public void setColor(String cssColor) {
        getElement().getStyle().set("--lumo-primary-color", cssColor);
    }

    public void setAxis(ColumnChartAxis axis) {
        if (axis == ColumnChartAxis.NONE) {
            getElement().removeAttribute(ColumnChartAxis.RIGHT.toString());
            getElement().removeAttribute(ColumnChartAxis.LEFT.toString());            
        } else {
            getElement().setAttribute(axis.toString(), true);
        }
    }

    public void setColumnMargin(int margin) {
        getElement().getStyle().set("--lumo-space-xs", margin+"px");        
    }
}
