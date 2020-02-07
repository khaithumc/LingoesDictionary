/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;


public class MyDefaultMetalTheme extends DefaultMetalTheme {
    
    private final Color color = new Color(201,208,240);
    
    @Override
    public ColorUIResource getWindowTitleInactiveBackground() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getWindowTitleBackground() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getPrimaryControlHighlight() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getPrimaryControlDarkShadow() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getPrimaryControl() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getControlHighlight() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getControlDarkShadow() {
    return new ColorUIResource(color);
  }

    @Override
  public ColorUIResource getControl() {
    return new ColorUIResource(Color.WHITE);
  }
}
