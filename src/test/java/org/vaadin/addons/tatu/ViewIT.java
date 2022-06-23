package org.vaadin.addons.tatu;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.testbench.TestBenchElement;

public class ViewIT extends AbstractViewTest {

    @Test
    public void componentWorks() {
        final TestBenchElement columnChart = $("column-chart").first();
        Assert.assertTrue(
                columnChart.$(TestBenchElement.class).all().size() > 0);
    }
}
