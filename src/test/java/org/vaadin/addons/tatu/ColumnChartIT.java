package org.vaadin.addons.tatu;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.screenshot.ImageFileUtil;

public class ColumnChartIT extends AbstractViewTest {

    @Override
    public void setup() throws Exception {
        super.setup();

        // Hide dev mode gizmo, it would interfere screenshot tests
        $("vaadin-dev-tools").first().setProperty("hidden", true);
    }

    @Test
    public void widthAndHeight() {
        ColumnChartElement chart = $(ColumnChartElement.class).first();
        Assert.assertEquals("350px",chart.getWidth());
        Assert.assertEquals("600px",chart.getHeight());
    }

    @Test
    public void valuesSet_ColumnLabels() {
        ColumnChartElement chart = $(ColumnChartElement.class).first();
        Assert.assertEquals("Mercedes:5.11",chart.getColumn(0).getText());
        Assert.assertEquals("BMW:-2.15",chart.getColumn(1).getText());
        Assert.assertEquals("Toyota:11.23",chart.getColumn(2).getText());
        Assert.assertEquals("Ford:7.12",chart.getColumn(3).getText());
        Assert.assertEquals("Kia:-1.12",chart.getColumn(4).getText());
        Assert.assertEquals("Seat:-4.31",chart.getColumn(5).getText());
        Assert.assertEquals("SAAB:0.51",chart.getColumn(6).getText());
        Assert.assertEquals("Volvo:-3.25",chart.getColumn(7).getText());
        Assert.assertEquals("Honda:8.25",chart.getColumn(8).getText());
        Assert.assertEquals("Opel:10.11",chart.getColumn(9).getText());
    }

    @Test
    public void valuesSet_Visual() throws IOException {
        Assert.assertTrue(testBench().compareScreen(ImageFileUtil
                .getReferenceScreenshotFile("column-chart-cars.png")));
    }

    @Test
    public void componentWorks() {
        final TestBenchElement columnChart = $("column-chart").first();
        Assert.assertTrue(
                columnChart.$(TestBenchElement.class).all().size() > 0);
    }
}
