package org.vaadin.addons.tatu;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.vaadin.addons.tatu.ColumnChart.ColumnChartAxis;
import org.vaadin.addons.tatu.ColumnChart.ColumnChartValue;

import elemental.json.JsonArray;
import elemental.json.JsonObject;

public class ColumnChartTest {

    @Test
    public void setPresets_propertyIsSetValues() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setValues(Arrays.asList(new ColumnChartValue(10, "Ten"),
                new ColumnChartValue(20, "Twenty")));
        JsonArray valuesJson = (JsonArray) columnChart.getElement()
                .getPropertyRaw("values");
        JsonObject valueJson = valuesJson.get(0);
        Assert.assertEquals("Number is not correct", 10,
                valueJson.getNumber("number"), 0.01);
        Assert.assertEquals("Caption is not correct", "Ten:10",
                valueJson.getString("caption"));
        valueJson = valuesJson.get(1);
        Assert.assertEquals("Number is not correct", 20,
                valueJson.getNumber("number"), 0.01);
        Assert.assertEquals("Caption is not correct", "Twenty:20",
                valueJson.getString("caption"));
    }

    @Test
    public void setPresets_propertyIsSetNumbers() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setNumbers(Arrays.asList(10, 20));
        JsonArray valuesJson = (JsonArray) columnChart.getElement()
                .getPropertyRaw("values");
        JsonObject valueJson = valuesJson.get(0);
        Assert.assertEquals("Number is not correct", 10,
                valueJson.getNumber("number"), 0.01);
        Assert.assertEquals("Caption is not correct", "10",
                valueJson.getString("caption"));
        valueJson = valuesJson.get(1);
        Assert.assertEquals("Number is not correct", 20,
                valueJson.getNumber("number"), 0.01);
        Assert.assertEquals("Caption is not correct", "20",
                valueJson.getString("caption"));
    }

    @Test(expected = NullPointerException.class)
    public void columnChart_setValues_null() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setValues(null);
    }

    @Test(expected = NullPointerException.class)
    public void columnChart_setNumbers_null() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setNumbers(null);
    }

    @Test(expected = NullPointerException.class)
    public void colorPreset_setValue_wrongFormat() {
        ColumnChartValue value = new ColumnChartValue(null, null);
    }

    @Test
    public void linesPropertySet() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setLines(true);
        Assert.assertTrue(
                columnChart.getElement().getProperty("lines") == "true");
        columnChart.setLines(false);
        Assert.assertTrue(
                columnChart.getElement().getProperty("lines") == "false");
    }

    @Test
    public void coloredPropertySet() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setColored(true);
        Assert.assertTrue(
                columnChart.getElement().getProperty("colored") == "true");
        columnChart.setColored(false);
        Assert.assertTrue(
                columnChart.getElement().getProperty("colored") == "false");
    }

    @Test
    public void chartAxisAttributesSet() {
        ColumnChart columnChart = new ColumnChart();
        columnChart.setAxis(ColumnChartAxis.LEFT);
        Assert.assertTrue(
                columnChart.getElement().getAttribute("leftaxis") != null);
        columnChart.setAxis(ColumnChartAxis.RIGHT);
        Assert.assertTrue(
                columnChart.getElement().getAttribute("rightaxis") != null);
        columnChart.setAxis(ColumnChartAxis.NONE);
        Assert.assertTrue(
                columnChart.getElement().getAttribute("rightaxis") == null);
        Assert.assertTrue(
                columnChart.getElement().getAttribute("leftaxis") == null);
    }
}
